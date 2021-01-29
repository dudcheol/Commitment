<template>
  <div>
    <map-national
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="national_data"
      @square-click="click"
    ></map-national>
    <input v-model="national_lat" /><input v-model="national_lng" /><input
      v-model="national_commit"
    /><button @click="national">
      입력
    </button>

    <hr />

    <map-seoul
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="seoul_data"
      @square-click="click"
    ></map-seoul>

    <hr />

    <map-busan
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-busan>

    <hr />

    <map-gangwon
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gangwon>

    <hr />

    <map-gwangju
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gwangju>

    <hr />

    <map-gyeonggi
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gyeonggi>

    <hr />

    <map-ulsan
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-ulsan>
  </div>
</template>

<script>
import MapBusan from '../components/MapBusan.vue';
import MapGangwon from '../components/MapGangwon.vue';
import MapGwangju from '../components/MapGwangju.vue';
import MapGyeonggi from '../components/MapGyeonggi.vue';
import MapNational from '../components/MapNational.vue';
import MapSeoul from '../components/MapSeoul.vue';
import MapUlsan from '../components/MapUlsan.vue';

let tileSize = 40000; // 20km
const METER_PER_LAT = 88740; // 경도 1도당 미터
const METER_PER_LNG = 110000; // 위도 1도당 미터

// tile의 사이즈
let spacingR = tileSize * (1 / METER_PER_LAT);
let spacingC = tileSize * (1 / METER_PER_LNG);

// 우리나라의 크기(울릉도,독도,제주도 포함)
let latmin = 33.0;
let latmax = 38.9;
let lngmin = 124.5;
let lngmax = 132.0;

let C = parseInt((latmax - latmin) / spacingC); //위도 셀 개수 (세로)
let R = parseInt((lngmax - lngmin) / spacingR); //경도 셀 개수 (가로)

export default {
  components: { MapNational, MapBusan, MapGangwon, MapSeoul, MapUlsan, MapGyeonggi, MapGwangju },
  data() {
    return {
      // temp data
      national_data: [
        // [12, 14, 2],
        // [12, 15, 5],
        // [12, 16, 1],
      ],
      seoul_data: [],
      national_lat: 0.0,
      national_lng: 0.0,
      national_commit: 0,
    };
  },
  methods: {
    click(obj) {
      console.log('%cCommitMapTest.vue line:14 obj', 'color: #007acc;', obj);
      alert(JSON.stringify(obj));
    },
    national() {
      // 우리나라
      // tile의 사이즈
      tileSize = 20000; // 20km
      spacingR = tileSize * (1 / METER_PER_LAT);
      spacingC = tileSize * (1 / METER_PER_LNG);

      // 우리나라의 크기(울릉도,독도,제주도 포함)
      latmin = 33.0;
      latmax = 38.9;
      lngmin = 124.5;
      lngmax = 132.0;

      C = parseInt((latmax - latmin) / spacingC); //위도 셀 개수 (세로)
      R = parseInt((lngmax - lngmin) / spacingR); //경도 셀 개수 (가로)

      this.national_data.push([
        this.convertLatToC(this.national_lat),
        this.convertLngToR(this.national_lng),
        this.national_commit,
      ]);

      // 서울
      // tile의 사이즈
      tileSize = 1000; // 1km
      spacingR = tileSize * (1 / METER_PER_LAT);
      spacingC = tileSize * (1 / METER_PER_LNG);

      // 크기
      latmin = 37.413294;
      latmax = 37.715133;
      lngmin = 126.734086;
      lngmax = 127.269311;

      C = parseInt((latmax - latmin) / spacingC); //위도 셀 개수 (세로)
      R = parseInt((lngmax - lngmin) / spacingR); //경도 셀 개수 (가로)

      this.seoul_data.push([
        this.convertLatToC(this.national_lat),
        this.convertLngToR(this.national_lng),
        this.national_commit,
      ]);
    },
    convertLatToC(targetLat) {
      const ret = parseInt((targetLat - latmin) / spacingC);
      return C - ret >= C ? C - 1 : C - ret;
    },
    convertLngToR(targetLng) {
      const ret = parseInt((targetLng - lngmin) / spacingR);
      return ret >= R ? R - 1 : ret;
    },
  },
};
</script>
