<template>

<div class="d-flex justify-center">
<!-- top -->
            <div class="top" :justify="dynamicJustify">
                <div>
                    <ProfileImgChange/>
                </div>
                <v-card
                    class="mx-auto"
                    flat
                    :width="width"
                >
                    <v-card-title>
                        {{user.age}} | 
                        <span v-if="user.gender=='man'">남성</span>
                        <span v-else-if="user.gender=='woman'">여성</span>
                    </v-card-title>
                    <v-card-subtitle>
                        {{user.nickname}}
                    </v-card-subtitle>
                    <div class="detail_left">
                        <v-spacer></v-spacer>
                        <div class="badge">
                            <div class="badgeImg">
                                <v-list-item-avatar size="50">
                                <img
                                    src="https://cdn4.iconfinder.com/data/icons/badges-9/66/31-512.png"
                                    alt="picture">
                                </v-list-item-avatar>
                            </div>
                        </div>

                    </div>

                    <v-card-actions>
                        <vs-button
                            uploade
                            success
                            border
                            :active="active == 2"
                            @click="active = 2"
                            class="button"
                        >
                            <i class="bx bxs-magic-wand"> 커밋 {{user.cnt}}</i> 
                        </vs-button>
                        <Follower/>
                        
                    <v-spacer></v-spacer>
                    <ProfileEdit/>
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
                        {{user.tel}}
                        </v-card-text>
                        
                    </div>
                    </v-expand-transition>
                </v-card>
                
            </div>
</div>

</template>
<script scoped>
import { mapGetters } from 'vuex';
import Follower from '../../common/dialog/Follower'
import ProfileEdit from '../../common/dialog/ProfileEdit'
import ProfileImgChange from '../../common/dialog/ProfileImgChange'
import {searchUserByEmail} from '../../../api/account'
export default {
    components: {
          Follower,
          ProfileEdit,
          ProfileImgChange,
    },
    data: () => ({
        show: false,
        cnt:0,
        imgSrc:'https://smallstartour.com/wp-content/uploads/2020/05/SA_page3_top1080x700-compressor.jpg',
        userInfo:[],
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
        doMouseOver(){
            // this.imgSrc = "https://www.pngitem.com/pimgs/m/242-2427082_transparent-telegram-icon-png-facebook-twitter-linkedin-instagram.png";
        },
        doMouseLeave(){
            this.imgSrc = "https://smallstartour.com/wp-content/uploads/2020/05/SA_page3_top1080x700-compressor.jpg";
        },
        profileImgChange(){

        }
    },
    created(){

        // console.log(email);
        searchUserByEmail(
            {keyword : this.user.email},
            (response)=>{
                console.log("성공22"+this.user.email);
                const content = response.data.content;
                this.userInfo.push(content);
                console.log("여기"); 
                console.log(content)
                console.log(content.profile);
                console.log(content.profile.filePath); 
                console.log("------")
                this.imgSrc = content.profile.filePath;
                console.log("imgsrc"+this.imgSrc);
                console.log(response.data.content);
            },
            (error)=>{
                console.log("create에러"+error);
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
        .agengender{
            font-weight: bold;
            color:grey;
        }
        .button{
            margin-right: 2%;
        }
</style>