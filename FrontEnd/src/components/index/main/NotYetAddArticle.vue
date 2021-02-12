<template>
  <div class="mt-4">
    <v-expansion-panels accordion flat multiple v-model="panel" class="rounded-lg">
      <v-expansion-panel style="background-color:transparent">
        <v-expansion-panel-header>
          <div>
            <h2 class="font-weight-black d-flex align-center">
              <v-icon color="primary" class="pr-1">mdi-pencil-circle-outline</v-icon>비어있는 커밋
            </h2>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <commit-card
            v-for="item in empCommits"
            :key="item"
            :username="item.address"
            :only-address="true"
            style="height:35px; width:100%"
            class="mb-2"
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
import { emptyCommit, latlngToAddress } from '../../../api/commit';
import { mapGetters } from 'vuex';
import NoDataCard from '../../common/card/NoDataCard.vue';
export default {
  components: { CommitCard, NoDataCard },
  data() {
    return {
      empCommits: [],
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
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
    emptyCommit(
      this.user.email,
      (response) => {
        const res = response.data.content;
        for (let i = 0; i < res.length; i++) {
          const item = res[i];
          console.log('%cNotYetAddArticle.vue line:65 item', 'color: #007acc;', item);
          latlngToAddress(
            { lat: item.lat, lng: item.lng },
            (response2) => {
              console.log('%cNotYetAddArticle.vue line:68 response2', 'color: #007acc;', response2);
              item['address'] = response2.data;
              this.empCommits.push(item);
            },
            (error) => {
              console.log(
                '%cerror NotYetAddArticle.vue line:64 ',
                'color: red; display: block; width: 100%;',
                error
              );
            }
          );
        }
      },
      (error) => {
        console.log(
          '%cerror NotYetAddArticle.vue line:68 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
};
</script>

<style scoped></style>
