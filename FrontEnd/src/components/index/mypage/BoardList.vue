<template>

<div :width="width">
    <v-card
    elevation="0"

    >
    <v-virtual-scroll
        :bench="benched"
        :items="scrolls"
        height="640"
        item-height="64"
        width="100%"
    >
        <template v-slot:default="{ item }">
        <v-list-item :key="item">
            <vs-button
                block
                border
            >
                <v-list-item-content>
                <v-list-item-title>
                    <strong>{{ item }}</strong> 커밋
                </v-list-item-title>
                </v-list-item-content>
                <v-list-item-action>
                <v-icon small>
                    mdi-camera-enhance-outline
                </v-icon>
                </v-list-item-action>
            </vs-button>

        </v-list-item>


        </template>
    </v-virtual-scroll>
    </v-card>
</div>
</template>
<script>
export default {
    name: 'BoardList',
    data: () => ({
            benched: 100,
            scrolls: [
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'
            ],
            colors: [
            'indigo',
            'warning',
            'pink darken-2',
            'red lighten-1',
            'deep-purple accent-4',
            ],
            value1: 1,
            limit: 0,//무한스크롤
        }),
    computed: {
        width () {
            switch (this.$vuetify.breakpoint.name) {
            case 'xs': return '500px'
            case 'sm': return '500px';
            case 'md': return '500px';
            case 'lg': return '700px';
            case 'xl': return '800px';
            }
            return 700
        },
    },

    
}
</script>


<style scoped>
    .scroll{
        margin-top: 5%;
    }
    
</style>