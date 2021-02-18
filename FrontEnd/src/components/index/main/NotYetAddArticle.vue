<template>
  <div class="mt-4">
    <v-expansion-panels accordion flat multiple v-model="panel" class="rounded-lg">
      <v-expansion-panel style="background-color:transparent">
        <v-expansion-panel-header>
          <div class="d-flex align-center">
            <v-icon color="primary" class="pr-1">mdi-pencil-circle-outline</v-icon>
            <h2 class="ml-1 font-weight-black text-custom-bold">완료되지 않은 커밋</h2>
            <v-tooltip right color="blue-grey" transition="scroll-x-reverse-transition">
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3"
                  >mdi-information</v-icon
                >
              </template>
              <span
                >커밋만 하고 글은 작성하지 않으면 <strong><i>완료되지 않은 커밋</i></strong
                >이 돼요<br />
                여행할 땐 커밋만 하고, 나중에 글을 채워넣어보세요!<br />
                완료되지 않은 커밋은 언제든지 이곳에서 확인할 수 있어요</span
              >
            </v-tooltip>
            <span class="ml-auto">
              <span
                v-if="empCommits.length"
                class="rounded-lg pa-1 blue-grey lighten-4 ml-1 blue-grey--text"
                >{{ empCommits.length }}</span
              >
            </span>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in empCommits.slice(0, dynamicCnt)"
            :key="'emtCommit' + index"
            :username="item.address"
            :onlyAddress="true"
            :address="item.createdAt"
            :img="'lead-pencil'"
            style="height:50px;"
            class="mb-2"
            @click="clickEmpCommit(item)"
          ></commit-card>
          <no-data-card
            v-if="empCommits.length == 0"
            :icon="'emoticon-happy-outline'"
            :text="'비어있는 커밋이 없어요'"
          ></no-data-card>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import CommitCard from '../../common/card/CommitCard.vue';
import { mapGetters } from 'vuex';
import NoDataCard from '../../common/card/NoDataCard.vue';
export default {
  components: { CommitCard, NoDataCard },
  data() {
    return {
      len: 0,
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'], empCommits: ['getEmpCommitList'] }),
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
          return this.empCommits.length;
        case 'lg':
          return this.empCommits.length;
        case 'xl':
          return this.empCommits.length;
        default:
          return 5;
      }
    },
  },
  methods: {
    clickEmpCommit(item) {
      this.$store.commit('WRITE_DIALOG', {
        state: true,
        id: item.id,
        address: item.address,
      });
    },
  },
};
</script>

<style scoped></style>
