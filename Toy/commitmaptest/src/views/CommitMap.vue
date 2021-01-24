<template>
  <div>
    <h1 style="position:fixed;">asasdfdasfaffdf</h1>
    <gmap-map ref="mapRef" map-type-id="coordinate" :center="center" :zoom="zoom" :options="mapStyle" style="width: 1200px; height: 1000px;">
      <gmap-polyline v-bind:path.sync="path" v-bind:options="{ strokeColor: '#000' }"> </gmap-polyline>
      <gmap-polyline v-bind:path.sync="pathGaro" v-bind:options="{ strokeColor: '#000' }"> </gmap-polyline>
      <gmap-polyline v-bind:path.sync="pathSero" v-bind:options="{ strokeColor: '#000' }"> </gmap-polyline>
    </gmap-map>
    <h1>zoom:{{ zoomText }}</h1>
  </div>
</template>

<script>
const TILE_ZOOM = 6; // 타일의 크기는 TILE_ZOOM으로 컨트롤할 것
const TILE_STANDARD = 8;
const TILE_SIZE = 1 << TILE_ZOOM;
// var google = window.google;

class CoordMapType {
  constructor(tileSize, commitTiles) {
    this.maxZoom = 19;
    this.name = 'Tile #s';
    this.alt = 'Tile Coordinate Map Type';
    this.tileSize = tileSize;
    this.commitTiles = commitTiles;
  }
  getTile(coord, zoom, ownerDocument) {
    if (this.commitTiles[String(coord)]) {
      const div = ownerDocument.createElement('div');
      div.innerHTML = String(coord);
      div.style.width = this.tileSize.width - 5 + 'px';
      div.style.height = this.tileSize.height - 5 + 'px';
      div.style.fontSize = '10';
      div.style.borderRadius = '5px';
      div.style.backgroundColor = '#f00';
      div.addEventListener('click', function() {
        alert('click');
      });
      return div;
    }
    return null;
  }
  // zoom 변경될때 타일이 바뀌면 바뀌는 타일들 릴리즈
  // releaseTile(tile, a) {
  //   console.log('%cCommitMap.vue line:43 tile', 'color: #007acc;', tile);
  //   console.log('%cCommitMap.vue line:53 a', 'color: #007acc;', a);
  // }
}

function createTiles(LatLngs, zoom) {
  var tiles = new Array();

  for (let i = 0; i < LatLngs.length; i++) {
    const tileCoord = convertLatlngToTileCoord(new window.google.maps.LatLng(LatLngs[i]), zoom);

    // console.log('%cCommitMap.vue line:67 tileCoord', 'color: #007acc;', tileCoord);

    const exist = tiles[String(tileCoord)];
    if (exist) {
      tiles[String(tileCoord)] = exist + 1;
    } else {
      tiles[String(tileCoord)] = 1;
    }
  }
  return tiles;
}

function convertLatlngToTileCoord(latLng, zoom) {
  // console.log('%cCommitMap.vue line:132 zoom', 'color: #007acc;', zoom);
  const scale = 1 << (zoom + (TILE_STANDARD - TILE_ZOOM)); // 타일사이즈 2^8 기준
  // console.log('%cCommitMap.vue line:133 scale', 'color: #007acc;', scale);
  // console.log(
  //   '%cCommitMap.vue line:90 TILE_SIZE',
  //   'color: #007acc;',
  //   TILE_SIZE
  // );
  const worldCoordinate = project(latLng);
  const tileCoordinate = new window.google.maps.Point(Math.floor((worldCoordinate.x * scale) / TILE_SIZE), Math.floor((worldCoordinate.y * scale) / TILE_SIZE));
  return tileCoordinate;
}

function project(latLng) {
  let siny = Math.sin((latLng.lat() * Math.PI) / 180);
  // Truncating to 0.9999 effectively limits latitude to 89.189. This is
  // about a third of a tile past the edge of the world tile.
  siny = Math.min(Math.max(siny, -0.9999), 0.9999);
  return new window.google.maps.Point(TILE_SIZE * (0.5 + latLng.lng() / 360), TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI)));
}

