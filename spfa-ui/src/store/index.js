import Vuex from 'vuex'
import Vue from 'vue'
import Axios from "axios";
import router from '../router/index'

Vue.use(Vuex);
import baseURL from "../main";

const jwtRegex = /^[A-Za-z0-9-_=]+\.[A-Za-z0-9-_=]+\.?[A-Za-z0-9-_.+/=]*$/

export default new Vuex.Store({
    state: {
        userToken: null,
    },
    mutations: {
        SET_USER_DATA(state, userData)
        {
            state.userToken = userData;
            localStorage.setItem('user', JSON.stringify(userData));
            if(userData.token != null)
            {
                Axios.defaults.headers.common['Authorization'] = `Bearer ${ userData.token }`
            }
        },
        CLEAR_USER_DATA(state) {
            state.userToken = null;
            localStorage.removeItem('user');
            Axios.defaults.headers.common['Authorization'] = null;
            //location.reload();
        }
    },
    actions: {
        login({commit}, credentials)
        {
            return Axios.post(baseURL.backendURL + '/login', credentials)
                .then(
                    ({data}) =>
                    {
                        location.reload();
                        commit('SET_USER_DATA', data);
                    })
        },
        logout({commit}, showToast) {
            commit('CLEAR_USER_DATA');
            router.push({name: "login"})
            if(showToast) {
                this._vm.$bvToast.toast('You are now logged out!', {
                    title: 'Logged Out',
                    variant : 'success',
                    toaster: 'b-toaster-bottom-center',
                    autoHideDelay: 1500,
                    solid : true
                })
            }
        }
    },
    getters: {
        loggedIn(state)
        {
            return state.userToken!=null;
        },
        validToken(state) {
            return  state.userToken.token !== "" && state.userToken.token.match(jwtRegex);
        },
        token: state => JSON.stringify(state.userToken),
        tokenPerson(state, getters) {
            //returns /api/people/###
            //atob decodes 64-base
            //gets the "body" part of the token, everything after first . and before second .
            let id = getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).person:null;
            if(id != null)
            {
                return id.substring(12)
            }
        },
        tokenFirstName(state, getters) {
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).firstName:null;
        },
        tokenLastName(state, getters) {
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).lastName:null;
        },
        tokenRole(state, getters) {
            //will return ROLE_USER...etc
            //Checking to see if state.userToken and is not empty
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).roles[0]:null;
        },
        tokenCategoryName(state,getters){
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).categoryName:null;
        },
        tokenEmploymentStatus(state,getters){
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).employmentStatus:null;
        },
        tokenScheduledHours(state,getters){
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).scheduledHours:null;
        },
        tokenUser(state, getters) {
            //returns the user email
            return getters.loggedIn && getters.validToken ? JSON.parse(atob(getters.token.split('.')[1])).username:null;
        }
    },
    modules: {}
})
