<template>
  <v-container class="blue-grey lighten-5 pa-0" fluid>
    <div class="back_button">
      <v-btn @click="back" color="white" :ripple="false" fab elevation="3" fixed>
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
    </div>

    <div v-bind="attrs" v-on="on" class="bottom2" id="mobileHidden">
      <Timeline />
    </div>
    <v-container class="py-8 px-0 white rounded-t-xl mt-12 mt-md-0">
      <v-row no-gutters>
        <v-col>
          <ProfileInfo />
        </v-col>
      </v-row>

      <v-row no-gutters class="py-4 pr-md-4">
        <map-list></map-list>
      </v-row>

      <div id="pcHidden" class="pt-4">
        <MyImagesMobile />
      </div>
    </v-container>
  </v-container>
</template>

<script>
import ProfileInfo from '../components/index/mypage/ProfileInfo';
import Timeline from '../components/index/mypage/Timeline';
import MyImagesMobile from '../components/index/mypage/MyImagesMobile';
import MapList from '../components/index/mypage/MapList.vue';
import { mapGetters } from 'vuex';

export default {
  name: 'MyPage',
  components: {
    ProfileInfo,
    Timeline,
    MyImagesMobile,
    MapList,
  },
  data: () => ({
    benched: 100,
    mapList: ['전국', '서울', '부산', '경기', '강원', '울산', '광주'],
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
    colors: ['indigo', 'warning', 'pink darken-2', 'red lighten-1', 'deep-purple accent-4'],
    value1: 1,
    picked: 0,
  }),
  computed: {
    ...mapGetters({ userId: ['getSelectedUserId'] }),
    length() {
      return 7000;
    },
    width() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '500px';
        case 'sm':
          return '500px';
        case 'md':
          return '500px';
        case 'lg':
          return '700px';
        case 'xl':
          return '800px';
      }
      return 700;
    },
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
  },
  activated() {
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped>
@media screen and (max-width: 1000px) {
  #mobileHidden {
    display: none;
  }
}
@media screen and (min-width: 1000px) {
  #pcHidden {
    display: none;
  }
}
.back_button {
  display: block;
  position: absolute;
  left: 25px;
  top: 20px;
  z-index: 1;
}
</style>
