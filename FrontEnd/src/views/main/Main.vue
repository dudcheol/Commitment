<template>
  <v-container>
    <v-row>
      <v-col class="sidebar_left" cols="12" sm="2">
        <div class="d-flex justify-center" @click="clickProfile">
          <ProfileSummary />
        </div>
        <!-- 첫번째 넣어야할거 -->

        <NotYetAddArticle />
      </v-col>

      <v-col class="mainslot" cols="12" sm="8">
        <div class="mainpage">
          <!-- 일단 3개컴포넌트 넣어보고 길이 결정해보기 -->
          <FollowerMap class="mb-6 mt-3" />
          <MyState />
          <AllArticle class="my-4" />
        </div>
      </v-col>

      <v-col cols="12" sm="2">
        <div class="radar mt-4 mb-3">
          <v-slider :thumb-size="15" thumb-label="always"></v-slider>
        </div>
        <v-sheet rounded="lg" min-height="200">
          <CommitNow />
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AllArticle from '../../components/index/main/AllArticle';
import FollowerMap from '../../components/index/main/FollowerMap';
import MyState from '../../components/index/main/MyState';
import CommitNow from '../../components/index/main/CommitNow';
import NotYetAddArticle from '../../components/index/main/NotYetAddArticle';
import ProfileSummary from '../../components/index/main/ProfileSummary';

export default {
  components: {
    AllArticle,
    FollowerMap,
    MyState,
    CommitNow,
    NotYetAddArticle,
    ProfileSummary,
  },
  methods: {
    clickProfile() {
      console.log('%cIndex.vue line:5', 'color: #007acc;');
      this.$router.push({ name: 'MyPage' });
    },
  },
};
</script>

<style scoped></style>
