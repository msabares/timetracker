<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>

        <b-modal id="modal-create-category" @shown="resetModal" no-close-on-backdrop no-close-on-esc hide-header-close>
            <template v-slot:modal-title="">{{categoryTitle}}</template>
            <div class="text-center">
                <b-spinner style="width: 5em; height: 5em" variant="primary" id="load_spinner" v-if="isLoading"></b-spinner>
            </div>
            <b-form-group v-if="!isLoading">

                <b-form-group id="lbl_name" label="Name:"><!--Name label-->
                    <!--Name Input-->
                    <b-form-input
                            maxlength="100"
                            id="input_name"
                            v-model.trim="$v.newCategory.categoryName.$model"
                            @change="showNameError=true; showModalError=false"
                            :state="nameError"
                    ></b-form-input>
                    <!--Error messages-->
                    <b-form-invalid-feedback id="error_name_minLength" v-if="showNameError && (!$v.newCategory.categoryName.minLength || !$v.newCategory.categoryName.required)">
                        Name must be between 2 and 100 characters
                    </b-form-invalid-feedback>
                    <b-form-invalid-feedback id="error_name_specialChar" v-if="!$v.newCategory.categoryName.nameSpecialChar">
                        Name may only contain alphanumeric or punctuation characters.
                    </b-form-invalid-feedback>
                </b-form-group>

                <!--Description-->
                <b-form-group id="lbl_desc" label="Description">
                    <b-form-input
                            maxlength=255
                            v-model="$v.newCategory.categoryDescription.$model"
                            @change="showDescError=true; showModalError=false"
                            :state="descriptionError"
                            id="input_description">
                    </b-form-input>

                    <!--Error Messages-->
                    <b-form-invalid-feedback id="error_description" v-if="!$v.newCategory.categoryDescription.maxLength"><!--Not required-->
                        Description character limit is 255 characters
                    </b-form-invalid-feedback>
                </b-form-group>

                <!--ParentID dropdown-->
                <b-form-group id="select_Parent" label="Parent Category:">

                    <b-form-select
                            id="parentCategory_id"
                            v-model="newCategory.parentCategory"
                            :options="sortedCategories"
                            @change="showModalError=false">
                        <!--For Top-Level categories-->
                        <template v-slot:first>
                            <b-form-select-option :value="null" >--No Parent Category--</b-form-select-option>
                        </template>
                    </b-form-select>
                </b-form-group>
                <!--No error messages, because null/no parent is a viable option-->
            </b-form-group>

            <!--Custom Footer to contain the error message for the whole "new Category modal"-->
            <template v-slot:modal-footer="{cancel}" class="ml-0 pl-0">
                <p class="text-danger text-nowrap m-0" id="error_createCategory" v-if="showModalError && !isLoading">Oops! Please review your inputs.</p>
                <b-button class="button ml-3" id="btn_cancel" variant="secondary" @click="cancel()" v-if="!isLoading">Cancel</b-button>
                <b-button class="button text-sm-center" id="btn_createCategory" variant="primary" @click="createCategory" :disabled="isLoading" v-if="!isLoading">Create Category</b-button>
            </template>
        </b-modal>

        <!--Confirmation modal-->
        <b-modal id="modal-confirm-category" title="Category Created" no-close-on-backdrop no-close-on-esc hide-header-close size="md">
            <div>
                <p id="confirm_message">{{confirmationMessage}}</p>
                <p id="confirm_name">Name: {{newCategory.categoryName}}</p>
                <p id="confirm_description">Description: {{newCategory.categoryDescription}}</p>
                <p id="confirm_parent">Parent Category: {{parentCategoryMessage}}</p>
            </div>

            <template v-slot:modal-footer="{ok}">
                <b-button id="btn_ok" @click="ok()" >OK</b-button>
            </template>
        </b-modal>

        <!--Error: No Connection Modal-->
        <b-modal id="no-connection-modal" title="Error" no-close-on-backdrop no-close-on-esc hide-header-close ok-only size="sm">
            <p>
                <b id ="error_connection">Connection Error</b>
            </p>
        </b-modal>
    </div>
</template>

