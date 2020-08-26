<template>
    <div class="w3-sidebar w3-bar-block" id="taskList">

        <ul id="itemList" v-if="taskList.length >0">
            <div class="accordion__panel accordion__panel--closed" style="transform: translate3d(0px, 0px, 0px); height: 47px; margin-top: 10px">
                <!--Loop through tasksList and pull out titles and create li with them-->
                <li  v-for="task in taskList" v-bind:key="task.id" class="list-group-item" :title="task.title"  :data-id="task.taskID">
                    {{task.title}}
                </li>
            </div>
        </ul>
        <h2 v-else-if="this.getLoading===true && this.taskList.length >0">Loading...</h2>
        <h2 v-else-if="this.getLoading===false && this.taskList.length <=0">No Tasks Available</h2>

    </div>
</template>

<script>

    export default{

        props:{
            taskList: null,
        },
        data: function () {
            return {
                loading:false, //boolean switch for database
            }
        },
        methods:{

        },
        mounted(){
        },
        computed:
            {
                /**
                 * Getter for our loading variable. had issues with page loading before database call is finished
                 * @returns loading
                 */
                getLoading:function () {
                    return this.loading;
                }
            }
    }
</script>

<style scoped>
    /*background "bubble" around tasks, move tasks apart*/
    li{
        text-align: left;
        padding-bottom: 5px;
        padding-top: 5px;
        background-color: rgb(106,176,228);
        border-radius: 10px;
        padding-left: 10px;
        margin-top: 5px;
        margin-bottom: 5px;
    }

    /*This is for limiting the length of tasks names displayed, and adding an elipses(maybe)*/
    li{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;/*Can remove this to get rid of elipsis*/
        /*If above is taken out, workds kinda just run out of bubble*/
    }

    /*Having this allows the user to hover over the task and have it*/
    /*Keeping this incase greg wants this */
    li:hover{
        white-space: normal;
        overflow: auto;
        cursor: move;
    }

    ul{height:400px; width:100%;padding-top: 10px}
    /*Change height here to set how many tasks you want visible at once*/
    ul{overflow:hidden; overflow-y:scroll;}
    #taskList, #itemList
    {
        color: black;
        text-align-all: left;
        text-decoration: none;
        list-style: none;
    }
</style>