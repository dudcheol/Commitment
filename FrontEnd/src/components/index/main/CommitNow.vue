<template>
  <div class="d-flex flex-column ml-lg-16">
    <v-expansion-panels
      v-model="panel"
      accordion
      flat
      class="mb-2 rounded-lg"
      multiple
    >
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
            v-for="(item, index) in openGroups"
            :key="index"
            :username="item.from"
            :address="item.dataId"
            :img="item.profile"
            class="mb-2"
          ></commit-card>
          <v-btn
            block
            :ripple="false"
            rounded
            height="52px"
            color="blue-grey darken-4"
            text
            @click="getRealtimeCommitData"
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
      // paging 처리 위함
      openGroups: [],
      tempKey: [],
      tempValue: [],
      tempArr: [],
      groupLength: 0,
      lastKey: '',
      lastValue: null,
      isFinish: false,
      ///
      perPage: 10,
      page: {
        value: '1',
      },
      isScroll: false,
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
      .once('value', (snapshot) => {
        console.log(snapshot);
        this.groupLength += snapshot.numChildren();

        console.log('groupLength: ' + this.groupLength); // 알림 총 길이
        // 최초 리스트 길이가 perPage 보다 작을 경우 최초 리스트 길이만 부르기
        if (this.groupLength < this.perPage + 1)
          this.perPage = this.groupLength - 1;

        firebase
          .database()
          .ref('noti/all')
          .limitToLast(this.perPage + 1)
          .on('child_added', (snapshot) => {
            this.getData(snapshot);
          });
      });
  },
  methods: {
    getData(snapshot) {
      if (!snapshot.val()) return;
      this.tempArr.push(snapshot.val());

      // this.tempKey = [];
      this.tempKey.push(snapshot.key); // key
      console.log('tempKey: ' + this.tempKey);

      this.tempValue.push(snapshot.val()); // 얘는 [object Object]
      // console.log('tempValue: ' + JSON.stringify(this.tempValue));
      this.lastKey = this.tempKey[0];
      // this.lastValue = this.tempValue[0];

      console.log(
        'groupLength: ' + this.groupLength + ' perPage: ' + this.perPage
      );
      if (this.groupLength < this.perPage + 1) {
        console.log('isFinish: true');
        this.isFinish = true;
        this.openGroups.push(snapshot.val()); // 원래 있던 거
      }

      if (this.tempValue.length === this.perPage + 1) {
        this.tempArr.shift();

        this.openGroups.push(...this.tempValue.reverse());
        this.groupLength -= this.perPage;
      }
    },
    getRealtimeCommitData() {
      if (this.isFinish) return;
      this.tempArr = [];
      this.tempKey = [];
      this.tempValue = [];

      // console.log('lastKey: ' + this.lastKey);
      // console.log('lastValue: ' + JSON.stringify(this.lastValue));
      firebase
        .database()
        .ref('noti/all')
        .endAt(this.lastValue, this.lastKey)
        // .endAt(this.lastKey)
        .limitToLast(this.perPage + 1)
        .on('child_added', (snapshot) => {
          this.getData(snapshot);
        });

      this.page += 1;
    },
  },
  watch: {
    // page: function() {
    //   console.log(this.page);
    //   if (this.page === 1) return this.init();
    //   this.getOpenGroupData();
    // },
  },
};
</script>

<style>
.v-messages {
  min-height: 0px;
}
</style>
