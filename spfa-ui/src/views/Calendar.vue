<template>
    <div class="home">
        <h1>Calendar</h1>
        <div id="calendar">
            <Calendar/>
        </div>
    </div>
</template>

<script>
    import Calendar from '@/components/calendar/Calendar.vue'

    export default {
        name: 'home',
        components: {
            Calendar,
        },
        computed: {
            vueRoot()
            {
                return this.$root;
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>
