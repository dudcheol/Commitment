<template>
  <div class="d-flex flex-column ml-lg-16">
    <!-- <div class="d-flex align-center mb-2">
      <img src="../../../assets/img/commitnow/LiveMonitoring.gif" style="max-width:16px;" />
      <h2 class="ml-1 font-weight-black">실시간 커밋</h2>
    </div> -->
    <h1>{{ test }}</h1>
    <v-expansion-panels v-model="panel" accordion flat class="mb-2 rounded-lg" multiple>
      <v-expansion-panel size="x-small" style="background-color:transparent">
        <v-expansion-panel-header>
          <div class="d-flex align-center">
            <img
              src="../../../assets/img/commitnow/LiveMonitoring.gif"
              style="max-width:18px;"
              class="mx-1"
            />
            <h2 class="ml-1 font-weight-black">실시간 커밋</h2>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in nowCommits"
            :key="index"
            :username="item.username"
            :address="item.address"
            :img="item.img"
            class="mb-2"
          ></commit-card>
          <v-btn block :ripple="false" rounded height="52px" color="blue-grey darken-4" text
            ><strong>더보기</strong></v-btn
          >
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import firebase from 'firebase/app';
import 'firebase/database';
import CommitCard from '../../common/card/CommitCard.vue';

export default {
  components: { CommitCard },
  name: 'CommitNow',
  data() {
    return {
      nowCommits: [],
    };
  },
  computed: {
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
  },
  created() {
    firebase
      .database()
      .ref('noti/all')
      .orderByChild('createdAt')
      .limitToLast(10)
      .on('value', (snap) => {
        let res = snap.val();
        console.log('%cCommitNow.vue line:72 res', 'color: #007acc;', res);
        this.nowCommits = [];
        for (const idx in res) {
          res[idx].id = idx;
          this.nowCommits.unshift({
            username: res[idx].from,
            address: res[idx].dataId,
            img: res[idx].profile,
            email: res[idx].userEmail,
          });
        }
      });
  },
};
</script>

<style>
.v-messages {
  min-height: 0px;
}
</style>
