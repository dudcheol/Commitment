<template>

<div class="d-flex justify-center">
<!-- top -->
            <div class="top" :justify="dynamicJustify">
                <div>
                    <ProfileImgChange/>
<!--                     <div class="profileImg ">
                        <v-list-item-avatar size="150">
                            <img :src=imgSrc 
                            alt="picture"
                            @click="showModal()"
                            >
                        </v-list-item-avatar>
                    </div> -->
                </div>
                <v-card
                    class="mx-auto"
                    flat
                    :width="width"
                >
                    <v-card-title>
                        {{this.age}} | 
                        <span v-if="this.gender=='man'">남성</span>
                        <span v-else-if="this.gender=='w'">여성</span>
                    </v-card-title>
                    <v-card-subtitle>
                        {{this.id}}
                    </v-card-subtitle>
                    
                        <div class="detail_left">
                            <vs-button
                                color="white"
                                border
                            >
                                <span class="texts">
                                    <i class="bx bxs-heart"> 커밋 {{this.cnt}}</i> 
                                </span>
                            </vs-button>
                            <Follower/>
                            <Following/>
                            <v-spacer></v-spacer>
                            <div class="badge">
                                <ProfileEdit/>
<!--                                 <div class="badgeImg">
                                    <v-list-item-avatar size="50">
                                    <img
                                        src="https://cdn4.iconfinder.com/data/icons/badges-9/66/31-512.png"
                                        alt="picture">
                                    </v-list-item-avatar>
                                </div> -->
                            </div>
                        </div>
                   
                    <v-card-actions>
                        
                    <v-btn
                        icon
                        @click="show = !show"
                    >
                        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                    </v-btn>
                    </v-card-actions>

                    <v-expand-transition>
                    <div v-show="show">

                        <v-card-text>
                        {{user.mystory}}
                        </v-card-text>
                        
                    </div>
                    </v-expand-transition>
                </v-card>
                <div>
                    <div class="badge">
                        <v-list-item-avatar size="70">
                            <img src="../../../assets/img/badge/badge0.png"
                            alt="../../../assets/img/badge/badge0.png"
                            >
                        </v-list-item-avatar>
                       <!--  <ProfileImgChange/> -->
                    </div>
                </div>
            </div>
</div>

</template>
<script scoped>
import { mapGetters } from 'vuex';
import Follower from '../../common/dialog/Follower'
import Following from '../../common/dialog/Following'
import ProfileEdit from '../../common/dialog/ProfileEdit'
import ProfileImgChange from '../../common/dialog/ProfileImgChange'
import {searchUserByEmail, searchUserByNickname} from '../../../api/account'
import {userCommitCount} from '../../../api/commit'

export default {
    components: {
          Follower,
          Following,
          ProfileEdit,
          ProfileImgChange,
    },
    data: () => ({
        show: false,
        id:'jimotme',//this.$route.params.id로 받은 현재 유저의 닉네임
        //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
        email:'',
        gender:'',
        badge:'https://mpng.subpng.com/20171128/221/gold-seal-png-clip-art-image-5a1d1d47856124.0292548315118574795463.jpg',
        age:'',
        imgSrc:'',
        //email로 /commit/total에서 가져온 커밋수
        cnt:0,
        }),
    computed:{
        ...mapGetters({
            user:['getUserInfo'],
        }),

        width () {
            switch (this.$vuetify.breakpoint.name) {
            case 'xs': return '200px'
            case 'sm': return '200px';
            case 'md': return '500px';
            case 'lg': return '600px';
            case 'xl': return '900px';
            }
            return 700
        },
    },
    methods:{
        showModal(){
            ProfileImgChange;
        }
    },
    created(){
        searchUserByEmail(
            // {keyword : this.user.email},
            // (response)=>{
            //     // console.log("성공"+this.user.email);
            //     const content = response.data.content[0];
            //     this.imgSrc = content.profile.filePath;
            // },
            // (error)=>{
            //     console.log("create에러"+error);
            // }
        ),

        searchUserByNickname(
            {keyword : this.id},
            (response)=>{
                const content = response.data.content[0];
                this.email = content.email;
                this.gender = content.gender;
                this.age = content.age;
                this.imgSrc = content.profile.filePath;
                this.badge = content.badge;
                
                userCommitCount(
                    this.email,
                    (response)=>{
                        this.cnt = response.data;
                    },
                    (error)=>{
                        console.log("cnt에러"+error)
                    }
                )
            },
            (error)=>{
                console.log("img에러"+error);
            }
        )
    }
}
</script>


<style scoped>

.profileImg{
    /* background-color:rgba(0,0,0,.7); */
    /* width:100%;
    height:100%; */
    /* position:absolute; */
    opacity:1;
    transition:opacity 0.5s;
}
.profileImg:hover {
     opacity:0.5;
}
.badgeImg{
    width: 35%;
    text-align: center;
    margin-top:-70%;
}
.detail{
    display: flex;
    justify-content:space-between;
}
.detail_left{
    display: flex;
}
.numbers{
    font-weight: bold;
    color:grey
}
.top {
    display: flex;
}
.nickname{
    font-size: 20px;
    font-weight: bold;
    color:black;
}
.texts{
  color:rgb(72, 199, 72);
}
</style>