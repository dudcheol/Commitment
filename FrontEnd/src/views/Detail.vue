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

    <div class="article">
      <ArticleDetail />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import ArticleDetail from './../components/detail/ArticleDetail';

export default {
  components: {
    ArticleDetail,
  },
  data() {
    return {
      map: null,
    };
  },
  computed: {
    ...mapGetters({ data: ['getBoardDetail'] }),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.mapLoad();
    }
  },
  methods: {
    ...mapActions(['BOARDDETAIL']),
    initMap() {
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
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e520a5002a5c5b259e5fb6722912402d';
      document.head.appendChild(script);
    },
    back() {
      this.$router.back();
    },
    async dataLoad() {
      await this.BOARDDETAIL(this.$route.params.id);
      if (this.map)
        this.map.setCenter(new kakao.maps.LatLng(this.data.commit.lat, this.data.commit.lng));
      else this.initMap();
    },
  },
  activated() {
    window.scrollTo(0, 0);
    this.dataLoad();
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
