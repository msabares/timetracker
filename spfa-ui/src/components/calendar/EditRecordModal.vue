<template>
    <div>
        <!--Timesheetrecord detail modal-->
        <!--title displays task name and date -->
        <b-modal id="modal-details" :title="currentRecord.title + ' - ' + currentRecord.start.toString().substr(0,16)"
                 @shown="getTimes" no-close-on-esc hide-footer @hidden="resetModal">
            <!--Container which holds all the record details in the grid -->
            <b-container>
                <b-row>
                    <b-col class="col-2">
                        <b-row class="pt-2">
                            <label style="font-size: 0.9em">Start Time:</label> <!-- label for start time -->
                        </b-row>

                        <b-row class="pr-1 pt-sm-3">
                            <label style="font-size: 0.9em">End Time:</label> <!-- label for end time -->
                        </b-row>
                    </b-col>

                    <b-col>
                        <b-row class="p-1">
                            <!-- time box for start time-->
                            <b-form-timepicker id="startTime" v-model="startTime" :disabled="!editMode" hide-header :state="timeError" @input="checkTimeError=true"></b-form-timepicker>
                        </b-row>
                        <b-row class="p-1">
                            <!-- time box for end time -->
                            <b-form-timepicker id="endTime" v-model="endTime" :disabled="!editMode" hide-header :state="timeError" @input="checkTimeError=true"></b-form-timepicker>
                            <!-- Error message if validation error in start time and end time -->
                            <b-form-invalid-feedback id="error_endTime">End time must be later than start time</b-form-invalid-feedback>
                        </b-row>
                    </b-col>

                    <!-- radio button for site location-->
                    <b-col class="pt-1 pl-5" align-self="center">
                        <b-form-group id="site-radio" label="Location: " :disabled="!editMode">
                            <b-form-radio id="on-site" v-model="currentRecord.siteLocation" name="locationSelected" value="on-site"
                            >On-Site
                            </b-form-radio>
                            <b-form-radio id="off-site" v-model="currentRecord.siteLocation" name="locationSelected" value="off-site"
                            >Off-Site
                            </b-form-radio>
                        </b-form-group>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                        <b-row class="pr-1 pt-2">
                            <label>Task:</label> <!-- label for task -->
                        </b-row>
                    </b-col>
                    <b-col cols="10">
                        <b-row class="p-1">
                            <b-form-group >
                                <b-form-select id="task-picker" :disabled="!editMode" v-model="currentRecord.task">
                                    <b-form-select-option v-for="task in tasks" :key="task.taskID" :value="task.taskID">{{task.title}}</b-form-select-option>
                                </b-form-select>
                            </b-form-group>
                        </b-row>
                    </b-col>
                </b-row>

                <!-- textarea for comments -->
                <b-row>
                    <label>Comments:</label>
                    <b-form-textarea
                            id="commentArea"
                            v-model="currentRecord.comment"
                            placeholder=""
                            rows="3"
                            max-rows="100"
                            :disabled="!editMode"
                            maxlength="255"
                    ></b-form-textarea>
                </b-row>

                <!--  delete and edit buttons and their related events on the popover-->
                <b-row align-h="center" class="btn-block  text-center">

                    <b-button id="btn_delete" style="width: 30%" class="mt-3 ml-4" variant="danger">Delete</b-button>

                    <!-- confirm delete popover -->
                    <b-popover target="btn_delete" triggers="click" placement="top" ref="popover">
                        <template v-slot:title><b>Are you sure to delete?</b></template>
                        <b-button id="btn_yes" v-on:click="deleteRecord()" style="width: 30%; float:right"
                                  class="mt-3 mb-3 mr-3" variant="success">Yes
                        </b-button>
                        <b-button id="btn_no" v-on:click="popOverClose()" style="width: 30%; float: left"
                                  class="mt-3 mb-3 ml-3" variant="danger">No
                        </b-button>
                    </b-popover>

                    <b-button id="btn_edit" style="width: 30%" class="mt-3 ml-5" variant="success"  v-if="!editMode" @click="startEdit">Edit</b-button>
                    <b-button id="btn_save" style="width: 30%" class="mt-3 ml-5" variant="success"  v-else @click="saveRecord">Save</b-button>

                </b-row>
            </b-container>
        </b-modal>

    </div>
</template>

<script>
    const moment = require('moment');

    export default {
        name: "EditRecordModal",
        props: {
            //Passed in from the Calendar modal
            currentRecord: {
                type: Object,
                default: () => ({
                    recordID: null,
                    start: null,
                    end: null,
                    siteLocation: '',
                    comment: null,
                    task: '',
                    person: null
                })
            },
            //Passed in from the Calendar modal
            tasks: {},
        },
        data() {
            return {
                startTime: null,
                endTime: null,
                editMode: false,
                url:this.apiUrlBase+'/timesheet_records',
                checkTimeError: false
            }
        },
        methods: {
            //close the popover
            popOverClose()
            {
                //Leave edit mode
                this.stopEdit();
                this.$refs.popover.$emit('close')
            },
            //delete the timesheetrecord event and close the modal
            deleteRecord: function(){
                //Leave edit mode
                this.stopEdit();
                //Delete the current record
                this.axios.delete(this.url + '/' + this.currentRecord.recordID )
                    .then((response) => {
                        if(response.status === 204){//it checks if the delete is successful
                            //Send a delete event to the Calendar to remove the record from the Calendar
                            this.$emit('delete', this.currentRecord.recordID);
                            //Hide this modal
                            this.$bvModal.hide('modal-details');
                        }
                    })
            },
            //Method to leave edit mode and reset validation messages
            resetModal()
            {
                //Leave edit mode
                this.stopEdit();
                //Reset validation messages
                this.checkTimeError=false;
            },
            //Get the times from the records start and end DateTimes
            getTimes() {
                this.startTime = this.currentRecord.start.toString().substr(17,8);
                this.endTime = this.currentRecord.end.toString().substr(17,8);
            },
            //Start and stop edit
            //enable/disable the inputs
            startEdit() {
                this.editMode = true;
                this.checkTimeError = true;
            },
            stopEdit() {
                this.editMode = false;
            },
            saveRecord() {
                //If timeError is true (no error) ... sorry
                //Build the new DateTimes and emit an update event
                //to the calendar
                if(this.timeError)
                {
                    this.stopEdit();
                    let newStart = this.currentRecord.start.toString().substr(0,17) + this.startTime;
                    newStart += this.currentRecord.start.toString().substr(25);
                    this.currentRecord.start = newStart;

                    let newEnd = this.currentRecord.end.toString().substr(0,17) + this.endTime;
                    newEnd += this.currentRecord.end.toString().substr(25);
                    this.currentRecord.end = newEnd;

                    delete this.currentRecord.title;

                    this.$emit('save', this.currentRecord);
                }
            },
        },
        computed: {
            //Ensure startTime doesn't match or exceed endTime
            timeError: function () {
                return this.checkTimeError  ? this.startTime < this.endTime : null;
            }
        }
    }
</script>

<style scoped>
</style>