<template>
    <b-card
            style="max-width: 20rem;"
            class="mx-auto"
            bg-variant="light"
    >
        <b-form @submit.prevent="login">

            <b-form-group id="username" label="Username:" label-for="uname">
                <b-form-input id="uname" v-model="username" required type="email" :disabled="loading"></b-form-input>
            </b-form-group>

            <b-form-group id="password" label="Password:" label-for="pwd">
                <b-form-input id="pwd" v-model="password" required type="password" :disabled="loading"></b-form-input>
            </b-form-group>

            <b-button variant="success" type="submit" :disabled="loading">Log In <font-awesome-icon icon="cog" spin v-if="loading"/></b-button>

            <div v-if="showLoginError" class="error alert-danger">
                Invalid Username or Password
            </div>

        </b-form>
    </b-card>
</template>

<script>

    export default {
        name: "LoginCard",
        data()
        {
            return {

                username: "",
                password: "",
                showLoginError: false,
                loading: false
            }
        },

        methods: {
            login()
            {
                this.loading = true;
                this.$store
                    .dispatch('login', {
                        email: this.username.toLowerCase(),
                        password: this.password
                    })
                    .then(() =>
                    {
                        this.$router.push({name: 'calendar'});
                        this.loading = false;
                    })
                    .catch(err =>
                    {
                        //this.showLoginError = err.response.data.error
                        this.showLoginError = true;
                        this.loading = false;
                    });

            },
        },
        state: {},
    }
</script>

<style scoped>

</style>