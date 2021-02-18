<template>
  <v-container class="pt-7">
    <v-row>
      <v-col class="sidebar_left" cols="12" sm="2">
        <div class="d-flex justify-center">
          <SelectZone @area="onSelectArea" />
        </div>
      </v-col>
      <v-col class="mainslot" cols="12" sm="8">
        <v-sheet min-height="70vh" rounded="xl">
          <div class="mainpage pa-5">
            <!-- Top3프로필 -->
            <div class="three">
              <div class="profiles justify-center">
                <div class="second">
                  <v-avatar size="100" v-if="imgSrc2 != null" @click="goToMyPage(nickname2)">
                    <img :src="imgSrc2" alt="John" />
                  </v-avatar>
                  <v-avatar
                    v-else
                    circle
                    size="100"
                    color="blue-grey"
                    class="font-weight-medium display-2"
                  >
                    <v-icon color="white">mdi-emoticon-happy</v-icon>
                  </v-avatar>

                  <h2 class="percentage">{{ cnt2 }}</h2>
                  <span class="nickname">{{ nickname2 }}</span>
                  <div v-if="nickname2 != user.nickname">
                    <div class="temp" v-if="isThere(email2) == true">
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email2)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-heart"></i>
                      </vs-button>
                    </div>
                    <div class="temp" v-else>
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email2)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-user-plus"></i>
                      </vs-button>
                    </div>
                  </div>
                </div>
                <div class="first">
                  <v-avatar size="100" v-if="imgSrc1 != null" @click="goToMyPage(nickname1)">
                    <img :src="imgSrc1" alt="John" />
                  </v-avatar>
                  <v-avatar
                    v-else
                    circle
                    size="100"
                    color="blue-grey"
                    class="font-weight-medium display-2"
                  >
                    <v-icon color="white">mdi-emoticon-happy</v-icon>
                  </v-avatar>
                  <h2 class="percentage">{{ cnt1 }}</h2>
                  <span class="nickname">{{ nickname1 }}</span>
                  <div v-if="nickname1 != user.nickname">
                    <div class="temp" v-if="isThere(email1) != true">
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email1)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-heart"></i>
                      </vs-button>
                    </div>
                    <div class="temp" v-else>
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email1)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-user-plus"></i>
                      </vs-button>
                    </div>
                  </div>
                </div>
                <div class="third">
                  <v-avatar size="100" v-if="imgSrc3 != null" @click="goToMyPage(nickname3)">
                    <img :src="imgSrc3" alt="John" />
                  </v-avatar>
                  <v-avatar
                    v-else
                    circle
                    size="100"
                    color="blue-grey"
                    class="font-weight-medium display-2"
                  >
                    <v-icon color="white">mdi-emoticon-happy</v-icon>
                  </v-avatar>
                  <h2 class="percentage">{{ cnt3 }}</h2>
                  <span class="nickname">{{ nickname3 }}</span>
                  <div v-if="nickname3 != user.nickname">
                    <div class="temp" v-if="isThere(email3) == true">
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email3)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-heart"></i>
                      </vs-button>
                    </div>
                    <div class="temp" v-else>
                      <vs-button
                        size="l"
                        circle
                        icon
                        color="danger"
                        flat
                        @click="clickFollow(email3)"
                        class="heartIcon"
                      >
                        <i class="bx bxs-user-plus"></i>
                      </vs-button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="whitebox">
              <SearchBar @keyword="onInputChange" class="pt-16 pb-3" />
              <div class="frame">
                <div class="tables">
                  <vs-table class="w-auto text-center">
                    <template #tbody>
                      <vs-tr :key="i" v-for="(tr, i) in users" :data="tr">
                        <vs-td class="rankNum">
                          {{ tr.ranking }}
                        </vs-td>
                        <vs-td>
                          <v-avatar
                            size="50"
                            v-if="tr.profile != null"
                            @click="goToMyPage(tr.nickname)"
                          >
                            <img :src="tr.profile.filePath" alt="" />
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
                        <vs-td @click="goToMyPage(tr.nickname)">
                          {{ tr.nickname }}
                        </vs-td>
                        <vs-td class="percentageSmall">
                          {{ tr.cnt }}
                        </vs-td>
                        <vs-td v-if="tr.nickname != user.nickname">
                          <div v-if="isThere(tr.email) == true">
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
                          </div>
                          <div v-else>
                            <vs-button
                              size="l"
                              circle
                              icon
                              color="danger"
                              flat
                              @click="clickFollow(tr.email)"
                            >
                              <i class="bx bxs-user-plus"></i>
                            </vs-button>
                          </div>
                        </vs-td>
                      </vs-tr>
                    </template>
                    <template #notFound>
                      <no-data-card :icon="'magnify'" :text="'검색 결과가 없습니다'"></no-data-card>
                    </template>
                  </vs-table>
                </div>
              </div>
            </div>
            <br />
          </div>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script scoped>
