<template>
  <v-container fluid class="blue-grey lighten-5">
    <div style="position:fixed" class="d-none d-md-block">
      <v-tooltip right color="blue-grey" transition="slide-x-reverse-transition">
        <template v-slot:activator="{ on, attrs }">
          <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3">mdi-information</v-icon>
        </template>
        <span><strong>좋아요</strong>한 게시글을 모아볼 수 있어요</span>
      </v-tooltip>
    </div>
    <v-row :justify="dynamicJustify">
      <v-col class="mainslot" cols="12" md="6">
        <div class="mt-4" v-for="(data, index) in feedDatas" :key="'likesFeedData' + index">
          <main-card :data="data.board"></main-card>
        </div>
        <infinite-loading @infinite="infiniteHandler" ref="InfiniteLoading" spinner="circles">
          <div slot="no-more" class="mt-4">
            <NoDataCard :icon="'emoticon-wink-outline'" :text="'모두 보셨습니다'"></NoDataCard>
          </div>
          <!-- <div slot="spinner"></div> -->
          <div slot="no-results" class="mt-4">
            <NoDataCard
              :icon="'hand-heart-outline'"
              :text="'찜한 게시글이 없어요. 다른 사람들의 게시글에 좋아요를 남겨보세요!'"
            ></NoDataCard>
          </div>
        </infinite-loading>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MainCard from '../../components/common/card/MainCard';
import { likeBoardList } from '../../api/like';
import { mapGetters } from 'vuex';
import NoDataCard from '../../components/common/card/NoDataCard.vue';
import InfiniteLoading from 'vue-infinite-loading';

export default {
  components: {
    MainCard,
    NoDataCard,
    InfiniteLoading,
  },
  data() {
    return {
      feedDatas: [],
      date: '7',
      options: '1',
      pageNumber: 0,
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
    dynamicJustify() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '';
        case 'sm':
          return '';
        case 'md':
          return 'center';
        case 'lg':
          return 'center';
        case 'xl':
          return 'center';
      }
      return '';
    },
  },
  methods: {
    infiniteHandler($state) {
      likeBoardList(
        this.user.email,
        this.pageNumber,
        5,
        (response) => {
          if (response.data.content.length) {
            this.feedDatas.push(...response.data.content);
            this.pageNumber += 1;
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        (error) => {
          console.log(
            '%cerror Likes.vue line:95 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
  },
};
</script>

<style scoped>
.overflow-style {
  position: fixed;
  overflow-y: scroll;
  max-height: calc(100vh - 64px);
}
</style>
