<template>
  <v-sheet class="mx-auto" max-width="800" color="transparent">
    <v-slide-group v-model="model" class="" show-arrows>
      <v-slide-item v-for="item in items" :key="item" v-slot="{ active, toggle }">
        <v-card
          :color="item.region ? 'white' : 'rgb(224,229,231)'"
          class="ma-1 d-flex justify-center flex-column justify-space-between"
          height="200"
          width="150"
          @click="toggle"
          rounded="lg"
          :ripple="false"
          :elevation="item.region ? 1 : 0"
          :disabled="!item.region"
          :style="`cursor:${item.region ? 'pointer' : 'default'}`"
        >
          <div class="d-flex align-center mt-2 ml-2">
            <vs-avatar circle size="36" color="rgb(232,234,238)">
              <!-- <img :src="imgsrc" alt="" /> -->
            </vs-avatar>
            <div class="pl-1">
              <div>{{ item.nickname }}</div>
              <div class="caption">{{ regionKr(item.region) }}</div>
            </div>
          </div>
          <div class="d-flex justify-center">
            <component
              :is="mapType[0]"
              :size="2"
              :borderRadius="1.5"
              :spacing="0.5"
              :datas="item.datas"
              :key="item.datas"
            ></component>
          </div>
        </v-card>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
export default {
  data() {
    return {
      mapType: [],
      items: [
        {
          mapType: null,
          region: 'busan',
          datas: [[4, 4, 1]],
          nickname: 'nickname',
        },
        {},
        {},
        {},
        {},
      ],
      nickname: 'nickname',
    };
  },
  computed: {
    loader() {
      return () =>
        import(
          `../../../components/common/map/Map${this.items[0].region.replace(/\b[a-z]/, (letter) =>
            letter.toUpperCase()
          )}`
        );
      // import(`../../../components/common/map/MapNational`);
    },
  },
  methods: {
    regionKr(region) {
      switch (region) {
        case 'busan':
          return '대한민국 부산';
        case 'gangwon':
          return '대한민국 강원';
        case 'gwangju':
          return '대한민국 광주';
        case 'gyeonggi':
          return '대한민국 경기';
        case 'national':
          return '대한민국';
        case 'seoul':
          return '대한민국 서울';
        case 'ulsan':
          return '대한민국 울산';
      }
    },
  },
  mounted() {
    this.loader()
      .then(() => {
        this.mapType[0] = () => this.loader();
      })
      .catch(() => {
        console.log(
          '%cerror FollowerMap.vue line:68 커밋지도 가져오기 실패',
          'color: red; display: block; width: 100%;'
        );
      });
  },
};
</script>

<style scoped></style>
