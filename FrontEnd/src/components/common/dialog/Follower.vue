<template>
    <div class="center">

      <vs-button size="l" circle icon color="danger" flat @click="active=!active">
          <i class="bx bxs-heart"></i>{{ this.followerCnt }}
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
                  v-if="tr.profile!=null"
                  >
                    <img
                      :src="tr.profile.filePath"
                      alt="pic"
                    >
                  </v-avatar>
                  <v-avatar
                    v-else
                    circle
                    size="50"
                    color="blue-grey"
                    class="font-weight-medium display-2"
                  >
                    <v-icon color="white">mdi-emoticon-happy</v-icon>
                  </v-avatar>
                </vs-td>
                <vs-td class="nickname">
                {{ tr.nickname }}
                </vs-td>
                <vs-td class="percentageSmall">
                {{ tr.mystory }}
                </vs-td>
                <vs-td class="temp">
                  <vs-button size="l" circle icon color="danger" flat @click="clickFollow(tr.email)">
                    <i class="bx bxs-heart"></i>
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
import { mapActions, mapGetters } from 'vuex';
import {searchFollowers} from '../../../api/follow'
import {searchUserByNickname} from '../../../api/account'
import { follow } from '../../../api/follow';
// import FollowListsWhole from '../../index/mypage/FollowLists';
  export default {
    // components: { FollowListsWhole },
    data () {
      return {
        active: false,
        followers: [
        ],
        id:'dudcheol',//this.$route.params.id로 받은 현재 유저의 닉네임
        //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
        email:'',
        followerCnt:0,
        //팔로우 기능
        hasFollowed: true,
      }
    },
    computed:{
      ...mapGetters({
        user:['getUserInfo'], 
        following: ['getFollowingList'],
        userId:['getSelectedUserId'],
      })
    },
    watch: {
      following(val) {
        this.hasFollowed = this.checkFollowing(val);
      },
    },
    created(){
      // this.hasFollowed = this.checkFollowing(this.following); 
        searchUserByNickname(
            {keyword : this.id},
            (response)=>{
                const content = response.data.content[0];
                this.email = content.email;
                searchFollowers(
                  this.email,
                  (response)=>{
                    
                      const res = response.data;
                      this.followerCnt = res.length;
                      for(let i=0;i<res.length;i++){
                        const item = res[i];
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
    methods:{
      ...mapActions(['GET_FOLLOWING_LIST']),
      clickFollow(to) {
        follow(
          this.user.email,  //나
          to, //상대
          () => {
            this.GET_FOLLOWING_LIST(this.user.email);
            console.log(this.user.email,"가",to,"팔로우 완료");
          },
          (error) => {
            console.log(
              'follow에러', error
            );
          }
        );
      },
      checkFollowing(list) {
        const compare = this.user.email;
        console.log("eeee",compare, this.data.user.email);
        for (let i = 0; i < list.length; i++) {
          if (list[i].email == compare) return false;
        }
        return true;
      },
    }
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