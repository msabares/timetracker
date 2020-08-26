import Vue from 'vue'
import VueRouter from 'vue-router'
import Reports from '../views/Reports'
import Calendar from '../views/Calendar'
import Administration from "../views/Administration"
import Login from '../views/Login'
import Vuex from "vuex";

Vue.use(VueRouter);
Vue.use(Vuex);

const routes = [
    {
        path: '/',
        name: 'index',
        meta: {requiresAuth: true}
    },
    {
        path: '/calendar',
        name: 'calendar',
        component: Calendar,
        meta: {requiresAuth: true}
    },
    {
        path: '/reports',
        name: 'reports',
        component: Reports,
        meta: {requiresAuth: true}
    },
    {
        path: '/administration',
        name: 'administration',
        component: Administration,
        meta: {requiresAuth: true, requiresAdmin: true}
    },

    {
        path: '/about',
        name: 'about',
        meta: {requiresAuth: true},
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {alreadyAuth: true}
    }
];


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    const loggedIn = localStorage.getItem('user'); //Gets us the token from local storage

    if(to.name === "index")
    {
        next('/calendar');
    }

    //If the token exist, extract the role.
    const tokenRole = loggedIn ? JSON.parse(atob(loggedIn.split('.')[1])).roles[0] : "";

    if (to.matched.some(record => record.meta.requiresAuth)) {

        if(!loggedIn) {
            //Routes users to the login page if they are accessing pages that require login
            next('/login');
        } else if (to.matched.some(record => record.meta.requiresAdmin) && tokenRole !== "ROLE_ADMIN") {
            //Routes users to the calendar page if they are accessing an admin page without admin role
            next('/calendar');
        } else {
            next();
        }
    } else if (to.matched.some(record => record.meta.alreadyAuth) && loggedIn) {
        //Routes users to the calendar page if they are accessing pages that are for users who haven't logged in yet
        next('/calendar');
    } else {
        next();
    }


});



export default router
