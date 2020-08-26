<template>
    <div id="detail_rep_2" v-if="showReport">
        <b-container>
            <b-row class="pb-4">
                <b-col>
                    <h2 id="header_report_2">Workload Breakdown Report</h2>
                </b-col>
            </b-row>
            <b-row class="pb-4">
                <b-col>
                    <b-row class="p-2"><b id="lbl_name" class="pr-1">Name: </b><span id="txt_name">{{tokenFirstName+" "+tokenLastName}}</span></b-row>
                    <b-row class="p-2"><b id="lbl_program" class="pr-1">Program: </b><span id="txt_program">{{tokenCategoryName}}</span></b-row>
                    <b-row class="p-2"><b id="lbl_status" class="pr-1">Employment Status: </b><span id="txt_status">{{tokenEmploymentStatus}}</span></b-row>
                    <b-row class="p-2"><b id="lbl_hours" class="pr-1">Weekly Scheduled Hours: </b><span id="txt_hours">{{tokenScheduledHours}}</span></b-row>
                </b-col>
                <b-row>
                    <b-col>
                        <label for="start_date" class="float-left font-weight-bold">Date starting:</label>
                        <b-datepicker
                                id="start_date"
                                v-model="startDate"
                                placeholder="Please select date"
                                :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                                locale="en"
                                :max="endDate === null ? maxDate : endDate "
                                @context="onContext(startDate,endDate)">
                        </b-datepicker>
                    </b-col>
                    <b-col v-if="startDate===null" id="txt_select_start" class="font-weight-bold pt-1">Please select a starting date</b-col>
                    <b-col v-else>
                        <label for="end_date" class="float-left font-weight-bold">Date ending:</label>
                        <b-datepicker
                                id="end_date"
                                v-model="endDate"
                                placeholder="Please select date"
                                :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                                locale="en"
                                :max="maxDate"
                                :min="startDate"
                                @context="onContext(startDate,endDate)"
                                :value="endDate"
                                >
                        </b-datepicker>
                    </b-col>
                </b-row>
            </b-row>
        </b-container>
        <h3 id="header_instruction_banner" class="border border-dark rounded p-2 ml-5 mr-5" v-if="!runReport">Please Select a Date Range to view Report</h3>
        <b-table-simple id="table_report" hover outlined bordered v-if="runReport" class="ml-5 mr-5" small>
            <colgroup ><col/></colgroup>
            <colgroup><col/></colgroup>
            <colgroup><col/></colgroup>
            <b-thead head-variant="dark">
                <b-th variant="dark">Work Assignment</b-th>
                <b-th variant="dark">Work Task</b-th>
                <b-th variant="dark">Hours Worked</b-th>
            </b-thead>
            <b-tbody v-for="(cat,index) in response" :key="index">
                <b-tr>
                    <b-td class="font-weight-bold align-middle border-dark" :rowspan="cat.count > 0 ? cat.count + 1 : 1">{{cat.category}}</b-td>
                </b-tr>
                <b-tr v-for="(row,index) in cat.data" :key="index">
                    <b-td>{{row.task}}</b-td>
                    <b-td>{{row.hours}}</b-td>
                </b-tr>
            </b-tbody>
            <b-tfoot foot-variant="dark">
                <b-tr>
                    <b-td variant="dark"></b-td>
                    <b-td variant="dark" class="font-weight-bold">Total Hours Worked</b-td>
                    <b-td variant="dark" class="font-weight-bold">{{totalHours}}</b-td>
                </b-tr>
            </b-tfoot>
        </b-table-simple>
        <div v-if="runReport">
            <b-table-simple id="table_summary" hover outlined bordered class="ml-5 mr-5 mt-5">
                <colgroup><col/></colgroup>
                <colgroup><col/></colgroup>
                <colgroup><col/></colgroup>
                <b-thead head-variant="dark">
                    <b-th colspan="3">Summary for Date Range {{startDate}} to {{endDate}}</b-th>
                </b-thead>
                <b-tbody>
                    <b-tr>
                        <b-td class="font-weight-bold align-middle border-dark">Estimated Scheduled Hours: {{scheduledHours}}</b-td>
                        <b-td class="font-weight-bold align-middle border-dark">Total Hours Worked: {{totalHours}}</b-td>
                        <b-td class="font-weight-bold align-middle border-dark">Variance: {{totalHours - scheduledHours}}</b-td>
                    </b-tr>
                </b-tbody>
                <caption id="caption_summary" class="p-0 font-weight-bold small">*Estimated scheduled hours determined by (Weekly Scheduled Hours / 7)*(Number of days in date range)</caption>
            </b-table-simple>
        </div>
    </div>
</template>

<script>
    import {authComputed} from "../../store/helpers";

    export default {
        name: "detail_report_2",
        props: {
            showReport: {               //Prop from reports view
                type: Boolean,          //Gets set when button to display
                default: false          //this report is opened
            },
            currentPerson:{}
        },
        data () {
            return{
                startDate: null,        //First day to report on
                endDate: null,          //Last day to report on
                response: [],           //Unprocessed GET
                totalHours: 0,
                scheduledHours: 0,
                runReport: false,       //Determines if report should show
                maxDate: new Date()     //The latest report than can be selected
            }
        },
        methods: {
            onContext(startDate,endDate){
                //Verify that start and end dates are both populated before making the GET request
                if(startDate !== null && startDate !== "" && endDate!== null && endDate !== "")
                {
                    //this.endDate.setDate(endDate.getDate() + 1);
                    this.getReport();
                }
            },
            getReport(){
                this.axios({
                    method:"GET",
                    url: this.apiUrlBase + '/detail_report_2',
                    header: {'Accept': 'application/ld+json'},
                    //Pass in the startdate and enddate as parameters for the date range
                    //of the report
                    params: {'start': this.startDate, 'end': this.endDate}
                })
                    .then((response) => {
                        this.response = response.data;
                        //Process the data passed in
                        this.processResponse();
                        //Set the flag that the report has run to display the table
                        this.runReport = true;
                    })
                    .catch(error =>{
                        this.response=[];
                    });
            },
            processResponse(){
                //The first index of the array is always 'totalHours'
                this.totalHours = this.response[0];
                //The second index is always 'scheduledHours'
                this.scheduledHours = this.response[1];
                //Remove the first two indexes so they don't interfere with the loop
                this.response.splice(0,2);
                //An array to track the indexes that are empty, and can be removed
                let indexesToRemove = [];
                for(let i=0;i<this.response.length;i++)
                {
                    //If the index contains no items, add its index
                    //to the array for removal
                    if(this.response[i].count === 0)
                    {
                        indexesToRemove.push(i);
                    }
                }
                //Loop through the removal array and splice out the empty indexes
                for(let i=indexesToRemove.length-1; i>=0;i--)
                {
                    this.response.splice(indexesToRemove[i],1);
                }
            }
         },
        watch:{
            //If showReport changes to false (if a different report or dashboard is displayed)
            //then reset the report variables
            showReport:{
                deep: true,
                handler: function(newVal){
                    if(!newVal)
                    {
                        this.startDate = null;
                        this.endDate = null;
                        this.runReport = false;
                        this.totalHours = 0;
                    }
              }
          }
        },
        computed:{
            ...authComputed,
        },
        mounted()
        {
        }
    }
</script>

<style scoped>
    #detail_rep_2{
        max-width: 75%;
    }
</style>
