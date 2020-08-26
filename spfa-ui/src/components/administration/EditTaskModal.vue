<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>


        <b-modal id="modal-edit-task" title="Create Task" @shown="resetModal" no-close-on-backdrop no-close-on-esc hide-header-close>
            <div class="text-center">
                <b-spinner style="width: 5em; height: 5em" variant="primary" id="load_spinner" v-if="isLoading"></b-spinner>
            </div>

            <b-form-group v-if="!isLoading">
                <b-form-group id="lbl_title" label="Title:">

                    <b-form-input
                            maxlength="255"
                            id="input_title"
                            v-model.trim="$v.newTask.title.$model"
                            @change="showTitleError=true; showModalError=false"
                            :state="(showTitleError) ? $v.newTask.title.minLength && $v.newTask.title.required && $v.newTask.title.titleSpecialChar : null"
                    >
                    </b-form-input>
                    <b-form-invalid-feedback id="error_title_minLength" v-if="showTitleError && (!$v.newTask.title.minLength || !$v.newTask.title.required)">Title must be between 1 and 255 characters long.</b-form-invalid-feedback>
                    <b-form-invalid-feedback id="error_title_specialChar" v-if="!$v.newTask.title.titleSpecialChar">Title can only contain alphanumeric or punctuation characters.</b-form-invalid-feedback>

                </b-form-group>

                <b-form-group id="lbl_estimate" label="Hours Estimate:">

                    <b-form-input
                            min="0"
                            max="1000000"
                            step="0.1"
                            v-model="$v.newTask.hoursEstimate.$model"
                            @change="showEstimateError=true; showModalError=false"
                            :state="(showEstimateError) ? $v.newTask.hoursEstimate.between && $v.newTask.hoursEstimate.required: null"
                            id="input_estimate">
                    </b-form-input>
                    <b-form-invalid-feedback id="error_estimate" v-if="!$v.newTask.hoursEstimate.between || !$v.newTask.hoursEstimate.required">Hours Estimate must be a number between 0 and 1000000.</b-form-invalid-feedback>


                </b-form-group>

                <b-form-group id="lbl_category" label="Category:">

                    <b-form-select
                            id="input_category"
                            v-model="$v.newTask.category.$model"
                            :options="sortedCategories"
                            @change="showCategoryError=true; showModalError=false"
                            :state="(showCategoryError) ? $v.newTask.category.required : null"
                    >
                        <!--A placeholder value for the select list-->
                        <template v-slot:first>
                            <b-form-select-option :value=null disabled>--Please select a Category--</b-form-select-option>
                        </template>
                    </b-form-select>
                    <b-form-invalid-feedback id="error_category" v-if="showCategoryError && !$v.newTask.category.required">Please select a Category from the list.</b-form-invalid-feedback>
                </b-form-group>

            </b-form-group>
            <!--Custom footer which contains a newTask error message, a standard cancel button, and a custom Create Task button-->
            <template  v-slot:modal-footer="{cancel}">
                <p class="text-danger" id="error_createEditTask" v-if="showModalError && !isLoading"> Oops! Please review your inputs.</p>
                <b-button class="button" id="btn_cancel" variant="secondary" @click="cancel()" v-if="!isLoading">Cancel</b-button>
                <b-button class="button" id="btn_createTask" variant="primary" @click="createTask" v-if="!isLoading">Create Task</b-button>
            </template>

        </b-modal>
        <!--Confirmation Modal to be displayed when a successful POST occurs-->
        <b-modal id="modal-confirm-task" title="Task Created" no-close-on-backdrop no-close-on-esc hide-header-close size="sm">Task {{newTask.title}} has been created!
            <template v-slot:modal-footer="{ok}">
                <b-button id="btn_ok" @click="ok()">Ok</b-button>
            </template>
        </b-modal>
    </div>

</template>

