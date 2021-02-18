<template>
  <v-app-bar app color="white" flat :hide-on-scroll="hideOnScroll" style="z-index:10">
    <v-btn
      elevation="0"
      fab
      x-small
      class="ml-3 d-none d-sm-flex mr-2"
      color="white"
      style="z-index:11;"
      :ripple="false"
      to="/"
      circle
    >
      <v-img src="../../assets/img/main/commitment_logo.jpg" alt="logo" height="35" width="35" />
    </v-btn>
    <!-- <div class="text-h2 font-weight-black blue-grey--text text--darken-3">
      {{ pageName }}
    </div> -->
    <div class="app-name blue-grey--text text--darken-3" style="font-size:30px">
      {{ pageName }}
    </div>

    <!-- 가운데 탭 항상 가운데에 만들기 버튼개수 상관없이 -->
    <v-tabs centered color="primary" class="tabs_list d-none d-md-block" v-model="tab">
      <v-tab
        v-for="(item, index) in items"
        :key="'tabMidIcon' + index"
        :to="item.route"
        :ripple="false"
      >
        <v-icon>
          {{ item.icon }}
        </v-icon>
      </v-tab>
    </v-tabs>

    <div class="d-none d-sm-flex ml-auto">
      <v-btn
        fab
        elevation="0"
        small
        text
        :ripple="false"
        color="primary"
        class="mr-3"
        @click="goToMyPage"
      >
        <v-avatar v-if="user.profile" circle size="40">
          <img :src="user.profile.filePath" />
        </v-avatar>
        <v-avatar v-else circle size="40" color="blue-grey" class="font-weight-medium display-2">
          <v-icon color="white">mdi-emoticon-happy</v-icon>
        </v-avatar>
      </v-btn>
      <v-btn
        fab
        elevation="0"
        small
        text
        :ripple="false"
        color="primary"
        class="mr-2"
        @click="alert = true"
      >
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-badge
        :value="noti.length && notiVisible"
        overlap
        offset-x="30"
        offset-y="20"
        color="error"
      >
        <template v-slot:badge>
          {{ noti.length }}
        </template>
        <v-menu offset-y rounded="lg">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              fab
              elevation="0"
              small
              text
              :ripple="false"
              color="primary"
              class="mr-2"
              v-bind="attrs"
              v-on="on"
              @click="notiVisible = false"
            >
              <v-icon>{{ right_items[1].icon }}</v-icon>
            </v-btn>
          </template>
          <div class="pa-3 white">
            <h2 class="px-2">알림</h2>
            <v-list class="pa-0" dense nav style="max-height:70vh; overflow:auto;">
              <v-list-item-group>
                <div v-if="!noti.length" class="pa-2 grey--text" :ripple="false">
                  알림이 없습니다
                </div>
                <v-list-item
                  v-for="(item, index) in noti"
                  :key="'noti' + index"
                  class="px-2"
                  :ripple="false"
                  @click="clickNotiItem(item)"
                >
                  <v-list-item-icon>
                    <v-icon
                      v-text="notiMsg[item.type].icon"
                      :color="notiMsg[item.type].color"
                    ></v-icon>
                  </v-list-item-icon>
                  <v-list-item-title
                    ><strong>{{ item.from }}</strong
                    >{{ notiMsg[item.type].content }}</v-list-item-title
                  >
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </div>
        </v-menu>
      </v-badge>

      <v-btn
        fab
        elevation="0"
        small
        text
        :ripple="false"
        color="primary"
        class="mr-2"
        @click="LOGOUT"
      >
        <v-icon>{{ right_items[3].icon }}</v-icon>
      </v-btn>
    </div>

    <v-speed-dial
      class="d-flex d-sm-none ml-auto"
      v-model="fab"
      direction="bottom"
      transition="slide-x-reverse-transition"
    >
      <template v-slot:activator>
        <v-btn x-small text color="primary" fab>
          <v-icon v-if="fab">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-menu
          </v-icon>
        </v-btn>
      </template>

      <v-btn fab elevation="0" small text :ripple="false" color="primary" @click="goToMyPage">
        <v-avatar v-if="user.profile" circle size="40">
          <img :src="user.profile.filePath" />
        </v-avatar>
        <v-avatar v-else circle size="40" color="blue-grey" class="font-weight-medium display-2">
          <v-icon color="white">mdi-emoticon-happy</v-icon>
        </v-avatar>
      </v-btn>

      <v-btn fab elevation="0" small :ripple="false" color="primary" @click="alert = true">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-badge
        :value="noti.length && notiVisible"
        overlap
        offset-x="30"
        offset-y="20"
        color="error"
      >
        <template v-slot:badge>
          {{ noti.length }}
        </template>
        <v-menu offset-y rounded="lg">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              fab
              elevation="0"
              small
              :ripple="false"
              color="primary"
              v-bind="attrs"
              v-on="on"
              @click="notiVisible = false"
            >
              <v-icon>{{ right_items[1].icon }}</v-icon>
            </v-btn>
          </template>
          <div class="pa-3 white">
            <h2 class="px-2">알림</h2>
            <v-list class="pa-0" dense nav style="max-height:50vh; overflow:auto;">
              <v-list-item-group color="primary">
                <div v-if="!noti.length" class="pa-2 grey--text" :ripple="false">
                  알림이 없습니다
                </div>
                <v-list-item
                  v-for="(item, index) in noti"
                  :key="'noti' + index"
                  class="px-2"
                  :ripple="false"
                  @click="clickNotiItem(item)"
                >
                  <v-list-item-icon>
                    <v-icon
                      v-text="notiMsg[item.type].icon"
                      :color="notiMsg[item.type].color"
                    ></v-icon>
                  </v-list-item-icon>
                  <v-list-item-title
                    ><strong>{{ item.from }}</strong
                    >{{ notiMsg[item.type].content }}</v-list-item-title
                  >
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </div>
        </v-menu>
      </v-badge>

      <v-btn fab elevation="0" small :ripple="false" color="primary" @click="LOGOUT">
        <v-icon>{{ right_items[3].icon }}</v-icon>
      </v-btn>
    </v-speed-dial>
    <SearchDailog :alert="alert" :searchValue="''" @close="alert = false"></SearchDailog>
  </v-app-bar>
