<template>
  <div>
    <div class="filtering d-flex justify-center">
      <v-btn-toggle mandatory dense borderless rounded v-model="toggle">
        <v-btn small color="primary" :ripple="false" text>ALL</v-btn>
        <v-btn small color="primary" :ripple="false" text>ME</v-btn>
        <v-btn small color="primary" :ripple="false" text>FOLLOW</v-btn>
      </v-btn-toggle>
    </div>

    <div class="mt-4" v-for="content in feedDatas" :key="content">
      <main-card></main-card>
    </div>
    <infinite-loading @infinite="infiniteHandler" ref="infiniteLoading">
      <div slot="no-more">더이상 불러올 데이터가 없습니다</div>
      <div slot="spinner">로딩중...</div>
      <div slot="no-results">결과가 없습니다</div>
    </infinite-loading>
  </div>
</template>

<script>
import MainCard from '../../common/card/MainCard.vue';
import InfiniteLoading from 'vue-infinite-loading';
import { myBoardList, followingBoardList, totalBoadList } from '../../../api/board';
import { mapGetters } from 'vuex';

export default {
  components: { MainCard, InfiniteLoading },

  data: () => ({
    feedDatas: [],
    toggle: 0,
    pageNumber: 0, // 무한스크롤이 진행되면서 다음에 불러올 페이지 번호
    pageSize: 5,
  }),
  watch: {
    toggle: function() {
      this.feedDatas = [];
      this.pageNumber = 0;
      this.$refs.infiniteLoading.stateChanger.reset();
    },
  },
  computed: {
    ...mapGetters({ userInfo: ['getUserInfo'] }),
  },
  methods: {
    infiniteHandler($state) {
      switch (this.toggle) {
        case 0:
          totalBoadList(
            {
              email: this.userInfo.email,
              size: this.pageSize,
              page: this.pageNumber,
            },
            (response) => {
              this.feedDatas = this.feedDatas.concat(response.data);
              if (response.data.length < this.pageSize) {
                $state.complete();
              } else {
                $state.loaded();
                this.pageNumber++;
              }
            },
            (error) => {
              console.log(
                '%cerror AllArticle.vue line:81 ',
                'color: red; display: block; width: 100%;',
                error
              );
            }
          );
          break;
        case 1:
          myBoardList(
            {
              email: this.userInfo.email,
              size: this.pageSize,
              page: this.pageNumber,
            },
            (response) => {
              this.feedDatas = this.feedDatas.concat(response.data);
              if (response.data.length < this.pageSize) {
                $state.complete();
              } else {
                $state.loaded();
                this.pageNumber++;
              }
            },
            (error) => {
              console.log(
                '%cerror AllArticle.vue line:85 ',
                'color: red; display: block; width: 100%;',
                error
              );
            }
          );
          break;
        case 2:
          followingBoardList(
            {
              email: this.userInfo.email,
              size: this.pageSize,
              page: this.pageNumber,
            },
            (response) => {
              this.feedDatas = this.feedDatas.concat(response.data);
              if (response.data.length < this.pageSize) {
                $state.complete();
              } else {
                $state.loaded();
                this.pageNumber++;
              }
            },
            (error) => {
              console.log(
                '%cerror AllArticle.vue line:102 ',
                'color: red; display: block; width: 100%;',
                error
              );
            }
          );
          break;
      }
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
