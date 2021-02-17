<template>
  <v-container fluid class="pa-0 ma-0">
    <v-row no-gutters>
      <v-col cols="12" sm="8">
        <div class="d-flex justify-center align-center flex-column">
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
          <v-btn
            outlined
            rounded
            small
            color="primary"
            elevation="0"
            text
            :ripple="false"
            @click="mapSettingDialog = !mapSettingDialog"
            style="position:absolute"
            class="mt-16"
          >
            대표지도로 설정
          </v-btn>
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
    <Dialog
      :confirm="mapSettingDialog"
      :confirmTitle="'대표지도 설정하기'"
      :confirmContent="'현재 보고 있는 지도를 대표지도로 설정할까요?'"
      @close="mapSettingDialog = !mapSettingDialog"
      @confirm-ok="setMyCommitMap"
    ></Dialog>
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
import { getUserInfoByNickname, setMyCommitMap } from '../../../api/account';
import Dialog from '../../common/dialog/Dialog.vue';
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
    Dialog,
  },
  data() {
    return {
      mapList: ['전국', '서울', '부산', '경기', '강원', '울산', '광주'],
      items: [],
      picked: 0,
      datas: [],
      mapSettingDialog: false,
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
    ...mapGetters({ user: ['getUserInfo'], userId: ['getSelectedUserId'] }),
  },
  methods: {
    mapClick(val) {
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
    getRegionIdx(region) {
      switch (region) {
        case 'national':
          return 0;
        case 'seoul':
          return 1;
        case 'busan':
          return 2;
        case 'gyeonggi':
          return 3;
        case 'gangwon':
          return 4;
        case 'ulsan':
          return 5;
        case 'gwangju':
          return 6;
      }
    },
    setMyCommitMap() {
      console.log('%cMapList.vue line:235 this.user', 'color: #007acc;', this.user);
      setMyCommitMap(
        this.user.email,
        this.getRegion(this.picked),
        (response) => {
          if (response.data === 'success') {
            this.openNotification(4000);
          }
        },
        () => {}
      );
      // this.user.email,
    },
    openNotification(duration) {
      this.$vs.notification({
        duration,
        position: 'top-right',
        color: 'primary',
        flat: true,
        progress: 'auto',
        title: '알림',
        text: `대표지도를 변경하였습니다.`,
      });
    },
  },
  activated() {
    this.datas = [];
    getUserInfoByNickname(
      this.userId,
      (response) => {
        this.picked = this.getRegionIdx(response.data.user.region_name);
        getCommitMap(
          this.userId,
          this.getRegion(this.picked),
          (response) => {
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
      (error) => {
        console.log(
          '%cerror MapList.vue line:222 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
  deactivated() {
    this.datas = [];
  },
};
</script>

<style scoped></style>
