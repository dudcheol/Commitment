<template>
  <v-container fluid class="pa-0 ma-0">
    <v-row no-gutters>
      <v-col cols="12" sm="8">
        <div class="d-flex justify-center">
          <v-btn-toggle
            v-model="picked"
            :ripple="false"
            color="primary"
            borderless
            mandatory
            rounded
          >
            <v-btn
              :ripple="false"
              v-for="(item, index) in mapList"
              :key="'mapList' + index"
              class="text-caption"
              small
            >
              {{ item }}
            </v-btn>
          </v-btn-toggle>
        </div>
        <div class="d-flex justify-center align-center" style="height:600px" :key="userId">
          <div v-if="picked == 0">
            <MapNational
              :size="10"
              :spacing="2"
              :borderRadius="2"
              @square-click="mapClick"
              :datas="datas"
            />
          </div>
          <div v-else-if="picked == 1">
            <MapSeoul :spacing="2" :borderRadius="2" @square-click="mapClick" :datas="datas" />
          </div>
          <div v-else-if="picked == 2">
            <MapBusan
              :size="8"
              :spacing="2"
              :borderRadius="2"
              class="pt-16"
              @square-click="mapClick"
              :datas="datas"
            />
          </div>
          <div v-else-if="picked == 3">
            <MapGyeonggi
              :size="8"
              :spacing="2"
              :borderRadius="2"
              class="pr-16"
              @square-click="mapClick"
              :datas="datas"
            />
          </div>
          <div v-else-if="picked == 4">
            <MapGangwon :size="7" :spacing="2" @square-click="mapClick" :datas="datas" />
          </div>
          <div v-else-if="picked == 5">
            <MapUlsan
              :size="8"
              :spacing="2"
              :borderRadius="2"
              @square-click="mapClick"
              :datas="datas"
            />
          </div>
          <div v-else-if="picked == 6">
            <MapGwangju :size="10" :spacing="2" @square-click="mapClick" :datas="datas" />
          </div>
        </div>
      </v-col>
      <v-col cols="12" sm="4">
        <div v-if="!items.length">
          <no-data-card
            :icon="'emoticon-excited-outline'"
            :text="'커밋지도에서 색칠된 부분을 클릭하면 커밋기록을 볼 수 있어요'"
            style="height:628px"
          ></no-data-card>
        </div>
        <div v-else class="rounded-xl blue-grey lighten-5 pa-4">
          <div class="rounded-lg" style="height:600px; overflow:auto;">
            <commit-list-item
              v-for="(item, index) in items"
              :key="'mypageCommitList' + index"
              class="mb-2"
              :data="item"
            >
            </commit-list-item>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MapNational from '../../common/map/MapNational';
import MapSeoul from '../../common/map/MapSeoul';
import MapBusan from '../../common/map/MapBusan';
import MapGyeonggi from '../../common/map/MapGyeonggi';
import MapGangwon from '../../common/map/MapGangwon';
import MapUlsan from '../../common/map/MapUlsan';
import MapGwangju from '../../common/map/MapGwangju';
import CommitListItem from '../../common/card/CommitListItem';
import NoDataCard from '../../common/card/NoDataCard.vue';
import { getCommitMap, getMapCoordCommits } from '../../../api/commit';
import { mapGetters } from 'vuex';
export default {
  components: {
    CommitListItem,
    MapNational,
    MapSeoul,
    MapBusan,
    MapGyeonggi,
    MapGangwon,
    MapUlsan,
    MapGwangju,
    NoDataCard,
  },
  data() {
    return {
      mapList: ['전국', '서울', '부산', '경기', '강원', '울산', '광주'],
      items: [],
      picked: 0,
      datas: [],
    };
  },
  watch: {
    picked(val) {
      this.datas = [];
      this.items = [];
      getCommitMap(
        this.userId,
        this.getRegion(val),
        (response) => {
          console.log('%cMapList.vue line:161 response.data', 'color: #26bfa5;', response.data);
          this.datas = response.data;
        },
        (error) => {
          console.log(
            '%cerror MapList.vue line:153 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
  },
  computed: {
    ...mapGetters({ userId: ['getSelectedUserId'] }),
  },
  methods: {
    mapClick(val) {
      console.log('%cMapList.vue line:69 val', 'color: #007acc;', val);
      getMapCoordCommits(
        this.userId,
        this.getRegion(this.picked),
        val.y,
        val.x,
        (response) => {
          console.log('%cMapList.vue line:115 response', 'color: #007acc;', response);
          this.items = response.data;
        },
        (error) => {
          console.log(
            '%cerror MyPage.vue line:161 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    getRegion(region) {
      switch (region) {
        case 0:
          return 'national';
        case 1:
          return 'seoul';
        case 2:
          return 'busan';
        case 3:
          return 'gyeonggi';
        case 4:
          return 'gangwon';
        case 5:
          return 'ulsan';
        case 6:
          return 'gwangju';
      }
    },
  },
  activated() {
    console.log('%cMapList.vue line:14', 'color: #007acc;');
    console.log('%cMapList.vue line:148 this.userId', 'color: #007acc;', this.userId);
    this.datas = [];
    this.items = [];
    getCommitMap(
      this.userId,
      this.getRegion(this.picked),
      (response) => {
        console.log('%cMapList.vue line:161 response.data', 'color: #26bfa5;', response.data);
        this.datas = response.data;
      },
      (error) => {
        console.log(
          '%cerror MapList.vue line:153 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
  deactivated() {
    this.datas = [];
    this.items = [];
  },
};
</script>

<style scoped></style>
