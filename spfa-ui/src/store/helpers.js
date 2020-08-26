import { mapGetters } from 'vuex'
//these are from the getters section in in /store/index.js
export const authComputed = {
  ...mapGetters(['loggedIn', 'tokenPerson', 'tokenRole', 'tokenFirstName','tokenLastName','tokenCategoryName','tokenEmploymentStatus','tokenScheduledHours'])
};
