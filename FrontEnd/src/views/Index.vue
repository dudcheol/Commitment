<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      <v-btn elevation="" fab x-small class="ml-3 d-none d-sm-flex" color="white">
        <v-avatar size="35">
          <img src="../assets/img/main/commitment_logo.jpg" alt="logo" />
        </v-avatar>
      </v-btn>

      <!-- 가운데 탭 항상 가운데에 만들기 버튼개수 상관없이 -->
      <v-tabs centered color="grey darken-1" class="tabs_list">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>

      <div class="d-none d-sm-flex ml-auto">
        <v-btn
          fab
          elevation=""
          x-small
          dark
          color="blue darken-2"
          class="mr-3"
          v-for="item in right_items"
          :key="item"
        >
          <v-icon>{{ item.icon }}</v-icon>
        </v-btn>
      </div>

      <v-speed-dial
        class="d-flex d-sm-none ml-auto"
        v-model="fab"
        direction="bottom"
        transition="slide-x-reverse-transition"
      >
        <template v-slot:activator>
          <v-btn x-small color="blue" dark fab>
            <v-icon v-if="fab">
              mdi-close
            </v-icon>
            <v-icon v-else>
              mdi-chevron-down
            </v-icon>
          </v-btn>
        </template>
        <v-btn fab dark x-small color="blue" v-for="item in right_items" :key="item">
          <v-icon>{{ item.icon }}</v-icon>
        </v-btn>
      </v-speed-dial>

      <div class="search-box">
        <div>
          <input type="text" name="" class="search-txt" placeholder="Search" />
          <a class="search-btn" href="#">
            <v-icon color="white">mdi-magnify</v-icon>
          </a>
        </div>
        <div class="result-box">
          <div class="result-list">
            <li>hello</li>
            <li>it's me</li>
            <li>I've</li>
            <li>been</li>
            <li>wandering</li>
          </div>
        </div>
      </div>
    </v-app-bar>
    <v-main class="blue-grey lighten-5">
      <router-view :openWriteDialog="openWriteDialog" @close-write="closeWrite"></router-view>
    </v-main>
    <v-btn
      fab
      large
      color="primary"
      fixed
      right
      bottom
      :ripple="false"
      @click="commit"
      :loading="!latlng || commitLoading"
      :disabled="!latlng || commitLoading || commitTimeout"
      elevation="10"
    >
      <div v-if="commitTimeout">
        <v-icon dark>mdi-lock</v-icon>
        <div>{{ min }}:{{ sec }}</div>
      </div>
      <v-icon v-else dark x-large>{{ commitBtnIcon }}</v-icon>
    </v-btn>
    <Dialog
      :alert="commitAlert"
      :alertTitle="alertTitle"
      :alertContent="alertContent"
      @close="
        commitAlert = false;
        commitLoading = false;
      "
    ></Dialog>
    <commit-complete
      :confirm="commitConfirm"
      :confirmContent="confirmContent"
      :confirmTitle="confirmTitle"
      :region="commitRegion"
      :datas="commitDatas"
      @close="
        commitConfirm = false;
        commitLoading = false;
      "
      @confirm-ok="confirmOk"
    ></commit-complete>
  </v-app>
</template>

<script>
import { addCommit, READ_PERMISSION_OK } from '../api/commit';
import { mapActions, mapGetters } from 'vuex';
import Dialog from '../components/common/dialog/Dialog.vue';
import CommitComplete from '../components/common/dialog/CommitComplete.vue';
export default {
  components: { Dialog, CommitComplete },
  name: 'MainPage',
  computed: {
    ...mapGetters({
      latlng: ['getCurrentLatlng'],
      user: ['getUserInfo'],
      address: ['getCurrentAddress'],
      minutes: ['getMinutes'],
      seconds: ['getSeconds'],
      totalTime: ['getTotalTime'],
    }),
    min() {
      return (this.minutes < 10 ? '0' : '') + this.minutes;
    },
    sec() {
      return (this.seconds < 10 ? '0' : '') + this.seconds;
    },
  },
  watch: {
    totalTime(val) {
      if (val == 0) {
        this.STOP_TIMER();
        this.commitTimeout = false;
      }
    },
  },
  data() {
    return {
      fab: false,
      items: [
        { icon: 'mdi-home', route: '/' },
        { icon: 'mdi-map-marker', route: '/sns' },
        { icon: 'mdi-medal', route: '/rank' },
        { icon: 'mdi-heart', route: 'likes' },
      ],
      right_items: [
        { icon: 'mdi-account', route: '/' },
        { icon: 'mdi-bell', route: '/sns' },
        { icon: 'mdi-cog', route: '/rank' },
        { icon: 'mdi-logout', route: 'likes' },
      ],
      alldatalist: [],
      commitBtnIcon: 'mdi-map-marker-check',
      commitLoading: false,
      commitConfirm: false,
      commitAlert: false,
      confirmTitle: '커밋완료🥳',
      confirmContent: '현재 커밋에 글이나 사진을 작성할까요?',
      alertTitle: '커밋실패😰',
      alertContent: '동일한 위치는 하루에 1번만 커밋할 수 있습니다',
      openWriteDialog: false,
      commitRegion: '',
      commitDatas: '',
      commitTimeout: false,
    };
  },
  methods: {
    ...mapActions(['CURRENT_LATLNG', 'START_TIMER', 'STOP_TIMER']),
    commit() {
      if (this.minutes != 0 || this.seconds != 0) {
        return;
      }
      this.START_TIMER();
      this.commitLoading = true;
      this.commitTimeout = true;
      addCommit(
        this.user.email,
        this.latlng.lat,
        this.latlng.lng,
        READ_PERMISSION_OK,
        (response) => {
          console.log('%cIndex.vue line:115 response', 'color: #007acc;', response.data);
          if (response.data) {
            this.commitRegion = response.data.region;
            this.commitDatas = [[response.data.localX, response.data.localY, 3]];
            this.confirmContent = `[ ${this.address} ] 에서 남긴 커밋에 글이나 사진을 작성할까요?`;
            this.commitConfirm = true;
            this.openNotification(4000);
          } else {
            this.alertContent = `[ ${this.address} ] 에서 이미 커밋하셨습니다. 1시간 뒤에 다시 시도해주세요.`;
            this.commitAlert = true;
          }
        },
        (error) => {
          console.log(
            '%cerror Index.vue line:116 ',
            'color: red; display: block; width: 100%;',
            error
          );
          this.alertContent = `알 수 없는 오류로 커밋에 실패했습니다. 다시 시도해주세요.`;
          this.commitAlert = true;
        }
      );
    },
    openNotification(duration) {
      this.$vs.notification({
        duration,
        position: 'top-right',
        color: 'primary',
        flat: true,
        progress: 'auto',
        title: 'Commit!',
        text: `<strong>${this.address}</strong>에서 커밋했습니다.`,
      });
    },
    confirmOk() {
      this.openWriteDialog = true;
    },
    closeWrite() {
      this.openWriteDialog = false;
    },
  },
  created() {
    this.CURRENT_LATLNG();
  },
};
</script>

<style scoped>
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
