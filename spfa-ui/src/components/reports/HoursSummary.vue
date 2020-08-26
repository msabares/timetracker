<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">

    <div v-if="showDash">
        <span id="header">
            <b-button-group >
                <b-button id="btn-prev-week" class="spfa-dark" @click="subtractWeek" :disabled="customTimeframe">
                    <b-icon icon="triangle-fill" rotate="-90" scale="1" shift-v="-1" shift-h="-1"></b-icon>
                </b-button>

                <b-button id="btn-next-week" class="spfa-dark" @click="addWeek" :disabled="viewingThisWeek || customTimeframe">
                    <b-icon icon="triangle-fill" rotate="90" scale="1" shift-v="-1" shift-h="-1"></b-icon>
                </b-button>
            </b-button-group>

            <span class="mt-2" style="width: 100%"><h3>{{weekTitle}}</h3></span>

            <b-button class="spfa-dark" style="width:40%" @click="enableCustomRange()" v-if="!customTimeframe">
                    Custom Dates
                </b-button>
            <b-button class="spfa-dark" style="width:40%" @click="resetWeek" v-if="customTimeframe">
                    Current Week
            </b-button>
        </span>

        <span id="pickers" >

            <label for="startDate" class="ml-2 mt-3" v-if="customTimeframe"><b>Start</b></label>
            <b-form-datepicker
                    id="startDate"
                    size="sm"
                    :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                    locale="en"
                    class="text-center mx-3 "
                    style="float: left"
                    :value-as-date="true"
                    v-model="pickerStart"
                    :max="pickerMax"
                    :state="dateError"
                    @context="onPickerUpdate(rangeStartDate,rangeEndDate)"
                    @input="checkDateError=true"
                    v-if="customTimeframe"
            >
            </b-form-datepicker>

            <label for="endDate" class="ml-2 mt-3" v-if="customTimeframe"><b>End</b></label>
            <b-form-datepicker
                    id="endDate"
                    size="sm"
                    :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                    locale="en"
                    class="text-center mx-3"
                    style="float: left"
                    :value-as-date="true"
                    v-model="pickerEnd"
                    :max="pickerMax"
                    :state="dateError"
                    @context="onPickerUpdate(rangeStartDate,rangeEndDate)"
                    @input="checkDateError=true"
                    v-if="customTimeframe"
            >
            </b-form-datepicker>
        </span>
            <b-form-invalid-feedback :state="dateError" class="w-50" id="error_endDate">End Date must be after start date</b-form-invalid-feedback>

        <span>
            <b-table class="mt-4"
                     fixed striped
                     outlined:true
                     headVariant="dark"
                     foot-variant:dark
                     :items="taskTotals"
                     :fields="fields"
                     sort-by="task"
                     custom-foot>

                <template v-slot:table-colgroup="scope">
                    <col style="text-align: left; width: 70%;">
                </template>

                <template v-if="!loading" v-slot:custom-foot>
                    <b-td v-if="totalHours > 0" variant="info"><b>Total</b></b-td>
                    <b-td v-if="totalHours > 0" variant="info"><b>{{totalHours}}</b></b-td>
                    <b-td v-else variant="danger" colspan="2"><h4>No time entered this week.</h4></b-td>
                </template>
            </b-table>

            <div v-if="loading">
                <b-spinner class="mt-5" style="width: 5em; height: 5em"></b-spinner>
            </div>
        </span>
    </div>
</template>

