<template>
    <v-app id="inspire">

        <v-main>
            <v-container>
                <!-- 카드 -->
                <div class="top">
                    <v-card class="mx-auto" max-width="1000" tile="tile">
                        <!-- <v-img
                            height="200"
                            src="https://travelblog.expedia.co.kr/wp-content/uploads/2016/12/08.jpg"></v-img> -->
                        <v-row style="margin:2.5%;position:absolute; top: 200px">
                            <v-list-item>
                                <v-list-item-avatar size="200">
                                    <img src="https://nooks-list.com/img/rct.png" alt="picture"></v-list-item-avatar>
                                    <v-list-item-content>
                                        <v-list-item-title class="nickname" style="margin-top:20px;">닉네임</v-list-item-title>
                                        <v-list-item-subtitle>26 | 여성</v-list-item-subtitle>
                                        <v-list-item-subtitle>안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-row>
                        </v-card>
                        <!-- 카드 끝 -->
                        <v-row justify="space-around">
                            <v-icon @click="model--">
                                mdi-minus
                            </v-icon>
                            {{ model }}
                            <v-icon @click="model++">
                                mdi-plus
                            </v-icon>
                        </v-row>
                </div><!-- top끝 -->
                <div class="middle1">a

                  
                </div>
                <div class="middle1">b</div>
                <div class="bottom">c</div>
                </v-container>

            </v-main>
        </v-app>
    </template>

    <script>
        export default {
            data: () => ({
                items: [
                    'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'
                ],
                value1: 1
            })
        }
    </script>

    <style >
        .top {
            grid-area: top;
            float: none;
        }
        .middle1 {
            grid-area: middle;
            float: none;
        }
        .middle2 {
            grid-area: middle;
            float: left;
        }
        .bottom {
            grid-area: bottom;
            float: left;
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

        v-tab {
            width: 20px;

        }

    </style>