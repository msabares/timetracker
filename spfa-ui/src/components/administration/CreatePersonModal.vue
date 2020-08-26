<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="CreatePersonModal" >
        <b-modal id="modal-create-member" @show="setupModal" @hidden="resetModal" title="Create Member" no-close-on-backdrop no-close-on-esc hide-header-close>

            <div class="text-center">
                <b-spinner style="width: 5em; height: 5em" variant="primary" id="load_spinner" v-if="isLoading"></b-spinner>
            </div>

            <b-form-group v-if="!isLoading">

                <div id="firstAndLastName">
                    <b-form-group id="lbl_firstName" label="First Name:">
                        <b-form-input id="input_firstName"
                                      size="sm"
                                      v-model.trim="$v.newPerson.firstName.$model"
                                      :state="(stateField.fName === true) ? validateState('firstName') : null"
                                      @change="stateField.fName = true; showModalError = false"
                                      maxLength="100"/>
                        <b-form-invalid-feedback id="error_firstName_maxLength" v-if="!$v.newPerson.firstName.maxLength">First Name cannot be greater than 100 characters.</b-form-invalid-feedback>
                        <b-form-invalid-feedback id="error_firstName_required" v-if="!$v.newPerson.firstName.required">First Name is required.</b-form-invalid-feedback>
                        <b-form-invalid-feedback id="error_firstName_specialChar" v-if="!$v.newPerson.firstName.nameValidation">First Name cannot contain special characters.</b-form-invalid-feedback>
                    </b-form-group>


                    <b-form-group id="lbl_lastName" label="Last Name:">

                        <b-form-input id="input_lastName"
                                      size="sm"
                                      v-model.trim="$v.newPerson.lastName.$model"
                                      :state="(stateField.lName === true) ? validateState('lastName') : null"
                                      @change="stateField.lName = true; showModalError = false"
                                      maxLength="100"/>

                        <b-form-invalid-feedback id="error_lastName_maxLength" v-if="!$v.newPerson.lastName.maxLength">Last Name cannot be greater than 100 characters.</b-form-invalid-feedback>
                        <b-form-invalid-feedback id="error_lastName_required" v-if="!$v.newPerson.lastName.required">Last Name is required.</b-form-invalid-feedback>
                        <b-form-invalid-feedback id="error_lastName_specialChar" v-if="!$v.newPerson.lastName.nameValidation">Last Name cannot contain special characters.</b-form-invalid-feedback>
                    </b-form-group>
                </div>

                <b-form-group id="lbl_email" label="Email:">

                    <b-form-input id="input_email"
                                  maxLength="255"
                                  size="sm"
                                  v-model="$v.newPerson.email.$model"
                                  :state="(stateField.email === true) ? validateState('email') : null"
                                  @change="stateField.email = true; showModalError = false"
                                  @blur.native="checkEmail()"
                                  type="email"/>

                    <b-form-invalid-feedback id="error_email_maxLength" v-if="!$v.newPerson.email.maxLength">Email cannot be greater than 255 characters.</b-form-invalid-feedback>
                    <b-form-invalid-feedback id="error_email_required" v-if="!$v.newPerson.email.required">Email is required.</b-form-invalid-feedback>
                    <b-form-invalid-feedback id="error_email_format" v-if="!$v.newPerson.email.email">Email address is not valid.</b-form-invalid-feedback>
                    <span v-if="pendingEmailCheck"><b-spinner small variant="primary" id="email_spinner"/> Checking if email is available...</span>
                    <b-form-invalid-feedback id="error_email_unique" v-if="!$v.newPerson.email.validateUniqueEmail && !pendingEmailCheck">Email address is unavailable.</b-form-invalid-feedback>
                </b-form-group>

                <b-form-group id="lbl_password" label="Password:">

                    <b-form-input id="input_password"
                                  v-model="$v.newPerson.password.$model"
                                  :state="(stateField.password === true) ? validateState('password') : null"
                                  @change="stateField.password = true; showModalError = false"
                                  size="sm"
                                  type="password"/>

                    <b-form-invalid-feedback id="error_password_format" v-if="!$v.newPerson.password.passwordValidation">Password must have the following:<br/>
                        • Include both lower and upper characters<br/>
                        • Include at least one number and special character<br/>
                        • Be at least 8 characters long
                    </b-form-invalid-feedback>
                    <b-form-invalid-feedback id="error_password_required" v-if="!$v.newPerson.password.required">Password is required.</b-form-invalid-feedback>

                </b-form-group>

                <div id="departmentAndStatus">
                    <b-form-group id="lbl_department" label="Department:">

                        <b-form-select v-model="$v.newPerson.category.$model"
                                       :state="(stateField.category === true) ? validateState('category') : null"
                                       @change="stateField.category = true; showModalError = false"
                                       size="sm"
                                       :options="departmentOptions"
                                       id="input_department">

                            <!--A placeholder value for the select list-->
                            <template v-slot:first>
                                <b-form-select-option :value=null disabled>--Select a Department--</b-form-select-option>
                            </template>
                        </b-form-select>
                        <b-form-invalid-feedback id="error_department_required" v-if="!$v.newPerson.category.required">Department is required.</b-form-invalid-feedback>

                    </b-form-group>


                    <b-form-group id="lbl_status" label="Employment Status:">

                        <b-form-select v-model="$v.newPerson.employmentStatus.$model"
                                       :state="(stateField.employmentStatus === true) ? validateState('employmentStatus') : null"
                                       @change="stateField.employmentStatus = true; showModalError = false"
                                       size="sm"
                                       :options="statusOptions"
                                       id="input_employmentStatus">
                            <!--A placeholder value for the select list-->
                            <template v-slot:first>
                                <b-form-select-option :value=null disabled>--Select a Status--</b-form-select-option>
                            </template>
                        </b-form-select>
                        <b-form-invalid-feedback id="error_employmentStatus_required" v-if="!$v.newPerson.employmentStatus.required">Employment Status is required.</b-form-invalid-feedback>

                    </b-form-group>
                </div>

                <b-form-group id="lbl_hours" label="Scheduled Hours:">

                    <b-form-input id="input_hours"
                                  v-model.trim="$v.newPerson.scheduledHours.$model"
                                  :state="(stateField.scheduledHours === true) ? validateState('scheduledHours') : null"
                                  @change="stateField.scheduledHours = true; showModalError = false"
                                  min="0"
                                  max="37.5"
                                  step="0.5"
                                  type="number"
                                  size="sm"/>

                    <b-form-invalid-feedback id="error_hours_Length" v-if="!$v.newPerson.scheduledHours.between || !$v.newPerson.scheduledHours.decimal">Scheduled Hours be between 0 than 37.5.</b-form-invalid-feedback>

                </b-form-group>

            </b-form-group>

            <template v-slot:modal-footer="{cancel}" class="ml-0 pl-0">
                <p class="text-danger" id="error_createMember" v-if="showModalError && !isLoading"> Oops! Please review your inputs.</p>
                <b-button class="text-sm-center" id="btn_cancel" variant="secondary" @click="cancel()" v-if="!isLoading" >Cancel</b-button>
                <b-button class="text-sm-center" id="btn_create" variant="primary" @click.stop.prevent="createPerson()" v-if="!isLoading" >Create Member</b-button>
            </template>

        </b-modal>

        <!--Confirmation Modal to be displayed when a successful POST occurs-->
        <b-modal id="modal-confirm-member" title="Member Created" no-close-on-backdrop no-close-on-esc hide-header-close size="sm">
            <p id="confirmMsg">{{this.confirmationMessage}}</p>
            <template v-slot:modal-footer="{ok}">
                <b-button id="btn_ok" @click="ok()">Ok</b-button>
            </template>
        </b-modal>
    </div>
