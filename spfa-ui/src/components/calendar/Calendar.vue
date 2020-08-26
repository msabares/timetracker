<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <!--box that hold all the components -->
    <div id="wrapper" >
        <div>
            <h1 v-if="this.noTSR ">No Records Available</h1>
        </div>

        <!--Box that holds the TaskList component-->
        <div id="taskListBox">
            <TaskList :taskList="taskList"/>
        </div>

        <!-- Modal for viewing and editing Timesheet Records (and their details)-->
        <!-- Sends the taskList, and selected record as props -->
        <!-- Delete record and save record are events from the modal to ... delete and save the selected record -->
        <EditRecordModal :current-record="confirmDetails" :tasks="taskList" @delete="removeRecord" @save="editRecordDetails" />


        <!--Calendar Components-->
        <div id="calendarBox">
            <!--default view is the view presented when page loaded
            Headers chooses the order of the components in the calendar header
            buttonText override button labels
            event is the where the calendar get the records from-->
            <FullCalendar
                    ref="calendar"
                    defaultView="timeGridWeek"
                    :plugins="calendarPlugins"
                    :header="{
                        left:'prev,next today',
                        center: 'title',
                        right: 'timeGridDay,timeGridWeek,dayGridMonth'
                    }"
                    :buttonText="{
                        today:  'Today',
                        month: 'Month',
                        week: 'Week',
                        day: 'Day'
                    }"
                    :events="computedRecords"
                    :editable="!isBusy"
                    @eventReceive="preventMultDays"
                    time-zone="true"
                    @eventResize="preventMultDays"
                    @eventDrop="preventMultDays"
                    scroll-time="07:00:00"
                    @eventRender="eventRender"
            />
            <div id="error">{{error}}</div>
            <!--citing the calendar source -->
            <div>Calendar from <a href="https://fullcalendar.io/license">Fullcalendar.io</a> under <a href="https://github.com/fullcalendar/fullcalendar/blob/master/LICENSE.txt">MIT License</a></div>

        </div>
    </div>
</template>


