<template>
    <div>
        <b-tabs align="center" pills card vertical content-class="pb-5">
            <b-tab title="Dashboard" :showDash="showDash" @click="showDashboard" title-item-class="pb-2">
                <hours-summary class="mx-auto pr-5" id="hoursSummary" style="max-width: 550px"/>
            </b-tab>
            <b-tab id="btn_report_1" title="Member Stats Report" title-item-class="pb-2" @click="showDetailReport1">
                <detail_report_1 :showReport="showDetail1" :currentPerson="tokenPerson"></detail_report_1>
            </b-tab>
            <b-tab id="btn_report_2" title="Workload Breakdown Report" title-item-class="pb-2" @click="showDetailReport2">
                <detail_report_2 :showReport="showDetail2" :currentPerson="tokenPerson"></detail_report_2>
            </b-tab>
        </b-tabs>
    </div>
</template>

<script>
    import {authComputed} from "../store/helpers";
    import detail_report_1 from '@/components/reports/DetailReport1.vue'
    import detail_report_2 from "@/components/reports/DetailReport2.vue";
    import hoursSummary from '@/components/reports/HoursSummary.vue'

    export default {
        components: {detail_report_2, detail_report_1, hoursSummary},

        data() {
            return {
                showDetail1: false,
                showDetail2: false,
                showDash: true
            }
        },

        methods: {
            showDetailReport1() {
                this.showDetail1 = true;
                this.showDetail2 = false;
                this.showDash = false;
            },
            showDetailReport2(){
                this.showDetail2 = true;
                this.showDetail1 = false;
                this.showDash = false;
            },
            showDashboard(){
                this.showDash = true;
                this.showDetail1 = false;
                this.showDetail2 = false;
            }
        },
        computed:{
            ...authComputed,
            // vueRoot()
            // {
            //     return this.$root;
            // },
        },

    }
</script>

<style>
</style>