</template>

<script>
    import {maxLength,required, helpers, between, email, decimal} from 'vuelidate/lib/validators'
    const passwordValidation = helpers.regex('passwordValidation', /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#\\~';$^+=!*<>()@%&]).{8,}$/);
    const nameValidation = helpers.regex('passwordValidation', /^('?-?[a-zA-z]{1,}'?-? ?){1,3}$/);


    export default {
        name: "CreatePersonModal",
        data: function () {
            return {
                showModalError: false,
                isLoading:false,
                pendingEmailCheck: false,
                isEmailUnique: false,
                newPerson: {
                    firstName: '',
                    lastName: '',
                    email: '',
                    password:'',
                    category: null,
                    employmentStatus: null,
                    scheduledHours: 0.0
                },
                currentStatus: [],
                statusOptions: [
                    {value: 'full time', text: 'Full Time'},
                    {value: 'part time', text: 'Part Time'},
                    {value: 'casual', text: 'Casual'},
                    {value: 'on leave', text: 'On Leave'}
                ],
                departmentOptions: [],
                stateField: {
                    fName: false,
                    lName: false,
                    email: false,
                    password: false,
                    category: false,
                    employmentStatus: false,
                    scheduledHours: false
                },
                confirmationMessage: ''
            }
        },
        methods: {
            validateState(name) {
                const { $dirty, $error } = this.$v.newPerson[name];
                return $dirty ? !$error : null;
            },
            getDepartments() { //Categories with department categories
                this.axios({
                    method: "GET",
                    url:this.apiUrlBase+'/categories?categoryType=1&parentCategory.categoryName=Department&order%5BcategoryName%5D=asc',
                    header: {'Accept': 'application/ld-json'}
                }).then( (response) => {
                    //If successful, load the categories into this.categories
                    let axiosResult = response.data['hydra:member'];
                    //Pull the new Categories from the response and append them
                    for (let i = 0; i < axiosResult.length; i++) {
                        //Each data member from response gets added to
                        //categories array to be displayed in dropdown
                        this.departmentOptions.push({
                            value: '/api/categories/' + axiosResult[i]['categoryID'],
                            text: axiosResult[i]['categoryName']
                        });
                    }
                }).catch( () => {

                }).finally(() => {
                    this.isLoading = false;
                })

            },
            createPerson() { //This method is going to validate our newPerson object, then call postPerson

                //All fields will now show any of their error messages.
                this.stateField = {
                    fName: true,
                    lName: true,
                    email: true,
                    password: true,
                    category: true,
                    employmentStatus: true,
                    scheduledHours: true
                };

                //Check each field for any errors
                this.$v.newPerson.$touch();

                //if any errors appear, exist out of the method.
                if (this.$v.newPerson.$anyError) {
                    this.showModalError = true;
                } else {
                    this.postPerson();
                }
            },
            postPerson() { //Post our validated Person object.
                this.isLoading = true;

                this.newPerson.scheduledHours = parseFloat(this.newPerson.scheduledHours);

                this.axios({
                    method:'POST',
                    url: this.apiUrlBase+'/people',
                    headers: {'Content-Type':'application/ld+json'},
                    data:this.newPerson
                }).then( ()=> {
                    //Because we reset the data when the modal is hidden, we need to save the first and last name to display
                    //in our confirmation modal that shows up next
                    this.confirmationMessage = 'Member ' + this.newPerson.firstName + ' ' + this. newPerson.lastName + ' has been created!';

                    this.$root.$emit('bv::refresh::table', 'person-table');

                    this.$bvModal.show('modal-confirm-member');

                    this.$bvModal.hide('modal-create-member');

                    this.isLoading = false;
                })
            },
            setupModal() {
                //Show loading model
                this.isLoading = true;

                //Get the current departments
                this.getDepartments();
            },
            resetModal() {
                //Reset onclick change.
                this.stateField = {
                    fName: false,
                    lName: false,
                    email: false,
                    password: false,
                    category: false,
                    employmentStatus: false,
                    scheduledHours: false
                };

                //Reset modal error message
                this.showModalError = false;

                //This actually resets the error message, but this may not be required
                this.$nextTick(() => {
                    this.$v.$reset();
                });

                //Reset newPerson object
                this.newPerson = {
                    firstName: '',
                    lastName: '',
                    email: '',
                    password:'',
                    category: null,
                    employmentStatus: null,
                    scheduledHours: 0.0
                };

                //Reset departmentOptions List
                this.departmentOptions=[];

            },
            async checkEmail() {
                //This method to validate unique email is separated from validations
                //as state is an on change event and we can't be making api calls that often.
                if(this.$v.newPerson.email.email && this.$v.newPerson.email.required) {
                    this.pendingEmailCheck = true;
                    // eslint-disable-next-line no-console
                    await this.axios({
                        method: "GET",
                        url: this.apiUrlBase + '/people?email=' + this.$v.newPerson.email.$model.toLowerCase(),
                        header: {'Accept': 'application/ld-json'}
                    }).then((response) => {
                        this.pendingEmailCheck = false;
                        this.isEmailUnique = response.data['hydra:member'].length === 0;
                    });
                } else {
                    this.isEmailUnique = true;
                }
            }
        },
        validations: {
            newPerson: {
                firstName: {
                    required,
                    maxLength: maxLength(100),
                    nameValidation
                },
                lastName: {
                    required,
                    maxLength: maxLength(100),
                    nameValidation
                },
                email: {
                    required,
                    maxLength: maxLength(255),
                    email,
                    validateUniqueEmail (email) {
                        if (email === '') return true;
                        return this.isEmailUnique;
                    }
                },
                password: {
                    required,
                    passwordValidation
                },
                category: {
                    required
                },
                employmentStatus: {
                    required
                },
                scheduledHours: {
                    between: between(0, 37.5),
                    decimal
                }
            }
        }
    }
</script>

<style scoped>
    #firstAndLastName, #departmentAndStatus {
        display: flex;
        justify-content: space-between;
    }

    #lbl_firstName, #lbl_lastName, #lbl_department, #lbl_status{
        width: 45%;
    }
</style>