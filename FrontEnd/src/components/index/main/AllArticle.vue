<template>
  <div>
    <div class="filtering d-flex justify-center">
      <v-btn-toggle mandatory dense borderless rounded v-model="toggle">
        <v-btn small color="primary" :ripple="false" text>ALL</v-btn>
        <v-btn small color="primary" :ripple="false" text>ME</v-btn>
        <v-btn small color="primary" :ripple="false" text>FOLLOW</v-btn>
      </v-btn-toggle>
    </div>

    <div class="mt-4" v-for="(data, index) in feedDatas" :key="'mainCard' + index">
      <main-card :data="data"></main-card>
    </div>
    <infinite-loading @infinite="infiniteHandler" ref="infiniteLoading" spinner="circles">
      <div slot="no-more" class="mt-4">
        <NoDataCard :icon="'emoticon-wink-outline'" :text="'모두 보셨습니다'"></NoDataCard>
      </div>
      <!-- <div slot="spinner"></div> -->
      <div slot="no-results" class="mt-4">
        <NoDataCard :icon="'emoticon-cry-outline'" :text="'불러올 게시글이 없어요'"></NoDataCard>
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import MainCard from '../../common/card/MainCard.vue';
import NoDataCard from '../../common/card/NoDataCard.vue';
import InfiniteLoading from 'vue-infinite-loading';
import { myBoardList, followingBoardList, totalBoadList } from '../../../api/board';
import { mapGetters } from 'vuex';

export default {
  components: { MainCard, InfiniteLoading, NoDataCard },

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
              if (response.data.content.length > 0) {
                this.feedDatas = this.feedDatas.concat(response.data.content);
                $state.loaded();
                this.pageNumber++;
              } else {
                $state.complete();
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
              if (response.data.content.length > 0) {
                this.feedDatas = this.feedDatas.concat(response.data.content);
                $state.loaded();
                this.pageNumber++;
              } else {
                $state.complete();
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
              if (response.data.content.length > 0) {
                this.feedDatas = this.feedDatas.concat(response.data.content);
                $state.loaded();
                this.pageNumber++;
              } else {
                $state.complete();
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