<script>

    //Import the specific Vuelidate modules
    import {minLength,maxLength,required, helpers, between} from 'vuelidate/lib/validators'
    //Custom regex validator to use with Vuelidate
    //https://vuelidate.js.org/#sub-regex-based-validator
    const titleSpecialChar = helpers.regex('titleSpecialChar', /^[A-Za-z0-9 ()\-_.,!:'/$]*$/);

    export default {
        name: "EditTaskModal",
        data() {
            return {
                newTask: {
                    title: '',
                    hoursEstimate: 0.0,
                    category: null
                },
                //Flag to show/hide the spinner
                isLoading: false,
                //Array of categories
                categories: [],
                //Flags which get set after the first @change event
                //on each field, which prevents the errors from showing
                //upon load
                showTitleError: false,
                showEstimateError: false,
                showCategoryError: false,
                showModalError: false
            }
        },
        methods: {
            //Get the categories from the API
            getCategories() {
                //Set the loading flag to hide the modal and show the spinner
                this.isLoading = true;

                this.axios({
                    method:"GET",
                    url: this.apiUrlBase+'/categories?categoryType=2',
                    header:{'Accept':'application/ld+json'},
                })
                    .then((response) =>{
                        //If successful, load the categories into this.categories
                        let axiosResult = response.data['hydra:member'];
                        //Pull the new Categories from the response and append them
                        for(let i=0; i < axiosResult.length; i++) {
                            this.categories.push({value:'/api/categories/'+axiosResult[i]['categoryID'], text: axiosResult[i]['categoryName']});
                        }
                        this.isLoading = false;
                    })
            },
            //Checks validation of the newTask before posting it
            createTask(){
                //This section verifies the validation, and if any invalid fields are present, enables
                //all the showError flags
                if(!this.$v.newTask.category.required || !this.$v.newTask.title.minLength || !this.$v.newTask.title.required ||
                    !this.$v.newTask.title.titleSpecialChar || !this.$v.newTask.hoursEstimate.between)
                {
                    this.showTitleError = true;
                    this.showEstimateError = true;
                    this.showCategoryError = true;
                    this.showModalError = true;
                }
                //If no validation issues, hide the modal error and post the newTask
                else {
                    this.showModalError = false;
                    this.postTask();
                }
            },

            postTask(){
                //Set the loading flag to activate the spinner
                this.isLoading = true;

                //Have to parse the float value form the input to save it
                this.newTask.hoursEstimate = parseFloat(this.newTask.hoursEstimate);
                //Perform the axios post to the API, sending the newTask object
                this.axios({
                    method:'POST',
                    url: this.apiUrlBase+'/tasks',
                    headers: {'Content-Type' : 'application/ld+json'},
                    data: this.newTask
                })
                    .then((response)=>{
                        //Show the confirmation modal
                        this.$bvModal.show('modal-confirm-task');
                        //Hide the main task modal
                        this.$bvModal.hide('modal-edit-task');
                        //Turn off the spinner
                        this.isLoading = false;
                        //Emit the newTask to the TaskList modal (our parent modal)
                        this.$emit('update-task-list', this.newTask);
                    });

            },
            //Resets the modal inputs and flags to their default values
            //Triggered when on @shown event for the modal
            resetModal() {
                this.showTitleError = false;
                this.showEstimateError = false;
                this.showCategoryError = false;
                this.showModalError = false;
                this.categories = [];
                this.getCategories();
                //this.categories = this.sortCategories();
                this.newTask = {
                    title: '',
                    hoursEstimate: 0.0,
                    category: null
                }
            },
        },
        computed: {
            //Case-insensitive sort for Categories[] which appear in the select dropdown
            sortedCategories: function() {
                function compare(a,b){
                    //https://stackoverflow.com/questions/8996963/how-to-perform-case-insensitive-sorting-in-javascript
                    //Converts to lower case to perform case-insensite sort
                    return a.text.toLowerCase().localeCompare(b.text.toLowerCase())
                }
                //Return categories[] sorted using our compare function
                return this.categories.slice(0).sort(compare)
            }
        },
        mounted(){

        },
        //This section is used by 'Vuelidate' to use their built-in validators
        validations: {
            newTask:{
                title:{
                    minLength: minLength(1),
                    maxLength: maxLength(255),
                    titleSpecialChar,
                    required
                },
                hoursEstimate:{
                    between: between(0,1000000),
                    required
                },
                category:{
                    required
                }
            }
        },

    }
</script>

<style scoped>

    #error_createEditTask {
        width: 250px;
    }

</style>