</template>

<script>
import firebase from 'firebase/app';
import 'firebase/database';
import { mapActions, mapGetters } from 'vuex';
import { clickNoti } from '../../api/noti';
import { searchNickname } from '../../api/search';
import SearchDailog from '../../components/common/dialog/SearchDialog.vue';

export default {
  components: { SearchDailog },
  data() {
    return {
      tab: '/',
      fab: false,
      word: '',
      users: '',
      alert: false,
      items: [
        { icon: 'mdi-home', route: '/' },
        { icon: 'mdi-map-marker', route: '/sns' },
        { icon: 'mdi-trophy', route: '/rank' },
        { icon: 'mdi-medal', route: '/badge' },
        { icon: 'mdi-heart', route: '/likes' },
      ],
      right_items: [
        { icon: 'mdi-emoticon-happy' },
        { icon: 'mdi-bell' },
        { icon: 'mdi-cog' },
        { icon: 'mdi-logout' },
      ],
      noti: [],
      notiMsg: {
        follow: {
          icon: 'mdi-account',
          content: '님이 회원님을 팔로우하셨습니다',
          color: 'primary',
        },
        like: {
          icon: 'mdi-heart',
          content: '님이 게시글에 좋아요를 남겼습니다',
          color: 'error',
        },
        comment: {
          icon: 'mdi-comment',
          content: '님이 게시글에 댓글을 남겼습니다',
          color: 'blue-grey',
        },
      },
      notiVisible: false,
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
    hideOnScroll() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return true;
        case 'sm':
          return true;
        case 'md':
          return false;
        case 'lg':
          return false;
        case 'xl':
          return false;
      }
      return '';
    },
    pageName() {
      switch (this.tab) {
        case '/':
          return 'Commitment';
        case '/sns':
          return 'Near';
        case '/rank':
          return 'Ranking';
        case '/likes':
          return 'Likes';
        case '/badge':
          return 'Badge';
      }
      return '';
    },
  },
  methods: {
    ...mapActions(['LOGOUT']),
    watchNoti() {
      firebase
        .database()
        .ref(`noti/${this.user.nickname}`)
        .on('value', (snap) => {
          let res = snap.val();
          this.noti = [];
          for (const idx in res) {
            if (this.user.nickname == res[idx].from) {
              clickNoti(
                res[idx].id,
                this.user.nickname,
                () => {
                  this.notiVisible = true;
                },
                (error) => {
                  console.log(
                    '%cerror Header.vue line:282 ',
                    'color: red; display: block; width: 100%;',
                    error
                  );
                }
              );
              continue;
            }
            res[idx].id = idx;
            this.noti.unshift(res[idx]);
          }
        });
    },
    clickNotiItem(noti) {
      switch (noti.type) {
        case 'follow':
          this.$store.commit('SELECTED_USER_ID', noti.from);
          this.$router.push({ name: 'MyPage' });
          break;
        case 'like':
          this.$store.commit('SELECTED_BOARD_ID', noti.dataId);
          this.$router.push({ name: 'Detail' });
          break;
        case 'comment':
          this.$store.commit('SELECTED_BOARD_ID', noti.dataId);
          this.$router.push({ name: 'Detail' });
          break;
      }
      clickNoti(
        noti.id,
        this.user.nickname,
        () => {},
        (error) => {
          console.log(
            '%cerror Header.vue line:282 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    goToMyPage() {
      this.$store.commit('SELECTED_USER_ID', this.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
    goToMain() {
      this.$router.replace({ name: 'Main' });
    },
    search() {
      this.word = '';

      searchNickname(
        this.searchvalue,
        (response) => {
          this.users = response.data.content;
          this.alert = true;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  created() {
    this.watchNoti();
  },
};
</script>

<style>
.search-box {
  position: absolute;
  left: 6%;
  background: #cfd8dc;
  height: 40px;
  border-radius: 40px;
  margin-top: 4px;
  z-index: 2px;
}

.search-box .result-box {
  /* padding: 10px 0px; */
  font-size: 1em;
  border-radius: 20px;
}

.result-box li {
  list-style: none;
  padding: 10px 15px;
  width: 100%;
  cursor: default;
  display: none;
  border: none;
}

.result-list {
  background-color: rgba(236, 239, 241);
  margin-top: 40px;
  border-radius: 0px 0px 20px 20px;
}

.search-box:hover > .result-box li {
  display: block;
}

.result-box li:hover {
  background: white;
  border-radius: 20px;
}

.search-box:hover {
  border-radius: 20px 20px 0px 0px;
}

.search-box:hover > .search-txt {
  width: 150px;
  padding: 0 6px;
}

.search-box:hover > .search-btn {
  color: black;
}

.search-box .search-btn {
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #cfd8dc;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
  color: white;
  cursor: pointer;
}

.search-btn > v-icon {
  font-size: 30px;
}

.search-txt {
  border: none;
  background: none;
  outline: none;
  float: left;
  margin-top: 9px;
  margin-left: 14px;
  padding: 0;
  color: black;
  font-size: 14px;
  transition: 0.4s;
  width: 150px;
  font-weight: bold;
}

.tabs_list {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

v-speed-dial {
  z-index: 0 !important;
}

@media (max-width: 1200px) {
  .search-box {
    position: absolute;
    left: 10px;
    background: #cfd8dc;
    border-radius: 40px;
    background-color: white;
  }

  .search-box:hover {
    width: 25vw;
    background-color: #cfd8dc;
  }

  .search-box:hover > .search-txt {
    width: 100vw;
    padding: 0 6px;
    margin-top: 8px;
  }

  .search-btn {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: 0.4s;
    color: white;
    cursor: pointer;
  }

  .search-box:hover > .search-btn {
    background: #cfd8dc;
  }

  .search-btn > v-icon {
    font-size: 30px;
  }

  .search-txt {
    border: none;
    background: none;
    outline: none;
    float: left;
    padding: 0;
    color: #263238;
    font-size: 16px;
    transition: 0.4s;
    width: 0px;
    font-weight: bold;
  }
}

@media (max-width: 450px) {
  .v-tab {
    min-width: 10vw !important;
  }

  .search-box:hover {
    width: 80vw;
  }
}
</style>
