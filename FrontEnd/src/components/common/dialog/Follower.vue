<template>
    <div class="center">
      <vs-button
      color="white"
            border
            :active="active == 2"
            @click="active=!active"
        >
          <span class="texts">
            <i class="bx bxs-heart"> 팔로워<span class="numbers"> {{this.followerCnt}}</span></i> 
          </span>
        </vs-button>
      <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="500px" height="500px">
        <template #header>
          <h3>
            팔로워
          </h3>
        </template>
        <div class="con-content">
          <vs-table class="w-auto" width="300px" height="300px">
            <template #tbody>
              <vs-tr
                :key="i"
                v-for="(tr, i) in followers"
                :data="tr"
              >
                <vs-td>
                  <v-avatar
                  size="50"  
                  >
                    <img
                      :src="tr.profile.filePath"
                      alt="pic"
                    >
                  </v-avatar>
                </vs-td>
                <vs-td class="nickname">
                {{ tr.nickname }}
                </vs-td>
                <vs-td class="percentageSmall">
                {{ tr.mystory }}
                </vs-td>
                <vs-td class="temp">
                  <vs-button
                    icon
                    border
                    :active="active == 2"
                    @click="active = 2"
                      class="temp2"
                  >
                    <i class='bx bxs-heart' ></i>
                  </vs-button>
                </vs-td>
              </vs-tr>
            </template>
          </vs-table>
          <div class="whole">팔로워 모두 보기</div>
        </div>
      </vs-dialog>
    </div>
</template>
<script scoped>
import { mapGetters } from 'vuex';
import {searchFollowers} from '../../../api/follow'
import {searchUserByNickname} from '../../../api/account'
// import FollowListsWhole from '../../index/mypage/FollowLists';
  export default {
    // components: { FollowListsWhole },
    data () {
      return {
        active: false,
        followers: [
        ],
        id:'jimotme',//this.$route.params.id로 받은 현재 유저의 닉네임
        //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
        email:'',
        followerCnt:0,
      }
    },
    computed:{
      ...mapGetters({
        user:['getUserInfo'],
      })
    },
    created(){
        searchUserByNickname(
            {keyword : this.id},
            (response)=>{
                const content = response.data.content[0];
                this.email = content.email;
                searchFollowers(
                  this.email,
                  (response)=>{
                      const res = response.data;
                      // console.log("--------");
                      // console.log("email",this.email);
                      // console.log("res",res);
                      // console.log("--------");
                      this.followerCnt = res.length;
                      for(let i=0;i<res.length;i++){
                        const item = res[i];
                        // console.log(item);
                        this.followers.push(item);
                      }
                  },
                  (error)=>{
                      console.log("follower에러"+error);
                  }
              )
            },
            (error)=>{
                console.log("img에러"+error);
            }
        )
    },
  }
</script>
<style scoped>
.nickname{
  color:black;
  font-weight: bold;
}
.whole{
  text-align: center;
  color:dodgerblue;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: -15%;
}
.texts{
  color:rgb(255, 30, 98);
}
</style>