<template>
  <div class="mt-4">
    <v-expansion-panels accordion flat multiple v-model="panel" class="rounded-lg">
      <v-expansion-panel style="background-color:transparent">
        <v-expansion-panel-header>
          <div>
            <h2 class="font-weight-black d-flex align-center">
              <v-icon color="primary" class="pr-1">mdi-pencil-circle-outline</v-icon>비어있는 커밋
              <span class="rounded-lg pa-1 blue-grey lighten-4 ml-1 primary--text">{{
                empCommits.length
              }}</span>
            </h2>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in empCommits"
            :key="'emtCommit' + index"
            :username="item.address"
            :only-address="true"
            :address="item.createdAt"
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
