<template>
  <v-container fluid class="pa-0 ma-0">
    <v-row no-gutters class="pa-0 ma-0">
      <v-col cols="12" sm="8" class="pa-0 ma-0">
        <div class="d-flex justify-center align-center">
          <v-btn-toggle
            v-model="picked"
            :ripple="false"
            color="primary"
            borderless
            mandatory
            rounded
            dense
          >
            <v-btn
              :ripple="false"
              v-for="(item, index) in mapList"
              :key="'mapList' + index"
              class="text-caption"
              x-small
            >
              {{ item }}
            </v-btn>
          </v-btn-toggle>
          <v-btn
            v-if="this.user.nickname == this.userId"
            outlined
            rounded
            x-small
            color="primary"
            elevation="0"
            text
            :ripple="false"
            @click="mapSettingDialog = !mapSettingDialog"
            class="font-weight-black mx-1"
          >
            대표지도
          </v-btn>
          <v-tooltip right color="blue-grey" transition="scroll-x-reverse-transition">
            <template v-slot:activator="{ on, attrs }" color="">
              <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-4">mdi-information</v-icon>
            </template>
            <span
              >보고싶은 지역을 클릭하면<br />
              <strong>지역별 커밋지도</strong>를 볼 수 있어요</span
            >
            <span v-if="this.user.nickname == this.userId"
              ><br /><strong>대표지도</strong>를 설정해서 가장 먼저 보여주고 싶은 내 커밋지도를
              선택해보세요</span
            >
          </v-tooltip>
        </div>
        <div
          class="d-flex justify-center align-center"
          style="height:600px; overflow:auto"
          :key="userId"
        >
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
          <div v-else-if="picked == 3" class="pt-16">
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
      <v-col cols="12" sm="4" class="mt-4 mt-sm-0">
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
import { mapActions, mapGetters } from 'vuex';
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
    ...mapActions(['GET_MEMBER_INFO', 'UPDATE_USERINFO_BY_NICKNAME']),
    mapClick(val) {
      getMapCoordCommits(
        this.userId,
        this.getRegion(this.picked),
        val.y,
        val.x,
        (response) => {
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
      setMyCommitMap(
        this.user.email,
        this.getRegion(this.picked),
        (response) => {
          if (response.data === 'success') {
            this.openNotification(4000);
            this.UPDATE_USERINFO_BY_NICKNAME(this.user.nickname);
          }
        },
        () => {}
      );
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
    this.items = [];
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
    this.items = [];
  },
};
</script>

<style scoped></style>
