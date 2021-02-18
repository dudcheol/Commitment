<template>
  <div class="center">
    <vs-button size="l" circle icon flat @click="active = !active">
      <i class="bx bxs-group"></i>{{ this.followingCnt }}
    </vs-button>
    <vs-dialog
      blur
      scroll
      overflow-hidden
      not-close
      auto-width
      v-model="active"
      v-on:close="isClose"
    >
      <template #header>
        <h3>
          팔로잉
        </h3>
      </template>
      <div class="con-content">
        <!-- <vs-table class="w-auto"> -->
        <vs-table style="max-height:600px">
          <template #tbody>
            <vs-tr :key="tr" v-for="tr in followers" :data="tr">
              <vs-td>
                <v-avatar size="50" v-if="tr.profile != null">
                  <img :src="tr.profile.filePath" alt="pic" @click="goToMyPage(tr.nickname)" />
                </v-avatar>
                <v-avatar
                  v-else
                  circle
                  size="50"
                  color="blue-grey"
                  class="font-weight-medium display-2"
                  @click="goToMyPage(tr.nickname)"
                >
                  <v-icon color="white">mdi-emoticon-happy</v-icon>
                </v-avatar>
              </vs-td>
              <vs-td class="nickname" id="mobileHidden" @click="goToMyPage(tr.nickname)">
                {{ tr.nickname }}
              </vs-td>
              <vs-td class="percentageSmall" id="mobileHidden" @click="goToMyPage(tr.nickname)">
                {{ tr.mystory }}
              </vs-td>
              <vs-button size="l" circle icon color="danger" flat @click="clickUnFollow(tr.email)">
                <i class="bx bxs-trash"></i>
              </vs-button>
            </vs-tr>
          </template>
        </vs-table>
        <!--           <div class="whole">팔로잉 모두 보기</div> -->
      </div>
    </vs-dialog>
  </div>
</template>
<script scoped>
import { mapActions, mapGetters } from 'vuex';
import { searchFollowings } from '../../../api/follow';
import { searchUserByNickname } from '../../../api/account';
import { follow } from '../../../api/follow';
// import FollowListsWhole from '../../index/mypage/FollowLists';
export default {
  // components: { FollowListsWhole },
  data() {
    return {
      activeButton: true,
      active: false,
      followers: [],
      email: '',
      followingCnt: 0,
      //언팔로우 기능
      hasFollowed: true,
    };
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      following: ['getFollowingList'],
      userId: ['getSelectedUserId'],
    }),
  },
  watch: {
    following(val) {
      this.hasFollowed = this.checkFollowing(val);
    },
  },
  created() {
    // this.hasFollowed = this.checkFollowing(this.following);
    searchUserByNickname(
      { keyword: this.userId },
      (response) => {
        const content = response.data;
        this.email = content.content[0].email;
        searchFollowings(
          this.email,
          (response) => {
            const res = response.data;
            this.followingCnt = res.length;
            for (let i = 0; i < res.length; i++) {
              const item = res[i];
              this.followers.push(item);
            }
          },
          (error) => {
            console.log('follower에러' + error);
          }
        );
      },
      (error) => {
        console.log('img에러' + error);
      }
    );
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST']),
    clickUnFollow(to) {
      follow(
        this.user.email, //나
        to, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);
          searchFollowings(
            this.email,
            (response) => {
              this.followers = [];
              const res = response.data;
              this.followerCnt = res.length;
              for (let i = 0; i < res.length; i++) {
                const item = res[i];
                this.followers.push(item);
              }
            },
            (error) => {
              console.log('follower에러' + error);
            }
          );
        },
        (error) => {
          console.log('unfollow에러', error);
        }
      );
    },
    clickFollow(to) {
      follow(
        this.user.email, //나
        to, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);
          searchFollowings(
            this.email,
            (response) => {
              this.followers = [];
              const res = response.data;
              this.followerCnt = res.length;
              for (let i = 0; i < res.length; i++) {
                const item = res[i];
                this.followers.push(item);
              }
            },
            (error) => {
              console.log('follower에러' + error);
            }
          );
        },
        (error) => {
          console.log('follow에러', error);
        }
      );
    },
    checkFollowing(list) {
      const compare = this.user.email;
      for (let i = 0; i < list.length; i++) {
        if (list[i].email == compare) return false; //팔로우 된사람이면 언팔
      }
      return true; //팔로우 안된사람이면 팔로우 할수있음을
    },
    isClose() {
      this.$emit('close');
    },
    goToMyPage(data) {
      this.$store.commit('SELECTED_USER_ID', data);
      this.$router.push({ name: 'MyPage' });
      location.reload();
    },
  },
};
</script>
<style scoped>
/* @media screen and (max-width: 1000px) {
  #mobileHidden {
    display: none;
  }
} */
.nickname {
  color: black;
  /* background-color: aqua; */
  font-weight: bold;
}
/* .whole{
  text-align: center;
  color:dodgerblue;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: -15%;
} */
.texts {
  color: dodgerblue;
}
.con-content {
  min-height: 500px !important;
  min-width: 500px !important;
}
</style>
