<template>
    <div id="personList">
        <b-table id="person-table"
                 ref="table"
                 :show-empty="true"
                 :busy="isLoading"
                 size="sm"
                 empty-text="No members to display"
                 fixed striped
                 outlined:true
                 headVariant="dark"
                 foot-variant:dark
                 :fields="fields"
                 no-provider-sorting
                 no-provider-paging
                 sort-by="lastName"
                 :per-page="perPage"
                 :current-page="currentPage"
                 :items="peopleList">

            <template v-slot:table-busy>
                <div class="text-center">
                    <b-spinner  style="width: 5em; height: 5em" variant="primary" id="load_spinner" ></b-spinner>
                </div>
            </template>

            <template v-slot:head(lastName)="data">
                <span class="ml-3">{{data.label}}</span>
            </template>

            <template v-slot:cell(lastName)="row" >
                <span class="ml-3">{{row.item.lastName.substr(0,11)}}, {{row.item.firstName.substr(0,11)}}</span>
            </template>

        </b-table>
        <b-pagination align="center" aria-controls="person-table" :per-page="perPage" :total-rows="totalRows"  v-model="currentPage">

        </b-pagination>
    </div>
</template>

<script>
    export default {
        name: "PersonList",
        data: function () {
            return {
                isLoading:false,
                peopleList:this.getPeople,
                //departmentList will have a placeholder value to get rid of startup error, will be cleared later
                departmentList: [],
                totalRows : 0,
                fields: [
                    {
                        key: 'lastName',
                        label: 'Full Name',
                        sortable: true,
                    },
                    {
                        key: 'email',
                        label: 'Email',
                    },
                    {
                        key: 'category',
                        label: 'Program',
                        formatter: value => {
                            return this.departmentList.find(item => item.iri === value['@id']).name
                        }
                    }
                ],
                currentPage: 1,
                perPage: 10,

            }
        },
        methods: {
            async getPeople() {
                this.isLoading = true;
                return this.axios({
                    method:"GET",
                    url: this.apiUrlBase+'/people',
                    header:{'Accept':'application/ld+json'}
                }).then((response) => {
                    this.getDepartment();
                    this.totalRows = response.data['hydra:member'].length;
                    return response.data['hydra:member'] || [];
                }).finally( ()=> {
                    this.isLoading=false;
                })
            },
            //The way this method is being used in incredibly
            getDepartment(){
                this.axios.get(this.apiUrlBase+'/categories?categoryType=1&parentCategory.categoryName=Department')
                    .then((response) => {
                        response.data['hydra:member'].forEach(element => {
                            this.departmentList.push({
                                iri: element['@id'],
                                name: element['categoryName']
                            })
                        });
                    })
            }
        },
        mounted() {
            this.getDepartment();
        }
    }
</script>

<style scoped>
    #person-table{
        text-align: left;

    }
    tr{
        white-space: nowrap;
        overflow: auto;
        background-color: aqua;
    }
</style>