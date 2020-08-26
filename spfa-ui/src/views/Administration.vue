<!--
    This file is the Administration View page
    It contains Administrative functions in color-coded div boxes
    Each 'Button' will open the corresponding Administrative component
    where the actual operations occur
    Is fairly scalable as it's based on a table configuration, so should be
    easy to add additional components and functions while maintaining aesthetics
-->

<template>
    <div class="administration">
        <create-category-modal :catType="categoryType" id="createCategoryModal"></create-category-modal>

        <EditTaskModal/>
        <CreatePersonModal/>

        <h1>Administration</h1>

        <b-container>
            <b-row align-h="around">
                <b-col class="pr-3">
                    <b-row>
                        <b-col class="alert-warning border border-warning mb-4" >
                            <b-row align-h="center" class="p-2">
                                <h5>Task Management</h5>
                            </b-row>
                            <b-row align-h="center" class="py-2 px-5">
                                <b-button id="btn_create_task_cat" variant="warning" @click="createTaskCat" class="btn-block">Create Work Assignment</b-button>
                            </b-row>
                            <b-row align-h="center" class="py-2 px-5">
                                <b-button id="btn_new_task_modal" variant="warning" class="btn-block" @click="$root.$emit('bv::show::modal','modal-edit-task',$event.target)">Create Task</b-button>
                            </b-row>
                        </b-col>
                    </b-row>

                    <b-row>
                        <b-col class="alert-primary border border-primary">
                            <b-row align-h="center" class="p-2">
                                <h5>Member Management</h5>
                            </b-row>
                            <b-row align-h="center" class="py-2 px-5">
                                <b-button id="btn_create_person_cat" variant="primary" @click="createPersonCat" class="btn-block">Create Member Grouping</b-button>
                            </b-row>
                            <b-row align-h="center" class="p-2 px-5">
                                <b-button id="btn_create_person" variant="primary" @click="createMember" class="btn-block">Create Member</b-button>
                            </b-row>
                        </b-col>
                    </b-row>
                </b-col>

                <b-col cols="9">
                    <PersonList />
                </b-col>

            </b-row>
        </b-container>
    </div>
</template>


<script>
    import CreateCategoryModal from "@/components/administration/CreateCategoryModal.vue";
    import EditTaskModal from '@/components/administration/EditTaskModal'
    import CreatePersonModal from "../components/administration/CreatePersonModal";
    import PersonList from "../components/administration/PersonList";

    export default {
        name: "Administration",
        components: {CreateCategoryModal,EditTaskModal, CreatePersonModal, PersonList},
        data: function() {
            return{
                categoryType: { val: null }
            }
        },
        methods: {
            createTaskCat: function(){
                this.categoryType = {val:2};
                this.$bvModal.show('modal-create-category');
            },
            createPersonCat: function(){
                this.categoryType = {val:1};
                this.$bvModal.show('modal-create-category');
            },
            createMember: function () {
                this.$bvModal.show('modal-create-member');
            }
        }
    }
</script>

<style scoped>
    .grouping-column{
        max-width: 350px;
    }
</style>