<script>
    import FullCalendar from '@fullcalendar/vue'
    import timeGridPlugin from '@fullcalendar/timegrid'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import TaskList from './TaskList'
    import interactionPlugin,{Draggable} from '@fullcalendar/interaction'
    import EditRecordModal from "./EditRecordModal";
    import {authComputed} from "../../store/helpers";
    export default {
        components: {
            EditRecordModal, // make the <EditRecordModal> tag available
            FullCalendar, // make the <FullCalendar> tag available
            TaskList // make the <TaskList> tag available
        },
        data() {
            return {
                calendarPlugins: [ timeGridPlugin , dayGridPlugin,interactionPlugin],
                rawRecords:[], //array to keep the timesheetrecords
                newRecord:Object.assign({},this.record),
                noTSR:false, //TSR = TimeSheetRecord
                loading: true,
                currentPerson: null, //Current user using App
                url:this.apiUrlBase+'/timesheet_records',
                status:{code:0},
                error:"",
                rawTasks:[], //Array that holds unprocessed tasks from API
                confirmDetails: {recordID:null, title:'', start: '', end:'', siteLocation: '',comment:'', task:''}, //hold record details for detail modal
                confirmRecord: {recordID:null,title:'',start:'',end:''}, //place holder for timesheetrecord objects
                popoverShow: true, //boolean for pop over
                userID:this.$store.getters.tokenPerson
            }
        },
        methods: {
            //Get all of the timesheet records for current person
            getRecords() {

                this.axios({
                    method: 'GET',
                    url: this.apiUrlBase + '/timesheet_records?person.personID=' + this.userID,
                    headers: {'Accept': 'application/ld+json'}
                })
                    .then((response) => {

                        //store them in rawRecords array so they can be processed
                        this.rawRecords = response.data['hydra:member'];
                        //Prevent error messages by checking if an array
                        //Then check if array has content
                        if (Array.isArray(this.rawRecords) && this.rawRecords.length <= 0) {
                            //Set display that user contains no records
                            this.noTSR = true;
                        }
                        this.loading = false;
                    })
                    .catch(e => {
                        this.error = e.message
                    })
            },
            //post the event to database
            postRecord(event) {
                //assign the event properties to newRecord
                this.newRecord.start = event.start.toUTCString();
                this.newRecord.end = event.end.toUTCString();
                this.newRecord.siteLocation = 'on-site';
                this.newRecord.task = event.extendedProps.task;
                this.newRecord.person = '/api/people/' + this.userID;
                //post the newRecord to database
                this.axios({
                    url: this.url,
                    method: "POST",
                    headers: {
                        Accept: "application/ld+json"
                    },
                    data: this.newRecord
                }).then(response => {
                        //get records from database, remove the cache event when get response.
                        this.getRecords(this.userID);
                        event.remove()
                    },
                ).catch(e => {
                    this.error = e.message
                }).finally(
                    //reset the status code to be 0.
                    () => {
                        this.status.code = 0
                    }
                );
            },
            //edit the event record in database
            putRecord() {
                this.axios({
                    //update the newRecord object to api_platform with all the related properties which come from event object.
                    url: this.url + '/' + this.newRecord.recordID,
                    method: "PUT",
                    headers: {
                        Accept: "application/ld+json"
                    },
                    data: this.newRecord
                }).then()
                    .catch(e => {
                        this.error = e.message
                    }).finally(() => {
                    this.status.code = 0
                })
            },
            //Method called from EditRecordModal handler
            editRecordDetails(record) {
                //Transfer the values from the modal's passed in record
                //to the Calendar newRecord to PUT
                this.newRecord.start = record.start;
                this.newRecord.end = record.end;
                this.newRecord.recordID = record.recordID;
                this.newRecord.task = '/api/tasks/' + record.task;
                this.newRecord.siteLocation = record.siteLocation;
                this.newRecord.comment = record.comment;
                this.newRecord.person = '/api/people/' + this.userID;
                //Call a PUT request
                this.putRecord();
                //Call the updated record
                this.updateRecord(this.newRecord.recordID);
                //Hide the detail modal
                this.$bvModal.hide('modal-details');
            },
            //This method handles the drag event on existing records
            editRecord(event) {
                //Transfer the record from the drag event to
                //the calendar record
                this.newRecord.start = event.start.toUTCString();
                this.newRecord.end = event.end.toUTCString();
                this.newRecord.recordID = event.id;
                this.newRecord.task = '/api/tasks/' + event.extendedProps.task;
                this.newRecord.siteLocation = event.extendedProps.siteLocation;
                this.newRecord.comment = event.extendedProps.comment;
                this.newRecord.person = '/api/people/' + this.userID;
                //Call a PUT request with updated record
                this.putRecord();
            },
            //prevent the event end time spanning to another day.
            preventMultDays(info) {

                //set the status code to be -1 to indicate we are waiting for API response
                this.status.code = -1;
                //if the end time is not as the same day as start time, the event end time will revert to
                //original position
                if (info.event.start.getDate() - info.event.end.getDate()) {
                    info.revert();
                    this.status.code = 0
                }
                //else if the event id is null, we will post the event. otherwise we will put the event.
                else {
                    if (!info.event.id) {
                        this.postRecord(info.event);
                    } else {
                        clearTimeout(this.timeOut);
                        //debounce the drag end time event
                        this.timeOut = setTimeout(() => this.editRecord(info.event), 1000);
                    }
                }
            },

            //Get all the tasks to be passed into the TaskList
            //and the EditRecordModal
            getTasks: function () {
                this.loading = true;

                this.axios({            //Axios get tasks
                    method: "GET",
                    url: this.apiUrlBase + '/tasks',
                    header: {'Accept': 'application/ld+json'}
                })
                    .then((response) => {
                        this.rawTasks = response.data['hydra:member']; //stores axiosresult with our task objects
                        this.loading = false;
                    })
                    .catch((errors) => {

                    });
            },
            //Helper to sort the task list
            compare: function (a, b) {
                return a.title.toLowerCase().localeCompare(b.title.toLowerCase())
            },
            //click event for timesheetrecord event
            eventRender(info) {
                info.el.onclick = () => {
                    this.onDetailsShown(info)
                };
            },
            //gets information for the modal
            onDetailsShown(info) {
                //open the detail modal
                this.$bvModal.show('modal-details');
                //get the time sheet record id
                let temprec = this.computedRecords.find(x => x.id === parseInt(info.event.id));
                //populates the confirmDetails for detail modal
                this.confirmDetails = {
                    recordID: info.event.id,
                    title: info.event.title,
                    start: info.event.start.toUTCString(),
                    task: info.event.extendedProps.task,
                    end: info.event.end.toUTCString(),
                    siteLocation: temprec.siteLocation,
                    comment: temprec.comment
                };
            },
            //When a record is succesfully removed, remove it from rawRecords
            //so it disappears from Calendar
            removeRecord(recordID) {
                let index = this.rawRecords.findIndex(x => x.recordID === parseInt(recordID));
                //Splice out the record from the array
                this.rawRecords.splice(index, 1);
            },
            //When a record gets updated from the modal, call a GET
            //request on that record to update it to the rawRecord array
            async updateRecord(recordID) {
                await new Promise(r => setTimeout(r, 250));
                this.removeRecord(recordID);
                this.axios({
                    method: 'GET',
                    url: this.apiUrlBase + '/timesheet_records/' + recordID,
                    headers: {'Accept': 'application/json'}
                })
                    .then((response) => {
                        this.rawRecords.push(response.data);
                    })
            }
        },
        computed: {
            //For token methods from authentication system
            ...authComputed,

            //Dynamically load and process records from raw records
            computedRecords: function () {
                let returnRecords = [];
                //process each records
                if (this.rawRecords)
                {
                    this.rawRecords.forEach((Array) => {
                        let id = Array['recordID'];
                        let title = Array['task']['title'];
                        let start = Array['start'];
                        let end = Array['end'];
                        let siteLocation = Array['siteLocation'];
                        let comment = Array['comment'];
                        let extendedProps = {
                            siteLocation: Array['siteLocation'],
                            comment: Array['comment'],
                            task: Array['task']['taskID']
                        };
                        let thisRecord = {
                            "id": id,
                            "title": title,
                            "start": start,
                            "end": end,
                            "siteLocation": siteLocation,
                            "comment": comment,
                            'extendedProps': extendedProps
                        };
                        returnRecords.push(thisRecord);
                    }); //End forEach
                }
                //Return the records which gets used by the Calendar component
                return returnRecords;
            },
            /**
             * Sorts rawTasks
             * @returns taskArray sorted
             */
            taskList: function() {
                return this.rawTasks.slice(0).sort(this.compare);
            },
            //if the stats code is not 0, means we are still waiting for API response, the calendar will be uneditable
            isBusy:function () {
                return this.status.code!==0
            }
        },
        mounted(){
            //query api when page loads
            this.getRecords();
            this.getTasks();
            //set up the draggable element
            let containerEl=document.getElementById('taskList');
            new Draggable(containerEl,{
                itemSelector: '.list-group-item',
                eventData: function(eventEl) {
                    return {
                        //set up the event property.
                        title: eventEl.innerText,
                        duration:'00:30',
                        extendedProps:{
                            task: "/api/tasks/"+eventEl.getAttribute("data-id")
                        }
                    };
                }
            })

        }
    }
</script>

<style lang='scss'>

    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/timegrid/main.css';

    //this is the container for everything
    #wrapper
    {
        overflow: hidden;
        margin-left: auto;
        margin-right: auto;
    }
    //this is a container for tasklist
    #taskListBox
    {
        width: 20%;
        float:left;
        padding: 10px;
    }
    //this is the container for the calendar
    #calendarBox
    {
        width: 65%;
        float:left;
        padding: 10px;
    }

    //the div for title
    //using break-word. Otherwise the long title passes its box and also passes inside the modal
    .fc-title{
        word-wrap: break-word;
    }

    //using break work for long text inside the modal
    #modal-details{
        word-break: break-all;
    }

    #commentArea{
        height: 70px ;
        max-height: 70px;
        overflow-y: auto;
    }
    .fc-time-grid-event{
        //SPFA light blue
        background-color: rgb(106,176,228);
    }
</style>