import SelectZone from '../../components/index/rank/SelectZone';
import SearchBar from '../../components/index/rank/SearchBar';
import { areaList, totalList, userFindList } from '../../api/rank';
import { mapActions, mapGetters } from 'vuex';
import { follow, searchFollowers } from '../../api/follow';
import NoDataCard from '../../components/common/card/NoDataCard.vue';
export default {
  name: 'Rank',

  components: {
    SelectZone,
    SearchBar,
    NoDataCard,
  },

  data: () => ({
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
    keyword: '',
    area: 'total',
    users: [],
    imgSrc1: '',
    imgSrc2: '',
    imgSrc3: '',
    email1: '',
    email2: '',
    email3: '',
    cnt1: '',
    cnt2: '',
    cnt3: '',
    nickname1: '',
    nickname2: '',
    nickname3: '',
    //팔로우 기능
    hasFollowed: true,
  }),
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      following: ['getFollowingList'],
    }),
  },
  watch: {
    following(val) {
      this.hasFollowed = this.checkFollowing(val);
    },
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST']),
    clickFollow(to) {
      follow(
        this.user.email, //나
        to, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);
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

    onInputChange(inputValue) {
      this.users = [];
      this.keyword = inputValue;
      userFindList(
        { keyword: inputValue },
        (response) => {
          const content = response.data.content;
          for (let i = 0; i < content.length; i++) {
            const item = content[i];
            this.users.push(item);
          }
        },
        (error) => {
          console.log('에러' + error);
        }
      );
    },
    onSelectArea(areaValue) {
      this.users = [];
      this.area = areaValue;
      // this.imgSrc1='';
      // this.imgSrc2='';
      // this.imgSrc3='';
      // this.email1='';
      // this.email2='';
      // this.email3='';
      // this.cnt1='';
      // this.cnt2='';
      // this.cnt3='';
      // this.nickname1='';
      // this.nickname2='';
      // this.nickname3='';
      areaList(
        areaValue,
        (response) => {
          const res = response.data;
          const temp1 = res[0];
          this.nickname1 = temp1.nickname;
          if (temp1.profile != null) {
            this.imgSrc1 = temp1.profile.filePath;
          }
          this.email1 = temp1.email;
          this.cnt1 = temp1.cnt;

          const temp2 = res[1];
          this.nickname2 = temp2.nickname;
          if (temp2.profile != null) {
            this.imgSrc2 = temp2.profile.filePath;
          }
          this.email2 = temp2.email;
          this.cnt2 = temp2.cnt;

          const temp3 = res[2];
          this.nickname3 = temp3.nickname;
          if (temp3.profile != null) {
            this.imgSrc3 = temp3.profile.filePath;
          }
          this.email3 = temp3.email;
          this.cnt3 = temp3.cnt;

          for (let i = 3; i < res.length; i++) {
            const item = res[i];
            this.users.push(item);
          }
        },
        (error) => {
          console.log('에러' + error);
        }
      );
    },
    isThere(para) {
      for (let i = 0; i < this.following.length; i++) {
        if (this.following[i].email == para) {
          return true;
        }
      }
    },
    goToMyPage(data) {
      this.$store.commit('SELECTED_USER_ID', data);
      this.$router.push({ name: 'MyPage' });
      location.reload();
    },
  },
  created() {
    totalList(
      (response) => {
        const res = response.data;
        const temp1 = res[0];
        this.nickname1 = temp1.nickname;
        if (temp1.profile != null) {
          this.imgSrc1 = temp1.profile.filePath;
        }
        this.email1 = temp1.email;
        this.cnt1 = temp1.cnt;

        const temp2 = res[1];
        this.nickname2 = temp2.nickname;
        if (temp2.profile != null) {
          this.imgSrc2 = temp2.profile.filePath;
        }
        this.cnt2 = temp2.cnt;
        this.email2 = temp2.email;

        const temp3 = res[2];
        this.nickname3 = temp3.nickname;
        if (temp3.profile != null) {
          this.imgSrc3 = temp3.profile.filePath;
        }
        this.email3 = temp3.email;
        this.cnt3 = temp3.cnt;

        for (let i = 3; i < res.length; i++) {
          const item = res[i];
          this.users.push(item);
        }
      },
      (error) => {
        console.log('에러' + error);
      }
    );
  },
  props: {
    Top3: {
      type: String,
      default: 'total',
    },
  },
};
</script>

<style scoped>
.v-avatar {
  cursor: pointer;
}
.vs-table__tr {
  cursor: pointer;
}
.heartIcon {
  margin-top: -80%;
  margin-left: 65%;
}
.temp2 {
  margin-right: -20px;
}
.container {
  max-width: 1200px;
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
.whitebox {
  background-color: white;
}
.like {
  margin-top: -10%;
  margin-left: 15%;
}
.likeMiddle {
  margin-top: -10%;
  margin-left: 20%;
}
.three {
  display: grid;
  margin-top: 5%;
}
.profiles {
  display: flex;
  text-align: center;
  height: 50%;
}
.second {
  margin-top: 8%;
  z-index: 1;
}
.first {
  margin-left: 5%;
  margin-right: 5%;
  /* z-index: 1; */
}
.third {
  margin-top: 8%;
  z-index: 1;
}
.nickname {
  color: rgba(0, 0, 0, 0.514);
  margin-top: 2%;
}
.percentage {
  color: rgba(0, 0, 0, 0.589);
}
.profiles {
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
.rankNum {
  color: #2196f3;
  /* font-weight: bold; */
}
.percentageSmall {
  color: #4e4f52;
  font-size: large;
  /* font-weight: bold; */
}
@media (max-width: 450px) {
  .search {
    display: none;
  }

  .navbar_right {
    display: none;
  }
}
</style>
