<template>
  <div>
    <h1 style="position:fixed;">asasdfdasfaffdf</h1>
    <gmap-map
      ref="mapRef"
      map-type-id="coordinate"
      :center="center"
      :zoom="zoom"
      :options="mapStyle"
      style="width: 300px; height: 300px;"
    >
      <gmap-polyline
        v-bind:path.sync="path"
        v-bind:options="{ strokeColor: '#000000' }"
      >
      </gmap-polyline>
    </gmap-map>
  </div>
</template>

<script>
const TILE_SIZE = 1 << 5;
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
    const div = ownerDocument.createElement('div');
    // div.innerHTML = String(coord);

    div.style.width = this.tileSize.width - 5 + 'px';
    div.style.height = this.tileSize.height - 5 + 'px';
    div.style.fontSize = '10';
    // div.style.borderStyle = 'solid';
    // div.style.borderWidth = '1px';
    // div.style.borderColor = '#dcdcdc';
    div.style.borderRadius = '5px';
    // div.style.margin = '5px';

    if (this.commitTiles[String(coord)]) {
      div.style.backgroundColor = '#f00';
    } else {
      div.style.backgroundColor = '#dcdcdc';
    }
    return div;
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
  console.log('%cCommitMap.vue line:132 zoom', 'color: #007acc;', zoom);
  const scale = 1 << (zoom + 2);
  console.log('%cCommitMap.vue line:133 scale', 'color: #007acc;', scale);
  const worldCoordinate = project(latLng);
  // const pixelCoordinate = new window.google.maps.Point(
  //   Math.floor(worldCoordinate.x * scale),
  //   Math.floor(worldCoordinate.y * scale)
  // );
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
        // draggable: false,
        zoomControl: false,
        mapTypeControl: true,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUi: false,
        // mapId: MAP_APP_ID,
        styles: [{ stylers: [{ visibility: 'off' }] }],
        backgroundColor: 'hsla(0, 0%, 0%, 0)',
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
      // tiles: [],
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    // map보다 먼저 실행되서 오류발생
    // this.mapStyle['mapTypeControlOptions'] = {
    //   mapTypeIds: ['coordinate', 'roadmap'],
    //   style: window.google.maps.MapTypeControlStyle.DROPDOWN_MENU,
    // };
    console.log('%cCommitMap.vue line:159 mountedzz', 'color: #007acc;');
    this.$refs.mapRef.$mapPromise.then((map) => {
      var tiles = createTiles(this.path, this.zoom + 1);

      map.addListener('maptypeid_changed', () => {
        const showStreetViewControl = map.getMapTypeId() !== 'coordinate';
        map.setOptions({
          streetViewControl: showStreetViewControl,
        });
      });

      map.mapTypes.set(
        'coordinate',
        new CoordMapType(
          new window.google.maps.Size(TILE_SIZE, TILE_SIZE),
          tiles
        )
      );
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
