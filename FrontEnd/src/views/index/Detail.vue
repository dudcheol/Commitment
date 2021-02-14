<template>
  <div class="grid-container">
    <div class="google_map">
      <div class="back_button">
        <vs-button icon relief>
          <i class="bx bx-arrow-back"></i>
        </vs-button>
      </div>
      <div id="map" class="kakao_map"></div>
    </div>

    <div class="article">
      <ArticleDetail />
    </div>
  </div>
</template>

<script>
import ArticleDetail from './../../components/detail/ArticleDetail';

export default {

  components: {
    ArticleDetail,
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e520a5002a5c5b259e5fb6722912402d';
      document.head.appendChild(script);
    }
  },

  methods: {
    initMap() {
      let mapContainer = document.getElementById('map'); // 지도를 표시할 div
      let mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        draggable: false, // 지도 드래그
        setZoomable: false,
        level: 3,
      };

      let map = new kakao.maps.Map(mapContainer, mapOption);

      return map;
    },
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

.article {
  grid-area: article;
}

.google_map .back_button {
  display: block;
  position: absolute;
  left: 25px;
  top: 20px;
  z-index: 1;
}

.my_location {
  font-size: 15px;
  background: rgba(220, 220, 220, 0.5);
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

</style>
