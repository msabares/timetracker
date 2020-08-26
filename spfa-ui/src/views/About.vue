<template>
  <div class="about">
    <!--used to send the current logged in person to the profile page-->
    <ProfileList :currentPerson="vueRoot.currentPerson"/>

  </div>
</template>

<script>

  import ProfileList from "@/components/profile/ProfileList";
  export default {
    components: {ProfileList},
    computed:{
      vueRoot(){
        return this.$root;
      }
    }
  }
</script>
