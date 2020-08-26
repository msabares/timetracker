<template>
    <div id="detail_rep_1">
        <b-modal id="modal-start-greater"  title="Error"  no-close-on-esc hide-footer>
            <p class="my-2">Start date cannot be greater than end date</p>
        </b-modal>
        <div v-if="showReport" id="app">
            <b-container>
                <b-row class="pb-4">
                    <b-col>
                        <h2>Member Stats Report</h2>
                    </b-col>
                </b-row>
                <b-row class="pb-4">
                    <b-col>
                        <b-row class="p-2"><b id="lbl_name" class="pr-1">Name:</b><span id="txt_name">{{tokenFirstName+" "+tokenLastName}}</span></b-row>
                        <b-row class="p-2"><b id="lbl_program" class="pr-1">Program:</b><span id="txt_program">{{tokenCategoryName}}</span></b-row>
                        <b-row class="p-2"><b id="lbl_status" class="pr-1">Employment Status:</b><span id="txt_status">{{tokenEmploymentStatus}}</span></b-row>
                        <b-row class="p-2"><b id="lbl_hours" class="pr-1">Weekly Scheduled Hours:</b><span id="txt_hours">{{tokenScheduledHours}}</span></b-row>
                    </b-col>
                    <b-row>
                        <b-col>
                            <label for="start" class="float-left font-weight-bold"><b>Start</b></label>
                            <b-form-datepicker
                                    id="start"
                                    size="sm"
                                    placeholder="YY/MM/DD"
                                    :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                                    locale="en"
                                    v-model="startDate"
                                    :max="maxDate"
                                    @context="onContext(startDate,endDate)">
                            </b-form-datepicker>
                            <div class="text-danger float-left" type="info" v-if="validTimes" >Please select the start date</div>
                        </b-col>
                        <b-col>
                            <label for="end" class="float-left font-weight-bold"><b>End</b></label>
                            <b-form-datepicker
                                    id="end"
                                    size="sm"
                                    placeholder="YY/MM/DD"
                                    :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit' }"
                                    locale="en"
                                    :min="startDate"
                                    :max="maxDate"
                                    v-model="endDate"
                                    @context="onContext(startDate,endDate)">
                            </b-form-datepicker>
                        </b-col>
                    </b-row>
                </b-row>
            </b-container>

            <b-table ref="TaskCatReportTable"
                     :id="tableId" :items="items" :busy="isBusy" :fields="fields" :tbody-transition-props="{name:'flip-list'}"
                     :sort-compare-options="{ numeric: true, sensitivity: 'base' }"
                     head-variant="dark"
                     no-provider-sorting no-provider-paging
                     striped hover sort-icon-left borderless
                     fixed
                     class="ml-5 mr-5">

                <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                        <b-spinner style="width: 5rem; height: 5rem;" class="align-middle"></b-spinner>
                        <strong class="d-block" >Loading...</strong>
                    </div>
                </template>
            </b-table>
        </div>
    </div>

</template>

<script>
    import {authComputed} from "../../store/helpers"
    export default {
        props: {
            showReport: {
                type: Boolean,
                default: false
            },
            tableId: {
                type: String,
                default: 'table-hoursOnTaskCat'
            },
            currentPerson:{}
        },
        data() {
            return {
                startDate: null,
                endDate: null,
                fields: [{key: 'Timesheet Date', variant: 'secondary', sortable: true},
                    {key:'Scheduled Paid Hours'},
                    {key: 'Actual Student Contact'},
                    {key: 'Actual Complementary Function'},
                    {key: 'Actual Other Assignment'},
                    {key: 'Actual Other Not Listed'},
                    {key: 'Timesheet Total'},
                    {key: 'variant hours'}
                ],
                items: [],
                isBusy: false,
                maxDate:new Date()
            }
        },
        methods: {
            onContext(startDate, endDate) {
                var startDate1 = new Date(startDate);
                var endDate1 = new Date(endDate);
                if (endDate != null && startDate1 > endDate1) {
                    this.startDate = null;
                    this.$bvModal.show("modal-start-greater");
                } else if (endDate != null && startDate1 <= endDate1 && startDate != null) {
                    this.getreport();
                }
            },
            getreport(cxt) {
                this.isBusy = true;
                this.axios.get(this.apiUrlBase + '/detail_report_1', {params: {'start': this.startDate, 'end': this.endDate}})
                    .then((response) => {
                        this.isBusy = false;
                        this.items = response.data;
                    }).catch(error => {
                    this.items = [];
                })
            },
        },
        computed: {
            validTimes: function()  {
                return ((this.startDate === null || this.startDate === "") && this.endDate !== null);
            },
            ...authComputed,
        }
    }
</script>

<style>
    #table-hoursOnTaskCat tbody tr:last-of-type{
        font-weight:bolder;
        background-color: coral;
    }
    #detail_rep_1{
        max-width: 75%;
    }
</style>