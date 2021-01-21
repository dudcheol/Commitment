<template>
  <div>
    <h1 style="position:fixed;">asasdfdasfaffdf</h1>
    <gmap-map
      ref="mapRef"
      map-type-id="coordinate"
      :center="center"
      :zoom="zoom"
      :options="mapStyle"
      style="width: 400px; height: 400px;"
    >
      <gmap-polyline
        v-bind:path.sync="path"
        v-bind:options="{ strokeColor: '#000' }"
      >
      </gmap-polyline>
      <gmap-polyline
        v-bind:path.sync="pathGaro"
        v-bind:options="{ strokeColor: '#000' }"
      >
      </gmap-polyline>
      <gmap-polyline
        v-bind:path.sync="pathSero"
        v-bind:options="{ strokeColor: '#000' }"
      >
      </gmap-polyline>
    </gmap-map>
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
    const tileCoord = convertLatlngToTileCoord(
      new window.google.maps.LatLng(LatLngs[i]),
      zoom
    );

    console.log(
      '%cCommitMap.vue line:67 tileCoord',
      'color: #007acc;',
      tileCoord
    );

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
  const tileCoordinate = new window.google.maps.Point(
    Math.floor((worldCoordinate.x * scale) / TILE_SIZE),
    Math.floor((worldCoordinate.y * scale) / TILE_SIZE)
  );
  return tileCoordinate;
}

function project(latLng) {
  let siny = Math.sin((latLng.lat() * Math.PI) / 180);
  // Truncating to 0.9999 effectively limits latitude to 89.189. This is
  // about a third of a tile past the edge of the world tile.
  siny = Math.min(Math.max(siny, -0.9999), 0.9999);
  return new window.google.maps.Point(
    TILE_SIZE * (0.5 + latLng.lng() / 360),
    TILE_SIZE * (0.5 - Math.log((1 + siny) / (1 - siny)) / (4 * Math.PI))
  );
}

export default {
  name: 'CommitMap',
  data() {
    return {
      zoom: 7,
      mapStyle: {
        draggable: true,
        zoomControl: false,
        mapTypeControl: true,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUi: false,
        // mapId: MAP_APP_ID,
        // styles: [{ stylers: [{ visibility: 'off' }] }],
        // backgroundColor: 'hsla(0, 0%, 0%, 0)',
      },
      center: { lat: 35.95, lng: 128.25 },
      // 상하좌우
      path: [
        { lat: 38.9, lng: 132.0 },
        { lat: 38.9, lng: 124.5 },

        { lat: 38.9, lng: 124.5 },
        { lat: 33.0, lng: 124.5 },

        { lat: 33.0, lng: 124.5 },
        { lat: 33.0, lng: 132.0 },

        { lat: 38.9, lng: 132.0 },
        { lat: 33.0, lng: 132.0 },
      ],
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
    const divideCnt = 16; // 지도가 몇개의 칸으로 나뉘어져 있는지

    const garoTop = 38.9;
    const garoBottom = 33.0;
    const garoSpace = (garoTop - garoBottom) / divideCnt;
    console.log(
      '%cCommitMap.vue line:159 garoSpace',
      'color: #007acc;',
      garoSpace
    );

    const seroLeft = 124.5;
    const seroRight = 132.0;
    const seroSpace = (seroRight - seroLeft) / divideCnt;

    // 가로줄 top->bottom 선긋기
    let topLine_LeftDot = { lat: 38.9, lng: 124.5 };
    let topLine_RightDot = { lat: 38.9, lng: 132.0 };
    let leftLine_TopDot = { lat: 38.9, lng: 124.5 };
    let leftLine_BottomDot = { lat: 33.0, lng: 124.5 };
    for (let i = 0; i < divideCnt; i++) {
      topLine_LeftDot.lat -= garoSpace;
      topLine_RightDot.lat -= garoSpace;
      leftLine_TopDot.lng += seroSpace;
      leftLine_BottomDot.lng += seroSpace;
      if (i % 2 == 0) {
        this.pathGaro.push(Object.assign({}, topLine_LeftDot));
        this.pathGaro.push(Object.assign({}, topLine_RightDot));
        this.pathSero.push(Object.assign({}, leftLine_TopDot));
        this.pathSero.push(Object.assign({}, leftLine_BottomDot));
      } else {
        this.pathGaro.push(Object.assign({}, topLine_RightDot));
        this.pathGaro.push(Object.assign({}, topLine_LeftDot));
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

        map.mapTypes.set(
          'coordinate',
          new CoordMapType(
            new window.google.maps.Size(TILE_SIZE, TILE_SIZE),
            tiles
          )
        );
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
