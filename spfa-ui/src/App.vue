<template>
    <div id="app">
        <b-navbar id="nav" v-if="tokenRole!=null" class="m-3">
            <b-navbar-brand>
                <img id="logo" src="../src/assets/spfaLogo.png">
            </b-navbar-brand>

            <b-navbar-nav class="ml-auto">
                <b-nav-item to="/calendar">Calendar</b-nav-item>
                <b-nav-item to="/reports">My Reports</b-nav-item>
                <b-nav-item v-if="tokenRole==='ROLE_ADMIN'" to="/administration">Administration</b-nav-item>
            </b-navbar-nav>

            <!--b-nav-item-dropdown adds a bullet point. Had to style it away-->
            <b-nav-item-dropdown id="moreOptions" style="list-style-type: none" class="ml-auto" :text="tokenFirstName+' '+tokenLastName">
                <b-dropdown-item to="/about">View Profile</b-dropdown-item>
                <b-dropdown-item id="btnLogout" @click="$bvModal.show('modal-logout-confirmation')">Log Out</b-dropdown-item>
            </b-nav-item-dropdown>
        </b-navbar>

        <!--Logout Confirmation Modal-->
        <b-modal id="modal-logout-confirmation" title="Log Out" no-close-on-backdrop no-close-on-esc hide-header-close size="sm">
            <p id="confirmMsg" class="text-center">Are you sure you want to log out?</p>
            <template v-slot:modal-footer="{cancel}">
                <b-button id="btn_cancel" @click="cancel()">Cancel</b-button>
                <b-button id="btn_logout" variant="danger" @click="logout">Logout</b-button>
            </template>
        </b-modal>

        <router-view/>
    </div>
</template>


<script>

    import Vue from 'vue'
    import {authComputed} from "./store/helpers";
    import Vuex from "vuex"

    export default {
        computed: {
            ...authComputed,

            login(){
                return this.$store.getters.loggedIn
            }
        },
        components: {},
        data()
        {
            return{
                memberName: "",
            }
        },
        methods: {
            logout: function() {
                this.$store.dispatch('logout', true).then( () => {
                    this.$bvModal.hide('modal-logout-confirmation')
                });
            }
        },
    }

</script>

<style lang="scss">
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #011e41;
    }

    #nav {
        padding: 0 50px;
        a {
            color: #011e41;

            &.router-link-exact-active {
                color: #0171ce;
                font-weight: bold;
            }
        }
    }
    .spfa-dark {
        background-color: #011e41 !important;
    }
    .spfa-light {
        background-color: #0171ce !important;
    }

    #profileDropdown__BV_toggle_
    {

        width: 150px;
        max-width: 150px;
        text-overflow: ellipsis;
    }
    #logo {
        width: 150px;
    }
</style>