export default {
  name: 'CommitMap',
  data() {
    return {
      zoom: 11,
      zoomText: '',
      mapStyle: {
        draggable: true,
        // zoomControl: false,
        // mapTypeControl: true,
        // scaleControl: false,
        // streetViewControl: false,
        // rotateControl: false,
        // fullscreenControl: false,
        // disableDefaultUi: false,
        // mapId: MAP_APP_ID,
        // styles: [{ stylers: [{ visibility: 'off' }] }],
        // backgroundColor: 'hsla(0, 0%, 0%, 0)',
      },
      center: { lat: 35.95, lng: 128.25 }, // 대한민국
      // center: { lat: 37.5642135, lng: 127.0016985 }, // 서울
      // 상하좌우
      path: [],
      pathGaro: [],
      pathSero: [],
      tileData: [
        { lat: 38.9, lng: 132.0 },
        { lat: 38.9, lng: 124.5 },

        { lat: 38.9, lng: 124.5 },
        { lat: 33.0, lng: 124.5 },

        { lat: 33.0, lng: 124.5 },
        { lat: 33.0, lng: 132.0 },

        { lat: 38.9, lng: 132.0 },
        { lat: 33.0, lng: 132.0 },
      ],
      // tiles: [],
    };
  },
  computed: {},
  watch: {
    // zoom: function(val) {
    //   console.log('%cCommitMap.vue line:147 val', 'color: #007acc;', val);
    // },
  },
  created() {
    // this.zoom = 13;

    //우리나라
    const divideGaro = 32; // 지도가 몇개의 칸으로 나뉘어져 있는지
    const divideSero = 33;
    (this.center = { lat: 35.95, lng: 128.25 }), // 대한민국
      (this.zoom = 7);
    const garoTop = 38.9;
    const garoBottom = 33.0;
    const garoSpace = (garoTop - garoBottom) / divideGaro;
    const seroLeft = 124.5;
    const seroRight = 132.0;
    const seroSpace = (seroRight - seroLeft) / divideSero;

    //서울
    // const divideGaro = 33; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 47;
    // this.center = { lat: 37.5642135, lng: 127.0016985 };
    // this.zoom = 11;
    // const garoTop = 37.715133;
    // const garoBottom = 37.413294;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 126.734086;
    // const seroRight = 127.269311;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    //경기
    // const divideGaro = 52; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 46;
    // this.center = { lat: 37.5864315, lng: 127.0462765 };
    // this.zoom = 9;
    // const garoTop = 38.300603;
    // const garoBottom = 36.87226;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 126.262021;
    // const seroRight = 127.830532;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    //강원
    // const divideGaro = 44; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 50;
    // this.center = { lat: 37.8304115, lng: 128.2260705 };
    // this.zoom = 9;
    // const garoTop = 38.642618;
    // const garoBottom = 37.018205;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 127.080231;
    // const seroRight = 129.37191;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    // 부산
    // const divideGaro = 47; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 46;
    // this.center = { lat: 35.1379222, lng: 129.05562775 };
    // this.zoom = 11;
    // const garoTop = 35.3959361;
    // const garoBottom = 34.8799083;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 128.7384361;
    // const seroRight = 129.3728194;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    // 광주
    // const divideGaro = 27; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 44;
    // this.center = { lat: 35.1, lng: 126.8 };
    // this.zoom = 11;
    // const garoTop = 35.2;
    // const garoBottom = 35.0;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 126.6;
    // const seroRight = 127;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    // 울산
    // const divideGaro = 46; // 지도가 몇개의 칸으로 나뉘어져 있는지
    // const divideSero = 46;
    // this.center = { lat: 35.52534995, lng: 129.22244165 };
    // this.zoom = 11;
    // const garoTop = 35.7376583;
    // const garoBottom = 35.3130416;
    // let garoSpace = (garoTop - garoBottom) / divideGaro;
    // const seroLeft = 128.9607861;
    // const seroRight = 129.4840972;
    // let seroSpace = (seroRight - seroLeft) / divideSero;

    console.log('%cCommitMap.vue line:157 ', 'color: #007acc;', garoSpace + '/' + seroSpace);

    this.path = [
      { lat: garoTop, lng: seroRight },
      { lat: garoTop, lng: seroLeft },

      { lat: garoTop, lng: seroLeft },
      { lat: garoBottom, lng: seroLeft },

      { lat: garoBottom, lng: seroLeft },
      { lat: garoBottom, lng: seroRight },

      { lat: garoTop, lng: seroRight },
      { lat: garoBottom, lng: seroRight },
    ];

    // 가로줄 top->bottom 선긋기
    let topLine_LeftDot = { lat: garoTop, lng: seroLeft };
    let topLine_RightDot = { lat: garoTop, lng: seroRight };
    let leftLine_TopDot = { lat: garoTop, lng: seroLeft };
    let leftLine_BottomDot = { lat: garoBottom, lng: seroLeft };
    for (let i = 0; i < divideGaro; i++) {
      topLine_LeftDot.lat -= garoSpace;
      topLine_RightDot.lat -= garoSpace;
      if (i % 2 == 0) {
        this.pathGaro.push(Object.assign({}, topLine_LeftDot));
        this.pathGaro.push(Object.assign({}, topLine_RightDot));
      } else {
        this.pathGaro.push(Object.assign({}, topLine_RightDot));
        this.pathGaro.push(Object.assign({}, topLine_LeftDot));
      }
    }

    for (let i = 0; i < divideSero; i++) {
      leftLine_TopDot.lng += seroSpace;
      leftLine_BottomDot.lng += seroSpace;
      if (i % 2 == 0) {
        this.pathSero.push(Object.assign({}, leftLine_TopDot));
        this.pathSero.push(Object.assign({}, leftLine_BottomDot));
      } else {
        this.pathSero.push(Object.assign({}, leftLine_BottomDot));
        this.pathSero.push(Object.assign({}, leftLine_TopDot));
      }
    }
  },
  mounted() {
    // map보다 먼저 실행되서 오류발생
    // this.mapStyle['mapTypeControlOptions'] = {
    //   mapTypeIds: ['coordinate', 'roadmap'],
    //   style: window.google.maps.MapTypeControlStyle.DROPDOWN_MENU,
    // };
    console.log('%cCommitMap.vue line:159 mountedzz', 'color: #007acc;');
    this.$refs.mapRef.$mapPromise.then((map) => {
      map.addListener('maptypeid_changed', () => {
        const showStreetViewControl = map.getMapTypeId() !== 'coordinate';
        map.setOptions({
          streetViewControl: showStreetViewControl,
        });
      });

      map.addListener('bounds_changed', () => {
        var tiles = createTiles(this.tileData, map.getZoom());
        this.zoomText = map.getZoom();

        map.mapTypes.set('coordinate', new CoordMapType(new window.google.maps.Size(TILE_SIZE, TILE_SIZE), tiles));
      });
    });
  },
  methods: {},
};
</script>

<style scoped>
#id {
  width: 100px;
  height: 100px;
}
</style>
