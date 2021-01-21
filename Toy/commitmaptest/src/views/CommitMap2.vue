<template>
  <div>
    <div class="line" v-for="(line, i) in tiles" :key="i">
      <div
        :class="[cellStyle, drawCellColor(cell)]"
        v-for="(cell, j) in line"
        :key="j"
      >
        <!-- {{ i }}, {{ j }} -->
      </div>
    </div>
  </div>
</template>

<script>
// 대한민국의 크기
// 124.5 38.9 좌상
// 132.0 33.0 우하
// 200x200 격자 만들기
const TILE_SIZE = 40000; // 20km
const METER_PER_LAT = 88740; // 경도 1도당 미터
const METER_PER_LNG = 110000; // 위도 1도당 미터

// tile의 사이즈
const spacingR = TILE_SIZE * (1 / METER_PER_LAT);
const spacingC = TILE_SIZE * (1 / METER_PER_LNG);

// 우리나라의 크기(울릉도,독도,제주도 포함)
const latmin = 33.0;
const latmax = 38.9;
const lngmin = 124.5;
const lngmax = 132.0;

const C = parseInt((latmax - latmin) / spacingC); //위도 셀 개수 (세로)
const R = parseInt((lngmax - lngmin) / spacingR); //경도 셀 개수 (가로)

export default {
  name: 'CommitMap2',
  data() {
    return {
      cellStyle: 'cell',
      testData: [
        { lat: 38.5, lng: 131.5 },
        { lat: 38.9, lng: 124.5 },

        { lat: 38.9, lng: 124.5 },
        { lat: 33.0, lng: 124.5 },

        { lat: 33.0, lng: 124.5 },
        { lat: 33.0, lng: 132.0 },

        { lat: 38.9, lng: 132.0 },
        { lat: 33.0, lng: 132.0 },
      ],
      tiles: [
        [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0, -1, -1],
        [-1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0],
        [-1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1],
        [-1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
      ],
    };
  },
  created() {
    this.createTile();
  },
  methods: {
    createTile() {
      console.log('%cCommitMap2.vue line:42 c1', 'color: #007acc;', C);
      console.log('%cCommitMap2.vue line:43 r1', 'color: #007acc;', R);

      // this.tiles = new Array(R);
      // for (let i = 0; i < R; i++) {
      //   this.tiles[i] = new Array(C).fill(0);
      // }

      // const leftLng = 125.8;
      // const rightLng = 129.6;
      // const bottomLat = 34.1;

      // const leftR = this.convertLngToR(leftLng);
      // const rightR = this.convertLngToR(rightLng);
      // const bottomC = this.convertLatToC(bottomLat);

      // let num = 0;
      // for (let i = 0; i < C; i++) {
      //   num = i < bottomC ? 0 : -1;
      //   for (let j = 0; j < R; j++) {
      //     if (j < leftR || j > rightR) this.tiles[j][i] = -1;
      //     else this.tiles[j][i] = num;
      //   }
      // }
      // // 독도, 을릉도 표시
      // const ullengLat = 37.499001;
      // const ullengLng = 130.864168;
      // const ullengC = this.convertLatToC(ullengLat);
      // const ullengR = this.convertLngToR(ullengLng);
      // this.tiles[ullengR][ullengC] = 0;
      // console.log(
      //   '%cCommitMap2.vue line:89 ullengR',
      //   'color: #007acc;',
      //   ullengR
      // );
      // console.log(
      //   '%cCommitMap2.vue line:88 ullengC',
      //   'color: #007acc;',
      //   ullengC
      // );

      // const dokdoLat = 37.2388;
      // const dokdoLng = 131.863842;
      // const dokdoC = this.convertLatToC(dokdoLat);
      // const dokdoR = this.convertLngToR(dokdoLng);
      // console.log('%cCommitMap2.vue line:95 dokdoR', 'color: #007acc;', dokdoR);
      // console.log('%cCommitMap2.vue line:96 dokdoC', 'color: #007acc;', dokdoC);
      // this.tiles[dokdoR][dokdoC] = 0;

      // // for (let i = 0; i < this.testData.length; i++) {
      // //   const c2 = parseInt((this.testData[i].lat - latmin) / spacingC);
      // //   const r2 = parseInt((this.testData[i].lng - lngmin) / spacingR);
      // //   this.tiles[r2][c2] = 1;
      // //   console.log(
      // //     '%cCommitMap2.vue line:74 rc+""+c2',
      // //     'color: #007acc;',
      // //     r2 + '' + c2
      // //   );
      // // }

      // // init data - korea shape
      // // this.tiles[5][4] = 1;
      // // this.tiles[6][7] = 2;
      // // this.tiles[5][8] = 3;
    },
    drawCellColor(cell) {
      if (cell > 0) {
        return 'fill-' + cell;
      } else if (cell == 0) return 'empty';
      return 'null';
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
<style scoped>
.line {
  display: block;
  font-size: 0;
}

.cell {
  height: 20px;
  width: 20px;
  border-radius: 5px;
  margin: 3px;
  font-size: 10px;
  display: inline-grid;
}

.fill-1 {
  background-color: #aae68a;
}
.fill-2 {
  background-color: rgb(62, 196, 91);
}
.fill-3 {
  background-color: rgb(13, 117, 57);
}
.null {
  background-color: #00d4ff;
}
.empty {
  background-color: #dcdcdc;
}
</style>
