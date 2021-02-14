<template>
  <v-app id="inspire">
    <Header></Header>
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
        closeCommitComplete();
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
import Header from '../components/index/Header.vue';
export default {
  components: { Dialog, CommitComplete, Header },
  name: 'Index',
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
    ...mapActions(['CURRENT_LATLNG', 'START_TIMER', 'STOP_TIMER', 'GET_EMPCOMMIT_LIST']),
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
    closeCommitComplete() {
      this.GET_EMPCOMMIT_LIST(this.user.email);
    },
  },
  created() {
    this.CURRENT_LATLNG();
  },
};
</script>

<style scoped></style>
