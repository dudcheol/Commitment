<template>
    <v-app id="inspire" class="nav">
        <v-main>
            <v-container>
                <v-row :justify="dynamicJustify">
                    <v-col
                        cols="18"
                        md="12"
                    >
                        <div class="top">
                            <div class="profile">
                                <ProfileInfo />
                            </div>
                        </div>
                    </v-col>   
                    <v-col
                            cols="18"
                            md="12"
                        >
                        <div class="tabs">
                            <v-tabs justify-space-between :width="width">
                                <v-tab>Tab 1</v-tab>
                                <v-tab>Tab 2</v-tab>
                                <v-tab>Tab 3</v-tab>
                            </v-tabs> 
                        </div>
                        <v-divider></v-divider>   
                    </v-col>
                </v-row>

                <v-row :justify="dynamicJustify"  class="px-3 px-lg-16 px-md-8 px-xl-16">
                    <v-col class="mainslot" cols="12" md="6">
                        <div class="mapnselect">
                            <div class="radios">
                                <vs-radio v-model="picked" val="1" class="radiocontent">
                                    전국
                                </vs-radio>
                                <vs-radio success v-model="picked" val="2" class="radiocontent">
                                    서울
                                </vs-radio>
                                <vs-radio danger v-model="picked" val="3" class="radiocontent">
                                    부산
                                </vs-radio>
                                <vs-radio warn v-model="picked" val="4" class="radiocontent">
                                    경기
                                </vs-radio>
                                <vs-radio dark v-model="picked" val="5" class="radiocontent">
                                    강원
                                </vs-radio>
                                <vs-radio color="#7d33ff" v-model="picked" val="6" class="radiocontent">
                                    울산
                                </vs-radio>
                                <vs-radio color="rgb(59,222,200)" v-model="picked" val="7" class="radiocontent">
                                    광주
                                </vs-radio>
                            </div>
                            <div class="middle1">
                                <div v-if="picked==1">
                                    <MapNational />
                                </div>
                                <div v-else-if="picked==2">
                                    <MapSeoul />
                                </div>
                                <div v-else-if="picked==3">
                                    <MapBusan />
                                </div>
                                <div v-else-if="picked==4">
                                    <MapGyeonggi />
                                </div>
                                <div v-else-if="picked==5">
                                    <MapGangwon />
                                </div>
                                <div v-else-if="picked==6">
                                    <MapUlsan />
                                </div>
                                <div v-else-if="picked==7">
                                    <MapGwangju />
                                </div>
                            </div>
                        </div>
                    </v-col>
                    <v-col
                        cols="9"
                        md="6"
                    >
                        <div class="middle2" :width="width">
                            <BoardList />
                        </div>
                    </v-col>
                </v-row>
                </v-container>
                <div class="bottom2">
                  
                </div>
            </v-main>
        </v-app>
    </template>

<script>
import ProfileInfo from '../../components/index/mypage/ProfileInfo'
import BoardList from '../../components/index/mypage/BoardList'
import MapNational from '../../components/common/map/MapNational'
import MapSeoul from '../../components/common/map/MapSeoul'
import MapBusan from '../../components/common/map/MapBusan'
import MapGyeonggi from '../../components/common/map/MapGyeonggi'
import MapGangwon from '../../components/common/map/MapGangwon'
import MapUlsan from '../../components/common/map/MapUlsan'
import MapGwangju from '../../components/common/map/MapGwangju'

export default {
  name: 'MyPage',
  components: {
        ProfileInfo,
        BoardList,
        MapNational,
        MapSeoul,
        MapBusan,
        MapGyeonggi,
        MapGangwon,
        MapUlsan,
        MapGwangju,

    },
    data: () => ({
                benched: 100,
                items: [
                    'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
                ], 
                times:[
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                    { title: 'nickname', content: 'hellohellohellohellohellohello' },
                 ],
                colors: [
                'indigo',
                'warning',
                'pink darken-2',
                'red lighten-1',
                'deep-purple accent-4',
                ],
                value1: 1,
                picked: 1,
            }),
    computed: {
        items () {
            return Array.from({ length: this.length }, (k, v) => v + 1)
        },
        length () {
            return 7000
        },
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

};
</script>

<style scoped>
    .top{
        align-content: center;
    }
    .tabs{

        align-items: center;
        text-align: center;
        justify-self: center;
        background-color: lavender;
    }
    .radios{
        display: flex;
    }
    .radiocontent{
        white-space:nowrap;
    }
    .middle{
        align-content: center;
        display: flex;

    }
    .middle1 {
        text-align: right;
        margin-top:3%;
    }

    .middle2 {
        margin-bottom:5%;
        text-align: left;
        background-color: lawngreen;
    }
    
    .bottom {
        height:30%;
        width: 60%;
        margin-top:30px;
        margin-left: 20%;

    }
    .navbar_right {
        display: flex;
        width: min-content;
    }
    .search {
        display: flex;
        width: min-content;
    }
    .v-toolbar__content {
        display: flex;
        justify-content: space-between;
    }
    .v-tab {
        width: 20px;

    }
    .v-list {
        height: 300px;
        overflow-y: auto;
    }   
</style>