<script>
    const moment = require('moment');
    import {authComputed} from "../../store/helpers";

    export default {
        name: "HoursSummary",
        props:{
            showDash: {
                type: Boolean,
                default: true
            }
        },
        data()
        {
            return {
                //Separate objects for pickers to reference/update?
                //rangeStartPicker //2020-04-05
                //rangeEndPicker   //2020-04-08
                thisWeekStartDate: {},
                thisWeekEndDate: {},
                rangeStartDate: {},
                rangeEndDate: {},
                pickerStart:{},
                pickerEnd:{},
                pickerMax:{},
                customTimeframe: false,
                recordList: [],
                checkDateError: false,
                viewingCustomRange: false,
                loading: false,
                fields: [
                    {
                        key: 'task',
                        sortable: true,
                    },
                    {
                        key: 'hours',
                        sortable: true
                    }
                ]
            }
        },
        methods: {
            //Make an axios call to get
            getRecords()
            {
                this.loading = true;
                this.recordList = [];

                this.axios({
                    method: 'GET',
                    url: this.apiUrlBase + '/timesheet_records?start[after]=' + this.rangeStartDate.toString().substr(0,33) +
                        '&end[before]=' + this.rangeEndDate.toString().substr(0,33)+'&person.personID='+this.$store.getters.tokenPerson,
                    headers: {'Accept': 'application/json'},
                })
                    .then((response) =>
                    {
                        this.recordList = response.data;
                        this.loading = false;
                    });
            },
            subtractWeek() //Navigate to the previous week
            {
                this.rangeStartDate = moment(this.rangeStartDate).subtract(1, 'weeks');
                this.rangeEndDate = moment(this.rangeEndDate).subtract(1, 'weeks');
                this.dateToPicker();
                this.getRecords();
            },

            addWeek() //Navigate to the next week
            {
                this.rangeStartDate = moment(this.rangeStartDate).add(1, 'weeks');
                this.rangeEndDate = moment(this.rangeEndDate).add(1, 'weeks');
                this.dateToPicker();
                this.getRecords();
            },
            resetWeek() //Reset week to default view of one week/this week
            {
                this.rangeStartDate = this.thisWeekStartDate;
                this.rangeEndDate = this.thisWeekEndDate;
                this.dateToPicker();
                this.getRecords();
                this.customTimeframe = false;
            },
            onPickerUpdate()
            {
                this.rangeStartDate = this.pickerStart;
                this.rangeEndDate = this.pickerEnd;
                this.getRecords();
            },
            dateToPicker()
            {
                this.pickerStart = this.rangeStartDate.toDate();
                this.pickerEnd = this.rangeEndDate.toDate();
            },
            enableCustomRange()
            {
                this.customTimeframe = true;
                this.checkDateError = false;
            }
        },
        computed: {
            taskTotals() //Calculate the totals for all tasks with records this week
            {

                let taskHours = [];
                let uniqueTasks = [...new Set(this.recordList.map(record => record.task.title))];


                for (let taskName of uniqueTasks)
                {
                    let hoursSum = 0;

                    for (let record of this.recordList)
                    {

                        if (record.task.title === taskName)
                        {
                            let startTime = moment(record.start);
                            let endTime = moment(record.end);

                            hoursSum += moment.duration(endTime.diff(startTime)).asHours();
                        }
                    }

                    if (hoursSum != Math.round(hoursSum))
                    {
                        hoursSum = parseFloat(hoursSum.toFixed(2));
                    }
                    taskHours.push({task: taskName, hours: hoursSum})
                }
                return taskHours;
            },
            totalHours()
            {
                let totalHours = 0;
                for (let task of this.taskTotals)
                {
                    totalHours += task.hours;
                }
                return totalHours;
            },

            weekTitle()
            {
                let weekTile;

                //Check if the current week starts and ends in the same month and year
                let sameMonth = moment(this.rangeEndDate).isSame(moment(this.rangeStartDate), 'month');
                let sameYear = moment(this.rangeEndDate).isSame(moment(this.rangeStartDate), 'year');
                if (sameMonth) //If the week starts and ends in the same month
                {
                    weekTile = moment(this.rangeStartDate).format('MMM D') + ' - ' + moment(this.rangeEndDate).format('D, YYYY');
                }
                else if (sameYear) //If the week does not start and end in the same month
                {
                    weekTile = moment(this.rangeStartDate).format('MMM D') + ' - ' + moment(this.rangeEndDate).format('MMM D, YYYY');
                }
                else //If the week does not start and end in the same year
                {
                    weekTile = moment(this.rangeStartDate).format('MMM D, YYYY') + ' - ' + moment(this.rangeEndDate).format('MMM D, YYYY');
                }

                return weekTile;
            },

            viewingThisWeek()
            {
                return moment(this.rangeEndDate).diff(moment(this.thisWeekEndDate)) >= 0;
            },
            dateError: function () {

                return this.checkDateError ? moment(this.rangeStartDate).diff(this.rangeEndDate) <=0  : null;
            },
            ...authComputed
        },
        mounted()
        {
            this.rangeStartDate = moment().startOf('week');
            this.rangeEndDate = moment().endOf('week');
            this.thisWeekStartDate = moment().startOf('week');
            this.thisWeekEndDate = moment().endOf('week');
            this.pickerMax = this.thisWeekEndDate.toDate();
            this.dateToPicker();
            this.getRecords();
            this.customTimeframe = false;
        }
    }
</script>

<style scoped>
    #header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    #pickers {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }


</style>