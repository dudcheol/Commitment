<template>
  <v-container fluid class="">
    <v-row>
      <v-col class="sidebar_left pr-md-16" cols="12" md="3">
        <v-row>
          <v-col>
            <ProfileSummary />
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <NotYetAddArticle />
          </v-col>
        </v-row>
      </v-col>

      <v-col class="mainslot" cols="12" md="6">
        <div class="mainpage">
          <FollowerMap class="mb-6 mt-3" />
          <MyState />
          <AllArticle class="my-4" />
        </div>
      </v-col>

      <v-col cols="12" md="3">
        <CommitNow />
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
