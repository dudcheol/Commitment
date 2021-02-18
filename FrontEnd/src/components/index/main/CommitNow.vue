<template>
  <div class="d-flex flex-column ml-lg-16">
    <v-expansion-panels v-model="panel" accordion flat class="mb-2 rounded-lg" multiple>
      <v-expansion-panel size="x-small" style="background-color:transparent">
        <v-expansion-panel-header>
          <div class="d-flex align-center">
            <img
              src="../../../assets/img/commitnow/LiveMonitoring.gif"
              style="max-width:18px;"
              class="mx-1"
            />
            <h2 class="ml-1 font-weight-black text-custom-bold">실시간 커밋</h2>
            <v-tooltip left color="blue-grey" transition="slide-x-reverse-transition">
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3"
                  >mdi-information</v-icon
                >
              </template>
              <span
                >실시간으로 모든 유저의 커밋을 확인할 수 있어요<br />내가 팔로우한 사람이 커밋을
                했다면 실시간 알림으로 알려드릴게요</span
              >
            </v-tooltip>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in nowCommits.slice(0, dynamicCnt)"
            :key="'commitNow' + index"
            :username="item.username"
            :address="item.address"
            :img="item.img"
            class="mb-2"
            @click="goToProfile(item)"
          ></commit-card>
          <v-btn block :ripple="false" rounded height="52px" color="blue-grey darken-4" text
            ><strong>최근 커밋만 볼 수 있어요</strong></v-btn
          >
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import CommitCard from '../../common/card/CommitCard.vue';
import { mapGetters } from 'vuex';

export default {
  components: { CommitCard },
  name: 'CommitNow',
  data() {
    return {
      nowCommits: [],
    };
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      following: ['getFollowingList'],
      realtime: ['getRealtimeCommitList'],
    }),
    panel() {
      switch (this.$vuetify.breakpoint.name) {
        case 'md':
          return [0];
        case 'lg':
          return [0];
        case 'xl':
          return [0];
        default:
          return [];
      }
    },
    dynamicCnt() {
      switch (this.$vuetify.breakpoint.name) {
        case 'md':
          return 15;
        case 'lg':
          return 15;
        case 'xl':
          return 15;
        default:
          return 5;
      }
    },
  },
  watch: {
    realtime: {
      immediate: true,
      handler(val) {
        console.log('%cCommitNow.vue line:93 val', 'color: #007acc;', val);
        this.nowCommits = val;
        if (this.checkFollowing(val[0].username)) this.openNotification(4000);
      },
    },
  },
  methods: {
    goToProfile(user) {
      this.$store.commit('SELECTED_USER_ID', user.username);
      this.$router.push({ name: 'MyPage' });
    },
    openNotification(duration) {
      this.$vs.notification({
        duration,
        position: 'top-right',
        color: 'primary',
        flat: true,
        progress: 'auto',
        title: '알림',
        text: `${this.nowCommits[0].username}님이 최근에 커밋을 작성했어요!`,
      });
    },
    checkFollowing(nickname) {
      for (let i = 0; i < this.following.length; i++) {
        if (this.following[i].nickname == nickname) return true;
      }
      return false;
    },
  },
};
</script>

<style>
.v-messages {
  min-height: 0px;
}
</style>
