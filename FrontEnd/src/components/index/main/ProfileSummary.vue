<template>
  <div class="d-flex justify-center">
    <vs-card @click="goToMyPage">
      <template #title>
        <div>
          <component
            :is="mapType"
            :size="5"
            :borderRadius="5"
            :spacing="1"
            :datas="datas"
          ></component>
        </div>
      </template>
      <template #text>
        <h2 class="text-center">{{ user.nickname }}</h2>
        <div class="text-center">
          {{ user.mystory }}
        </div>
        <div class="d-flex flex-row justify-center">
          <vs-button size="l" circle icon color="success" flat>
            <i class="bx bxs-check-square"></i>{{ user.commitCnt }}
          </vs-button>
          <vs-button size="l" circle icon color="danger" flat>
            <i class="bx bxs-heart"></i>{{ user.followerCnt }}
          </vs-button>
          <vs-button size="l" circle icon color="warning" flat>
            <i class="bx bxs-medal"></i>{{ user.badgeCnt }}
          </vs-button>
        </div>
      </template>
      <template #interactions> </template>
    </vs-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { userCommitMap } from '../../../api/commit';
export default {
  data: () => ({
    commitCnt: 12,
    badgeCnt: 7,
    followerCnt: 210,
    mapType: null,
    datas: [],
  }),
  methods: {
    goToMyPage() {
      console.log('%cIndex.vue line:5', 'color: #007acc;');
      this.$router.push({ name: 'MyPage' });
    },
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
    loader() {
      if (!this.user.region_name) {
        return null;
      }
      return () =>
        import(
          `../../../components/common/map/Map${this.user.region_name.replace(/\b[a-z]/, (letter) =>
            letter.toUpperCase()
          )}`
        );
    },
  },
  mounted() {
    this.loader()
      .then(() => {
        this.mapType = () => this.loader();
        userCommitMap(
          this.user.email,
          (response) => {
            console.log('%cProfileSummary.vue line:69 response', 'color: #007acc;', response);
            this.datas.push(...response.data.commitXY);
          },
          (error) => {
            console.log(
              '%cerror ProfileSummary.vue line:68 ',
              'color: red; display: block; width: 100%;',
              error
            );
          }
        );
      })
      .catch(() => {
        console.log('%cProfileSummary.vue line:61 지도를 불러오는데 실패함', 'color: #007acc;');
      });
  },
};
</script>

<style scoped>
.vs-card__title {
  display: flex;
  justify-content: center;
}
.profile {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.profile_buttons {
  display: flex;
  justify-content: center;
}
</style>
