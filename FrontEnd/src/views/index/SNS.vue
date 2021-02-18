<template>
  <v-container fluid class="blue-grey lighten-5">
    <v-col
      class="mt-4 pa-0 pl-lg-16 d-flex justify-center flex-column align-center"
      :class="dynamicPosition"
      cols="12"
      md="3"
    >
      <v-sheet rounded="xl" class="mb-4 px-4 pt-3" style="width:100%; max-width:680px">
        <div class="d-flex justify-space-between">
          <div class="d-flex align-center">
            <div class="font-weight-black mr-2 display-1">
              {{ commitRange == 30 ? '모든 커밋' : address }}
            </div>
            <div
              class="blue-grey darken-1 px-1 rounded-lg white--text font-weight-bold"
              rounded="lg"
            >
              <span v-if="commitRange < 30 && commitRange >= 0.5">{{ commitRange }} km</span>
              <span v-else>
                전체
              </span>
            </div>
          </div>
          <v-tooltip right color="blue-grey" transition="slide-x-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3">mdi-information</v-icon>
            </template>
            <span
              >나의 현재 위치를 기준으로 반경을 설정해요<br />반경 안에서 작성된 커밋 게시글을 볼 수
              있어요<br />아래 지도를 통해 대략적인 범위를 확인할 수 있어요</span
            >
          </v-tooltip>
        </div>
        <div class="text-caption">슬라이더를 이용하여 반경 조절</div>
        <v-slider
          hint="Im a hint"
          v-model="commitRange"
          step="0.5"
          :min="commitMinRange"
          :max="commitMaxRange"
          @mouseup="changeRange"
          color="blue-grey lighten-3"
          track-color="blue-grey lighten-5"
          thumb-color="blue-grey lighten-2"
          dense
        ></v-slider>
      </v-sheet>
      <GmapMap
        ref="map"
        :center="{ lat: pos.lat, lng: pos.lng }"
        :zoom="mapZoom"
        :options="mapOptions"
        map-type-id="terrain"
        style="width:100%; max-width:680px; min-height:300px; overflow: hidden; border-radius:24px"
        v-if="pos"
      >
        <GmapMarker
          v-for="(marker, index) in markers"
          :key="'marker' + index"
          :position="marker"
          :clickable="true"
          :icon="require('../../assets/img/sns/chat.png')"
        />
        <GmapCircle
          :center="{ lat: pos.lat, lng: pos.lng }"
          :radius="commitRange == 30 ? 0 : commitRange * 1000"
          :visible="true"
          :options="{
            fillColor: '#3b5998',
            fillOpacity: '0.3',
            strokeOpacity: '0',
          }"
        ></GmapCircle>
      </GmapMap>
      <no-data-card
        :icon="'timer-sand'"
        :text="'위치 정보를 받아오고 있어요...'"
        style="width:100%"
        v-else
      ></no-data-card>
    </v-col>

    <v-row :justify="dynamicJustify">
      <v-col class="mainslot" cols="12" md="6">
        <div class="mt-4" v-for="(data, index) in feedDatas" :key="'snsFeedData' + index">
          <main-card :data="data" @removed="changeRange"></main-card>
        </div>
        <infinite-loading
          :identifier="infiniteId"
          @infinite="infiniteHandler"
          ref="InfiniteLoading"
          spinner="circles"
        >
          <div slot="no-more" class="mt-4">
            <NoDataCard :icon="'emoticon-wink-outline'" :text="'모두 보셨습니다'"></NoDataCard>
          </div>
          <!-- <div slot="spinner"></div> -->
          <div slot="no-results" class="mt-4">
            <NoDataCard
              :icon="'emoticon-outline'"
              :text="'반경 내 커밋이 없어요. 가장 먼저 커밋을 남겨보세요!'"
            ></NoDataCard>
          </div>
          <div slot="error">
            <NoDataCard
              :icon="'alert-circle-outline'"
              :text="'현재 위치를 알 수 없어요. 다시 시도해주세요.'"
            ></NoDataCard>
            <div class="pt-3">
              <v-btn
                elevation="0"
                :ripple="false"
                rounded
                color="primary"
                class="font-weight-black"
                @click="infiniteHandler"
                >다시 시도</v-btn
              >
            </div>
          </div>
        </infinite-loading>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MainCard from '../../components/common/card/MainCard';
import { mapGetters } from 'vuex';
import NoDataCard from '../../components/common/card/NoDataCard.vue';
import { totalRadiusBoardList } from '../../api/board';
import InfiniteLoading from 'vue-infinite-loading';

export default {
  components: {
    MainCard,
    NoDataCard,
    InfiniteLoading,
  },
  data() {
    return {
      feedDatas: [],
      options: '1',
      // commitRange: [0, 30],
      commitRange: 30,
      commitMinRange: 0.5,
      commitMaxRange: 30,
      mapOptions: {
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUi: false,
        draggable: true,
        maxZoom: 15,
      },
      pageNumber: 0,
      markers: [],
      infiniteId: +new Date(),
    };
  },
  // watch: {
  //   commitRange: {
  //     deep: true,
  //     handler(val) {
  //       this.commitRange = [0, val[1]];
  //     },
  //   },
  // },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      pos: ['getCurrentLatlng'],
      address: ['getCurrentAddress'],
    }),
    dynamicPosition() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '';
        case 'sm':
          return '';
        case 'md':
          return 'overflow-style';
        case 'lg':
          return 'overflow-style';
        case 'xl':
          return 'overflow-style';
      }
      return '';
    },
    dynamicJustify() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '';
        case 'sm':
          return '';
        case 'md':
          return 'center';
        case 'lg':
          return 'center';
        case 'xl':
          return 'center';
      }
      return '';
    },
    mapZoom() {
      if (this.commitRange == 30) {
        return 6;
      }
      if (this.commitRange >= 18) {
        return 9;
      } else if (this.commitRange >= 9) {
        return 10;
      } else if (this.commitRange >= 5) {
        return 11;
      } else if (this.commitRange >= 2.5) {
        return 12;
      } else if (this.commitRange >= 1.5) {
        return 13;
      } else if (this.commitRange >= 1) {
        return 14;
      } else if (this.commitRange >= 0.5) {
        return 15;
      } else return 9;
    },
  },
  methods: {
    infiniteHandler($state) {
      if (!this.pos) {
        $state.error();
        return;
      }
      totalRadiusBoardList(
        this.pos.lat,
        this.pos.lng,
        this.commitRange == 30 ? 0 : this.commitRange,
        this.pageNumber,
        5,
        this.commitRange == 30 ? 'createdAt,desc' : 'created_at,desc',
        (response) => {
          const res = response.data.content;
          if (res.length) {
            this.feedDatas.push(...res);
            for (let i = 0; i < res.length; i++) {
              this.markers.push({
                lat: parseFloat(res[i].commit.lat),
                lng: parseFloat(res[i].commit.lng),
              });
            }
            this.pageNumber += 1;
            $state.loaded();
          } else {
            $state.complete();
          }
        },
        (error) => {
          console.log('%cSNS.vue line:197 error', 'color: #007acc;', error);
        }
      );
    },
    changeRange() {
      this.feedDatas = [];
      this.markers = [];
      this.pageNumber = 0;
      this.$refs.map.panTo({ lat: this.pos.lat, lng: this.pos.lng });
      this.infiniteId += 1;
    },
  },
};
</script>

<style scoped>
.overflow-style {
  position: fixed;
  overflow-y: scroll;
  max-height: calc(100vh - 64px);
}
</style>
