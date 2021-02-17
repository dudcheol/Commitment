<template>
  <v-container fluid class="blue-grey lighten-5">
    <!-- <v-col class="pa-0 d-flex justify-center" :class="dynamicPosition" cols="12" md="3">
      <v-sheet
        class="mt-4 d-flex flex-column pa-5 ml-md-auto"
        elevation="0"
        rounded="xl"
        max-width="300px"
      >
        <div class="pb-2">
          <h3>필터</h3>
        </div>
        <div class="d-flex justify-center mb-2">
          <div class="d-flex align-center justify-center mr-4">
            <i class="bx bxs-calendar-alt"></i>
          </div>
          <vs-select color="primary" placeholder="날짜" v-model="date">
            <vs-option label="1일" value="1">
              1일
            </vs-option>
            <vs-option label="2일" value="2">
              2일
            </vs-option>
            <vs-option label="3일" value="3">
              3일
            </vs-option>
            <vs-option label="1주" value="4">
              1주
            </vs-option>
            <vs-option label="1달" value="5">
              1달
            </vs-option>
            <vs-option label="1년" value="6">
              1년
            </vs-option>
            <vs-option label="전체" value="7">
              전체
            </vs-option>
          </vs-select>
        </div>
        <div class="domian_selector d-flex justify-center">
          <div class="d-flex align-center justify-center mr-4">
            <i class="bx bxs-landscape"></i>
          </div>
          <vs-select filter multiple placeholder="지역" v-model="options">
            <vs-option label="서울" value="1">
              서울
            </vs-option>
            <vs-option label="경기도" value="2">
              경기도
            </vs-option>
            <vs-option label="강원도" value="3">
              강원도
            </vs-option>
            <vs-option label="울산" value="4">
              울산
            </vs-option>
            <vs-option label="광주" value="5">
              광주
            </vs-option>
            <vs-option label="부산" value="6">
              부산
            </vs-option>
            <vs-option label="제주도" value="7">
              제주도
            </vs-option>
          </vs-select>
        </div>
      </v-sheet>
    </v-col> -->

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
              :text="'찜한 커밋이 없어요. 다른 사람들의 커밋에 좋아요를 남겨보세요!'"
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
    dynamicPosition() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '';
        case 'sm':
          return '';
        case 'md':
          return 'overflow-style';
        case 'lg':
          return 'overflow-style';
        case 'xl':
          return 'overflow-style';
      }
      return '';
    },
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
