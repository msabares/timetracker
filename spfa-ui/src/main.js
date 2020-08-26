import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vuelidate from 'vuelidate'
import store from './store/index'
import Vuex from 'vuex'
import {BootstrapVue, BootstrapVueIcons} from 'bootstrap-vue'

//FontAwesome Cog import stuff
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faCog } from '@fortawesome/free-solid-svg-icons'
import {authComputed} from "./store/helpers";
library.add(faCog);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.config.productionTip = false;



Vue.use(VueAxios, axios);
Vue.use(Vuex);
Vue.use(Vuelidate);

//Bootstrap vue icons.
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

//Change this to change API
let ipAddr = "spfa-api.herokuapp.com";

Vue.store = Vue.prototype.apiUrlBase = 'https://' + ipAddr + '/api';
Vue.prototype.role = "NA";
//Vue.config.productionTip = false;

export default {
    name: "url",
    backendURL: "https://" + ipAddr,
    apiURL: "https://" + ipAddr + "/api",
}

new Vue({
    router,
    store,
    data:{
        currentPerson:{},
    },
    created() {
        const userString = localStorage.getItem('user');
        if (userString) {
            const userData = JSON.parse(userString);
            this.$store.commit('SET_USER_DATA', userData);
            this.getPerson();
        }

        //If current token in local storage isn't valid (by using the validToken getter method from the store), log them out
        if(this.$store.getters.loggedIn && !this.$store.getters.validToken) {
            this.$store.dispatch('logout', false)
        }

        axios.interceptors.response.use(response => response, error => {
            //401 Unauthorized access error will log out the user if they already have a token in local storage.
            if (error.response.status === 401 && this.$store.getters.loggedIn) {
                this.$store.dispatch('logout', false)
            }
            return Promise.reject(error)
        })
    },
    methods:{
        getPerson:function () {
            this.axios.get(this.apiUrlBase+"/people/"+this.$store.getters.tokenPerson)
                .then((response) =>
                {
                    Object.assign(this.currentPerson,response.data);
                });
        }
    },
    computed: {
        ...authComputed,
        login(){
            return this.$store.getters.loggedIn;
        }
    },
    watch:{
        login: {
            deep:true,
            handler:function(newVal,oldVal){
                if(newVal){
                    this.getPerson();
                }
            }
        }
    },
    render: h => h(App)
}).$mount('#app');
