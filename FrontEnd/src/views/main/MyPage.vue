<template>
    <v-app id="inspire" class="nav">
        <v-main>
            <v-container>
                    <!-- top -->
                    <div class="top">
                        <div class="top1">
                        <v-list-item-avatar size="180">
                                    <img src="https://ai.esmplus.com/hr120890/img/1710/171026/kakao_embo1000.jpg" alt="picture">
                        </v-list-item-avatar>
                        </div>
                        <div class="top2">
                        <div class="nickname">닉네임</div>
                            <div class="agengender">26 | 여성</div>
                            <br>
                            <div class="intro">안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</div>
                            <div class="detail">
                                <div class="detail_left">
                                    <div class="commit">
                                        <vs-button
                                            uploade
                                            color="#7d33ff"
                                            border
                                            :active="active == 2"
                                            @click="active = 2"
                                        >
                                            <i class="bx bxs-magic-wand"> 커밋수<span class="numbers">30</span></i> 
                                        </vs-button>
                                    </div> 
                                    <div class="follower">
                                        <vs-button
                                            danger
                                            border
                                            :active="active == 2"
                                            @click="active = 2"
                                        >
                                            <i class="bx bxs-heart"> 팔로워<span class="numbers">30</span></i> 
                                        </vs-button>
                                    </div>
                                </div>

                                <div class="editProfile">
                                    <vs-button
                                        success
                                        border
                                        :active="active == 2"
                                        @click="active = 2"
                                    >
                                        <i class="bx bxs-magic-wand">수정</i> 
                                    </vs-button>
                                </div>
                            </div>
                            
                        </div>
                        <div class="top3">
                            <div class="badge">
                                <div class="badgeImg">
                                    <v-list-item-avatar size="100">
                                    <img
                                        src="https://cdn4.iconfinder.com/data/icons/badges-9/66/31-512.png"
                                        alt="picture">
                                    </v-list-item-avatar>
                                </div>
                                <div class="badgename">
                                    <span>뱃지명</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- middle -->
                    <div class="middle">
                      <div class="middle1">
                          <!-- 여기 v-img를 지우고 커밋지도 -->
                            <v-img
                            lazy-src="https://picsum.photos/id/11/10/6"
                            height=100%
                            width=100%
                            class="justify-end"
                            src="./korea.jpg"
                            ></v-img>
                      </div>
                      <!-- 오른쪽 -->
                      <div class="middle2">
                            <div class="scroll">
                                <v-card
                                max-width="400"
                                class="mx-auto"
                                >
                                <v-virtual-scroll
                                    :bench="benched"
                                    :items="scrolls"
                                    height="500"
                                    item-height="64"
                                >
                                    <template v-slot:default="{ item }">
                                    <v-list-item :key="item">
                                        <v-list-item-action>
                                        <v-btn
                                            fab
                                            small
                                            depressed
                                            color="primary"
                                        >
                                            {{ item }}
                                        </v-btn>
                                        </v-list-item-action>

                                        <v-list-item-content>
                                        <v-list-item-title>
                                            User Database Record <strong>ID {{ item }}</strong>
                                        </v-list-item-title>
                                        </v-list-item-content>

                                        <v-list-item-action>
                                        <v-icon small>
                                            mdi-open-in-new
                                        </v-icon>
                                        </v-list-item-action>
                                    </v-list-item>

                                    <v-divider></v-divider>
                                    </template>
                                </v-virtual-scroll>
                                </v-card>
                            </div>
                      </div>
                    </div>
                    <!-- bottom -->
                    <div class="bottom">
                        <vue-horizontal-timeline :items="times" />
                    </div>
                    <div class="bottom2">

                    </div>
                </v-container>

            </v-main>
        </v-app>
    </template>

<script>

export default {
  name: 'MyPage',
  data: () => ({
                benched: 100,
                items: [
                    'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
                ], 
                times:[
                    ['a','b'],['c','d'],['e','f'],['g','h'],['a','b'],['c','d'],['e','f'],['g','h'],
                ],
                scrolls: [
                    'aaa', 'bbb', 'ccc', 'ddd', 'eee', 'fff', 'ggg', 'hhh', 'iii', 'jjj', 'kkk', 'lll', 'mmm', 'nnn'
                ],
                colors: [
                'indigo',
                'warning',
                'pink darken-2',
                'red lighten-1',
                'deep-purple accent-4',
                ],
                slides: [
                'First',
                'Second',
                'Third',
                'Fourth',
                'Fifth',
                ],
                value1: 1,
            }),
            computed: {
                items () {
                    return Array.from({ length: this.length }, (k, v) => v + 1)
                },
                length () {
                    return 7000
                },
            },

};
</script>

<style scoped>
        .badge{
            width: 25%;
            background-color: white;
        }
        .badgeImg{
            text-align: center;
            margin-left: 10px;
            background-color: white;
        }
        .badgename{
            text-align: center;
            background-color: white;
            font-weight: bold;
            color:rgb(119, 119, 119)
        }
        .scroll{
            margin-top: 40px;
        }
        .intro{
            margin-bottom:5px;
            margin-bottom: 15px;
        }
        .detail{
            display: flex;
            justify-content:space-between;
            background-color: white;
        }
        .detail_left{
            display: flex;
        }
        .commit{
            color:white;
        }
        .follower{
            margin-left:10px;
            color:white;
  
        }
        .editProfile{
            right:100px;
        }
        .numbers{
            margin-left: 13px;
            font-weight: bold;
            color:grey
        }
      .nav{
        margin-top:50px;
      }
        .top {
            display: flex;
            margin-left:50px;
            margin-right:50px;
            margin-bottom:10px;
            background-color: white;
            height:20%;
        }
        .top1 {
            width:30%;
            margin-right:25px;
            background-color: white;
            text-align: right;
        }
        .top2 {
            width:40%;
            margin-top: 30px;
            background-color: white;
        }
        .top3 {
            width:30%;
            vertical-align: middle;
            background-color: white;
            margin-top: 30px;
            text-align: left;
            display: inline;
        }
        .nickname{
            font-size: 20px;
            font-weight: bold;
        }
        .agengender{
            font-weight: bold;
            color:grey;
        }
        .middle{
          display: flex;
          height:60%;
          width: 70%;
          margin-left:15%;
          background-color: white;
        }
        .middle1 {
            width:40%;
            background-color: white;
            margin-right: 10px;
        }
        .middle2 {
            width:60%;
            background-color: white;
        }
        
        .bottom {
            height:30%;
            width: 60%;
            margin-top:30px;
            margin-left: 20%;
            background-color: white;
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
