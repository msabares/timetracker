<template>
    <div>
        <!--Card is used to display the users personal info. The card has a profile picture for future releases-->
        <b-card id="profileCard"
                title="Personal Information"
                img-src="https://i.vippng.com/png/small/355-3554387_create-digital-profile-icon-blue-profile-icon-png.png"
                img-alt="Image"
                img-left
                style="max-width: 45rem; margin: 0 auto;"
                class="mb-2"
                border-variant="primary"
        >
            <!--This text is what is displayed inside the card. The data is pulled from parents currentPerson variable -->
            <b-card-text id="profileCardText">
                <br/><p class="text-left" id="fname"><b>First Name: </b><span class="label label-default" >{{tokenFirstName}}</span></p>
                <p class="text-left"  id="lname"><b>Last Name: </b><span class="label label-default">{{tokenLastName}}</span></p>
                <p class="text-left" id="department"><b>Department: </b><span class="label label-default" >{{tokenCategoryName}}</span></p>
                <p class="text-left"  id="employment"><b>Employment Status: </b><span class="label label-default">{{tokenEmploymentStatus}}</span></p>

            </b-card-text>

        </b-card>
    </div>
</template>

<script>
    import {authComputed} from "../../store/helpers";

    export default {
        props: {currentPerson:{}},
        computed:{
            ...authComputed,
            vueRoot(){
                return this.$root;
            }
        }
    }
</script>

<style scoped>
    #profileCard {
        /*Margins for the card*/
        margin : 10em
    }
    #fname, #lname, #department, #employment{
        /*Used for overflow of long first,last names, department and employment status even though they will never be long enough*/
        white-space: nowrap;
        width: 20em;
        overflow: auto;

    }
</style>