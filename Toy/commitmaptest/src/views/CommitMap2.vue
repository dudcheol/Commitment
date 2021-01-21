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
export default {
  data() {
    return {
      tiles: [],
      cellStyle: 'cell',
    };
  },
  created() {
    this.createTile();
  },
  methods: {
    createTile() {
      // 대한민국의 크기
      // 124.5 38.9 좌상
      // 132.0 33.0 우하
      // 200x200 격자 만들기
      const TILE_SIZE = 50000;
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

      const c1 = parseInt((latmax - latmin) / spacingC); //위도 셀 개수 (세로)
      const r1 = parseInt((lngmax - lngmin) / spacingR); //경도 셀 개수 (가로)

      console.log('%cCommitMap2.vue line:42 c1', 'color: #007acc;', c1);
      console.log('%cCommitMap2.vue line:43 r1', 'color: #007acc;', r1);

      this.tiles = new Array(r1);
      for (let i = 0; i < this.tiles.length; i++) {
        this.tiles[i] = new Array(c1);
      }

      // test set
      this.tiles[5][4] = 1;
      this.tiles[6][7] = 2;
      this.tiles[5][8] = 3;
    },
    drawCellColor(cell) {
      if (cell) {
        return 'fill-' + cell;
      }
      return 'empty';
    },
  },
};
</script>
<style scoped>
.line {
  float: left;
}

.cell {
  height: 30px;
  width: 30px;
  border-radius: 5px;
  margin: 3px;
  font-size: 10px;
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

.empty {
  background-color: #dcdcdc;
}
</style>
