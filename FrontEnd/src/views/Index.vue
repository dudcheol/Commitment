<template>
  <v-app id="inspire">
    <Header></Header>
    <v-main class="blue-grey lighten-5 pb-16">
      <router-view :openWriteDialog="openWriteDialog" @add-commit="commit"></router-view>
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
      :disabled="!latlng || commitLoading || totalTime != 0"
      elevation="10"
      class="mb-14 mb-md-0"
    >
      <div v-if="totalTime != 0">
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
      :confirm="commitDialog"
      :confirmContent="confirmContent"
      :confirmTitle="confirmTitle"
      :region="commitRegion"
      :datas="commitDatas"
      @close="
        closeCommitComplete();
        commitLoading = false;
      "
      @confirm-ok="confirmOk"
    ></commit-complete>
    <write-dialog
      :web="writeDialog"
      @close="closeWriteDialog"
      :empCommitId="commitId"
    ></write-dialog>
    <BadgeDialog
      :confirm="badgeflag"
      :confirmTitle="'뱃지 획득!'"
      :confirmContent="badgemsg"
      :badgename="badgename"
      :path="path"
      @close="badgeRemain()"
    ></BadgeDialog>

    <Footer class="d-md-none"></Footer>
  </v-app>
</template>

<script>
import { addCommit, READ_PERMISSION_OK } from '../api/commit';
import { mapActions, mapGetters } from 'vuex';
import Dialog from '../components/common/dialog/Dialog.vue';
import CommitComplete from '../components/common/dialog/CommitComplete.vue';
import Header from '../components/index/Header.vue';
import Footer from '../components/index/Footer.vue';
import WriteDialog from '../components/common/dialog/WriteDialog.vue';
import BadgeDialog from '../components/common/dialog/BadgeDialog.vue';
import { badgeCheck } from '../api/badge';
export default {
  components: { Dialog, CommitComplete, Header, WriteDialog, BadgeDialog, Footer },
  name: 'Index',
  computed: {
    ...mapGetters({
      latlng: ['getCurrentLatlng'],
      user: ['getUserInfo'],
      address: ['getCurrentAddress'],
      minutes: ['getMinutes'],
      seconds: ['getSeconds'],
      totalTime: ['getTotalTime'],
      writeDialog: ['getWriteDialogState'],
      commitDialog: ['getCommitDialogState'],
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
      }
    },
  },
  data() {
    return {
      commitBtnIcon: 'mdi-map-marker-check',
      commitLoading: false,
      commitAlert: false,
      confirmTitle: '커밋완료🥳',
      confirmContent: '현재 커밋에 글이나 사진을 작성할까요?',
      alertTitle: '커밋실패😰',
      alertContent: '동일한 위치는 하루에 1번만 커밋할 수 있습니다',
      openWriteDialog: false,
      commitRegion: '',
      commitDatas: '',
      commitTimeout: false,
      badgeflag: false,
      badgemsg: '',
      badgename: '',
      badgearr: [],
      badgeIndex: 0,
      path: '',
      commitId: '',
      commitAddress: '',
    };
  },
  methods: {
    ...mapActions([
      'CURRENT_LATLNG',
      'FIRST_START_TIMER',
      'START_TIMER',
      'STOP_TIMER',
      'GET_EMPCOMMIT_LIST',
    ]),
    commit() {
      if (this.totalTime != 0) return;
      this.START_TIMER();
      this.commitLoading = true;
      addCommit(
        this.user.email,
        this.latlng.lat,
        this.latlng.lng,
        READ_PERMISSION_OK,
        (response) => {
          if (response.data) {
            badgeCheck(
              this.user.email,
              (response) => {
                this.badgeIndex = 0;
                this.badgearr = response.data;
                if (this.badgearr[0].result == 'yes') {
                  this.badgemsg = this.badgearr[0].msg;
                  this.badgename = this.badgearr[0].badge;
                  this.path = require('../assets/img/badge/' + this.badgename + '.png');
                  this.badgeflag = true;
                  this.badgeIndex++;
                }
              },
              (error) => {
                console.log(error);
              }
            );
            this.commitRegion = response.data.region;
            this.commitDatas = [[response.data.localX, response.data.localY, 3]];
            this.confirmContent = `[ ${this.address} ] 에서 남긴 커밋에 글이나 사진을 작성할까요?`;
            this.commitId = response.data.id;
            this.commitAddress = this.address;
            this.$store.commit('COMMIT_DIALOG', true);
            this.openNotification(4000);
          } else {
            this.alertContent = '예상치 못한 오류가 발생했습니다. 다시 시도해주세요.';
            this.STOP_TIMER();
            this.$store.commit('COMMIT_DIALOG', false);
            this.$store.commit('WRITE_DIALOG', false);
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
      this.$store.commit('COMMIT_DIALOG', false);
      this.$store.commit('WRITE_DIALOG', {
        state: true,
        id: this.commitId,
        address: this.commitAddress,
      });
    },
    closeCommitComplete() {
      this.GET_EMPCOMMIT_LIST(this.user.email);
      this.$store.commit('COMMIT_DIALOG', false);
    },
    closeWriteDialog() {
      this.GET_EMPCOMMIT_LIST(this.user.email);
      this.$store.commit('WRITE_DIALOG', { state: false, id: '', address: '' });
    },
    badgeRemain() {
      this.badgeflag = false;
      if (this.badgeIndex < this.badgearr.length) {
        this.badgemsg = this.badgearr[this.badgeIndex].msg;
        this.badgename = this.badgearr[this.badgeIndex].badge;
        this.path = require('../assets/img/badge/' + this.badgename + '.png');
        this.badgeflag = true;
        this.badgeIndex++;
      }
    },
  },
  created() {
    this.CURRENT_LATLNG();
    if (this.totalTime != 0) this.FIRST_START_TIMER();
  },
};
</script>

<style scoped></style>
