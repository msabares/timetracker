<template>
    <div id="login">
        <img id="logoBanner" src="../../src/assets/spfaVertical.png">
        <login-card id="loginCard"/>
    </div>
</template>

<script>
    import loginCard from '@/components/login/LoginCard.vue'

    export default {
        name: "Login",
        components: {
            loginCard
        }
    }
</script>

<style scoped>

</style>