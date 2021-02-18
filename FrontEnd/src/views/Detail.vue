<template>
  <div class="grid-container">
    <div class="google_map">
      <div class="back_button">
        <v-btn @click="back" color="white" :ripple="false" fab elevation="3" fixed>
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
      </div>
      <div id="map" class="kakao_map"></div>
    </div>
    <div class="article" v-if="data">
      <ArticleDetail />
    </div>
    <div v-else class="pa-4">
      <no-data-card
        :icon="'alert-circle'"
        :text="'게시글이 존재하지 않아요'"
        style="height:200px"
      ></no-data-card>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import NoDataCard from '../components/common/card/NoDataCard.vue';
import ArticleDetail from './../components/detail/ArticleDetail';

export default {
  components: {
    ArticleDetail,
    NoDataCard,
  },
  data() {
    return {
      map: null,
      errorDialog: false,
    };
  },
  computed: {
    ...mapGetters({ data: ['getBoardDetail'], boardId: ['getSelectedBoardId'] }),
  },
  methods: {
    ...mapActions(['BOARDDETAIL']),
    async initMap() {
      await this.BOARDDETAIL(this.boardId);

      let mapContainer = document.getElementById('map'); // 지도를 표시할 div
      let mapOption = {
        center: new kakao.maps.LatLng(this.data.commit.lat, this.data.commit.lng), // 지도의 중심좌표
        draggable: false, // 지도 드래그
        setZoomable: false,
        level: 3,
      };
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      return this.map;
    },
    mapLoad() {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=1e373ca7ca88ca8e7e80c9a86b64a02c';
      document.head.appendChild(script);
    },
    back() {
      this.$router.go(-1);
    },
  },
  activated() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.mapLoad();
    }
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped>
.map {
  position: relative;
}

.kakao_map {
  height: 35vh;
  z-index: 0;
}

.google_map .back_button {
  display: block;
  position: absolute;
  left: 25px;
  top: 20px;
  z-index: 1;
}

@media (max-width: 450px) {
  .google_map .back_button {
    display: block;
    position: absolute;
    left: 15px;
    top: 20px;
    z-index: 1;
  }
}
</style>