<script>

    //Import the specific Vuelidate modules
    import {minLength,maxLength,required, helpers} from 'vuelidate/lib/validators'
    //Custom regex validator to use with Vuelidate
    //https://vuelidate.js.org/#sub-regex-based-validator
    const nameSpecialChar = helpers.regex('titleSpecialChar', /^[A-Za-z0-9 ]*$/);

    export default {
        props:{
            catType: {
                Type: Object,
                default: () => ({val: 0})
            }
        },
        data: function () {
            return {
                isLoading:false, //boolean switch for database
                categories:[],//array of categories
                showNameError:false, //boolean to check whether or not to show error messages
                showDescError:false,  //flag to determine showing of description errors
                newCategory:{  //Default empty object, gets set from form inputs and sent via Axios for POST
                    categoryName:'',
                    categoryDescription:'',
                    parentCategory:'',//null = no parent id
                    categoryType: null
                },
                showModalError:false,  //Flag to determine showing of general modal errors
                parentCategoryMessage:"",  //String used for confirmation message in confirmation modal
                getFilter:null,            //gets set in the Watch for getType, 1 for Person, 2 for Task
                categoryTitle:"Create Category",  //Dynamic title that gets adjusted depending on Task/Member button called the component
                confirmationMessage:""  //Dynamic confirmation message
            }
        },

        methods: {
            //Post a new Category to DB
            postCategory: function () {
                this.isLoading = true;  //Set loading flag to show spinner

                this.axios({
                    method:'POST',
                    url:this.apiUrlBase+"/categories",
                    headers:{'Content-Type' : 'application/ld+json'},
                    data:this.newCategory  //Send the new category
                })
                    .then((response) =>{
                        //show confirmation
                        this.confirmationMessage = this.newCategory.categoryType === 1 ? "A new Member Grouping has been created!" : "A new Work Assignment has been created!";
                        this.$bvModal.show('modal-confirm-category');
                        //hide main modal
                        this.$bvModal.hide('modal-create-category');
                        this.isLoading =false;
                    })
                    .catch((errors) =>{
                        this.isLoading =false;
                    })

            },

            getCategories() {
                //Set the isLoading flag to hide the modal and show the spinner
                this.isLoading = true;

                this.axios({
                    method: "GET",
                    url:this.apiUrlBase+'/categories?categoryType='+this.getFilter,
                    header: {'Accept': 'application/ld+json'},
                })
                    .then((response) => {
                        //If successful, load the categories into this.categories
                        let axiosResult = response.data['hydra:member'];
                        //Pull the new Categories from the response and append them
                        for (let i = 0; i < axiosResult.length; i++) {
                            //Each data member from response gets added to
                            //categories array to be displayed in dropdown
                            this.categories.push({
                                value: '/api/categories/' + axiosResult[i]['categoryID'],
                                text: axiosResult[i]['categoryName']
                            });
                        }
                        this.isLoading = false;
                    })
                    .catch((errors) => {
                        //If connection issue, disable 'loading' and
                        //show error connection modal
                        //THIS WILL SHOW DURING ALL ERRORS
                        this.$bvModal.hide("modal-create-category");
                        this.$bvModal.show("no-connection-modal");
                        this.isLoading = false;
                    })
            },
            //Will check that all fields are valid before making axios post
            createCategory:function ()
            {
                //Ensure no validations are flagged as true
                if(this.nameError !== true && this.descriptionError !== true)
                {
                    //Enable the error message flags
                    this.showNameError = true;
                    this.showDescError = true;
                    this.showModalError = true;
                }
                else
                {
                    //If no errors
                    this.showModalError = false;

                    //Determine which Parent Category confirmation message to show
                    if(this.newCategory.parentCategory === null)
                    {
                        //If no parent category is set, confirmation gives
                        //user-friendly response
                        this.parentCategoryMessage = "No Parent Category";
                    }
                    else
                    {
                        //Build the parentCategory confirmation message
                        //Get the INDEX of the selected parentCategory
                        let index = (document.getElementById("parentCategory_id").selectedIndex)-1;
                        //Use the INDEX to get the text value of the label
                        this.parentCategoryMessage = this.sortedCategories[index].text;
                    }
                    //If all validation clears, try to POST the new category
                    this.postCategory();
                }
            },
            //Resets the modal inputs and drop-down to blank, or 'Top-Level'
            resetModal:function()
            {
                this.showNameError = false;
                this.showDescError = false;
                this.showModalError = false;
                this.categories=[];
                this.getCategories();

                this.newCategory =
                    {
                        categoryName:'',
                        categoryDescription:'',
                        parentCategory: null,
                        categoryType: this.catType.val
                    }
            },
        },
        computed:
            {
                //this sorts the categories returned by the API
                sortedCategories: function() {
                    //Helper function that actually performs the sorting by the TEXT value
                    //of the categories, converting to lower case to ensure case-insensitive
                    function compare(a,b){
                        return a.text.toLowerCase().localeCompare(b.text.toLowerCase())
                    }
                    //Return a COPY of the category array to display (otherwise get mutation error)
                    return this.categories.slice(0).sort(compare);
                },
                //Returns the state of name field validation
                nameError: function() {
                    //This outer flag isn't set until values have been entered, or save button clicked
                    if (this.showNameError) {
                        if (!this.$v.newCategory.categoryName.minLength || !this.$v.newCategory.categoryName.maxLength || !this.$v.newCategory.categoryName.nameSpecialChar
                            || !this.$v.newCategory.categoryName.required) {
                            //If validator returns false on any condition (input violates validation),
                            //return a false state indication validation error
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return null;
                    }
                },
                //Returns state of description field validation
                descriptionError: function() {
                    return (!this.showDescError)?null:this.$v.newCategory.categoryDescription.maxLength
                }
            },
        //This section is used by 'Vuelidate' to use their built-in validators
        validations:{
            newCategory:{
                categoryName:{
                    minLength: minLength(2),
                    maxLength: maxLength(100),
                    nameSpecialChar,
                    required
                },
                categoryDescription:{
                    maxLength: maxLength(255)
                }
            }
        },
        watch:{
            catType: {
                deep: true,
                handler: function(current) {
                    if(current.val === 2)
                    {
                        this.categoryTitle = "Create Work Assignment";
                        this.newCategory.categoryType=2;
                        this.getFilter = 2;
                    }
                    else if (current.val === 1)
                    {
                        this.categoryTitle = "Create Member Grouping";
                        this.newCategory.categoryType=1;
                        this.getFilter = 1;
                    }
                }
            }
        }
    }
</script>
<style scoped>
    #error_createCategory{
        width:225px;
    }
</style>