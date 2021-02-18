<template>
  <div class="d-flex justify-center">
    <vs-card @click="goToMyPage" style="max-width:260px">
      <template #title>
        <div>
          <component
            :is="user.region_name"
            :size="custom.size"
            :borderRadius="custom.borderRadius"
            :spacing="custom.spacing"
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
  components: {
    national: () => import('../../../components/common/map/MapNational'),
    busan: () => import('../../../components/common/map/MapBusan'),
    gangwon: () => import('../../../components/common/map/MapGangwon'),
    gwangju: () => import('../../../components/common/map/MapGwangju'),
    gyeonggi: () => import('../../../components/common/map/MapGyeonggi'),
    seoul: () => import('../../../components/common/map/MapSeoul'),
    ulsan: () => import('../../../components/common/map/MapUlsan'),
  },
  data: () => ({
    commitCnt: 12,
    badgeCnt: 7,
    followerCnt: 210,
    mapType: null,
    datas: [],
  }),
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
    custom() {
      switch (this.user.region_name) {
        case 'national':
          return { size: 5, borderRadius: 5, spacing: 1 };
        case 'seoul':
          return { size: 4, borderRadius: 1, spacing: 1 };
        case 'busan':
          return { size: 4, borderRadius: 1, spacing: 0.5 };
        case 'gyeonggi':
          return { size: 4, borderRadius: 1, spacing: 0.5 };
        case 'gangwon':
          return { size: 3, borderRadius: 0, spacing: 0.5 };
        case 'ulsan':
          return { size: 4, borderRadius: 1, spacing: 0.5 };
        case 'gwangju':
          return { size: 4, borderRadius: 1, spacing: 1 };
        default:
          return { size: 3, borderRadius: 0, spacing: 0.5 };
      }
    },
  },
  watch: {
    user() {
      this.datas = [];
      userCommitMap(
        this.user.email,
        (response) => {
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
    },
  },
  methods: {
    goToMyPage() {
      this.$store.commit('SELECTED_USER_ID', this.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
  },
  mounted() {
    this.datas = [];
    userCommitMap(
      this.user.email,
      (response) => {
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
