<template>
  <div class="d-flex justify-center" @click="goToMyPage">
    <vs-card>
      <template #title>
        <div>
          <component :is="mapType" :size="5" :borderRadius="5" :spacing="1"></component>
        </div>
      </template>
      <template #text>
        <h2 class="text-center">{{ userInfo.nickname }}</h2>
        <div class="text-center">
          {{ userInfo.mystory }}
        </div>
        <div class="d-flex flex-row justify-center">
          <vs-button size="l" circle icon color="success" flat>
            <i class="bx bxs-check-square"></i>{{ userInfo.commitCnt }}
          </vs-button>
          <vs-button size="l" circle icon color="danger" flat>
            <i class="bx bxs-heart"></i>{{ userInfo.followerCnt }}
          </vs-button>
          <vs-button size="l" circle icon color="warning" flat>
            <i class="bx bxs-medal"></i>{{ userInfo.badgeCnt }}
          </vs-button>
        </div>
      </template>
      <template #interactions> </template>
    </vs-card>
  </div>
</template>

<script>
export default {
  props: ['userInfo'],
  data: () => ({
    commitCnt: 12,
    badgeCnt: 7,
    followerCnt: 210,
    mapType: null,
  }),
  methods: {
    goToMyPage() {
      console.log('%cIndex.vue line:5', 'color: #007acc;');
      this.$router.push({ name: 'MyPage' });
    },
  },
  computed: {
    loader() {
      if (!this.userInfo.region_name) {
        return null;
      }
      return () =>
        import(
          `../../../components/common/map/Map${this.userInfo.region_name.replace(
            /\b[a-z]/,
            (letter) => letter.toUpperCase()
          )}`
        );
    },
  },
  mounted() {
    this.loader()
      .then(() => {
        this.mapType = () => this.loader();
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
