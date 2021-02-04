<template>
  <div>
    <div class="filtering">
      <vs-button-group>
        <vs-button flat icon>
          ALL
        </vs-button>
        <vs-button flat icon>
          ME
        </vs-button>
        <vs-button flat icon>
          FOLLOW
        </vs-button>
      </vs-button-group>
      <!-- <v-sheet class="pa-3 " rounded="xl">
        <div>
          <h3>피드 필터링</h3>
        </div>
      </v-sheet> -->
    </div>

    <div class="mt-4" v-for="content in feedDatas" :key="content">
      <main-card></main-card>
    </div>
    <infinite-loading @infinite="infiniteHandler">
      <div slot="no-more">더이상 불러올 데이터가 없습니다</div>
      <div slot="spinner">로딩중...</div>
    </infinite-loading>
  </div>
</template>

<script>
import MainCard from '../../common/card/MainCard.vue';
import InfiniteLoading from 'vue-infinite-loading';

export default {
  components: { MainCard, InfiniteLoading },

  data: () => ({
    feedDatas: [],
    limit: 0, // 무한스크롤이 진행되면서 다음에 불러올 페이지 번호
  }),
  methods: {
    infiniteHandler($state) {
      // const EACH_LEN = 5; // 5개씩 받아오기

      console.log(
        '%cAllArticle.vue line:48 인피티니 핸들러 작동!',
        'color: #007acc;'
      );

      const tmpdata = [0, 0, 0, 0, 0];
      setTimeout(() => {
        this.feedDatas = this.feedDatas.concat(tmpdata);
        $state.loaded();
        console.log(
          '%cAllArticle.vue line:52 데이터추가됨!',
          'color: #007acc;'
        );
      }, 1000);

      console.log('%cAllArticle.vue line:55 $state', 'color: #007acc;', $state);
    },
  },
};
</script>

<style scoped>
.vs-card {
  max-width: 50vh !important;
  max-height: 50vh !important;
}
</style>
