<template>
  <div class="center">
    <vs-button size="l" circle icon color="danger" flat @click="active = !active">
      <i class="bx bxs-heart"></i>{{ this.followerCnt }}
    </vs-button>
    <vs-dialog blur scroll overflow-hidden not-close v-model="active" v-on:close="isClose">
      <template #header>
        <h3>
          팔로워
        </h3>
      </template>
      <div class="con-content">
        <vs-table>
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
                >
                  <v-icon color="white">mdi-emoticon-happy</v-icon>
                </v-avatar>
              </vs-td>
              <vs-td class="nickname" @click="goToMyPage(tr.nickname)">
                {{ tr.nickname }}
              </vs-td>
              <vs-td class="percentageSmall" @click="goToMyPage(tr.nickname)">
                {{ tr.mystory }}
              </vs-td>
              <vs-td class="temp" v-if="isThere(tr.email) == true">
                <vs-button
                  size="l"
                  circle
                  icon
                  color="danger"
                  flat
                  @click="clickUnFollow(tr.email)"
                >
                  <i class="bx bxs-heart"></i>
                </vs-button>
              </vs-td>
              <vs-td class="temp" v-else>
                <vs-button size="l" circle icon color="danger" flat @click="clickFollow(tr.email)">
                  <i class="bx bxs-user-plus"></i>
                </vs-button>
              </vs-td>
            </vs-tr>
          </template>
        </vs-table>
        <!--           <div class="whole">팔로워 모두 보기</div> -->
      </div>
    </vs-dialog>
  </div>
</template>
<script scoped>
import { mapActions, mapGetters } from 'vuex';
import { searchFollowers } from '../../../api/follow';
import { searchUserByNickname } from '../../../api/account';
import { follow } from '../../../api/follow';
// import FollowListsWhole from '../../index/mypage/FollowLists';
export default {
  // components: { FollowListsWhole },
  data() {
    return {
      active: false,
      followers: [],
      email: '',
      followerCnt: 0,
      //팔로우 기능
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
      //val(=리스트임)에 팔로우된거 있는지 확인하고 맞으면 hasFollowed에 TRUE저장
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
        searchFollowers(
          this.email,
          (response) => {
            const res = response.data;
            this.followerCnt = res.length;
            for (let i = 0; i < res.length; i++) {
              const item = res[i];
              this.followers.push(item);
              // this.isThere(item.email);
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
    clickFollow(to) {
      follow(
        this.user.email, //나
        to, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);

          searchFollowers(
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

          // location.reload();

          // this.$router.go(0)
        },
        (error) => {
          console.log('follow에러', error);
        }
      );
    },
    clickUnFollow(to) {
      follow(
        this.user.email, //나
        to, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);

          searchFollowers(
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
          this.GET_FOLLOWING_LIST(this.user.email);
          // location.reload();
        },
        (error) => {
          console.log('follow에러', error);
        }
      );
    },
    checkFollowing(list) {
      const compare = this.user.email;
      for (let i = 0; i < list.length; i++) {
        if (list[i].email == compare) return false;
      }
      return true;
    },

    isThere(para) {
      for (let i = 0; i < this.following.length; i++) {
        if (this.following[i].email == para) {
          return true;
        }
      }
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
.nickname {
  color: black;
  font-weight: bold;
}
.whole {
  text-align: center;
  color: dodgerblue;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: -15%;
}
.texts {
  color: rgb(255, 30, 98);
}
.con-content {
  min-height: 500px !important;
  min-width: 500px !important;
}
</style>
