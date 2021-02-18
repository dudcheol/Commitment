<template>
  <v-sheet class="mx-auto" max-width="700" color="transparent">
    <no-data-card
      v-if="empFollower"
      :icon="'heart-circle-outline'"
      :text="'다른 사람을 팔로우 해보세요. 팔로우한 사람의 실시간 커밋지도를 확인할 수 있습니다.'"
      class="mt-md-4"
    ></no-data-card>
    <v-slide-group show-arrows>
      <v-slide-item
        v-for="(item, index) in commitMpas"
        :key="'followMap' + index"
        v-slot="{ toggle }"
      >
        <v-card
          v-if="item.user"
          color="white"
          class="ma-1"
          height="200"
          width="180"
          @click="
            toggle;
            followMapClick(item);
          "
          rounded="lg"
          :ripple="false"
          :elevation="1"
          :style="`cursor:${item.user.region_name ? 'pointer' : 'default'}`"
        >
          <div class="d-flex align-center mt-2 ml-2">
            <v-avatar v-if="item.user.profile" circle size="40">
              <img :src="item.user.profile.filePath" />
            </v-avatar>
            <v-avatar
              v-else
              circle
              size="40"
              color="blue-grey"
              class="font-weight-medium display-2"
            >
              <v-icon color="white">mdi-emoticon-happy</v-icon>
            </v-avatar>
            <div class="pl-1">
              <div>{{ item.user.nickname | truncate(8, '...') }}</div>
              <div class="caption">{{ regionKr(item.user.region_name) }}</div>
            </div>
          </div>
          <div class="d-flex justify-center align-center" style="height:150px">
            <component
              :is="item.user.region_name"
              :size="mapSize(item.user.region_name)"
              :borderRadius="1"
              :spacing="0.5"
              :datas="item.commit"
              :key="'commitMap' + index"
            ></component>
          </div>
        </v-card>
        <v-card
          v-else
          height="200"
          width="180"
          color="rgb(224,229,231)"
          class="ma-1"
          elevation="0"
          rounded="lg"
        >
          <div
            class="rounded-circle blue-grey lighten-4 ma-2"
            style="width:40px; height:40px"
          ></div>
        </v-card>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
import { mapGetters } from 'vuex';
import { followCommitMap } from '../../../api/commit';
import NoDataCard from '../../common/card/NoDataCard.vue';
export default {
  components: {
    national: () => import('../../../components/common/map/MapNational'),
    busan: () => import('../../../components/common/map/MapBusan'),
    gangwon: () => import('../../../components/common/map/MapGangwon'),
    gwangju: () => import('../../../components/common/map/MapGwangju'),
    gyeonggi: () => import('../../../components/common/map/MapGyeonggi'),
    seoul: () => import('../../../components/common/map/MapSeoul'),
    ulsan: () => import('../../../components/common/map/MapUlsan'),
    NoDataCard,
  },
  data() {
    return {
      mapType: [],
      commitMpas: [],
      nickname: 'nickname',
      empFollower: false,
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
  watch: {
    user: {
      immediate: true,
      handler() {
        this.commitMpas = [];
        this.getFollowCommitMap();
      },
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
    mapSize(region) {
      switch (region) {
        case 'national':
          return 3;
        case 'seoul':
          return 3;
        default:
          return 2;
      }
    },
    followMapClick(item) {
      this.$store.commit('SELECTED_USER_ID', item.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
    getFollowCommitMap() {
      followCommitMap(
        this.user.email,
        (response) => {
          if (response.data.length == 0) {
            this.empFollower = true;
          } else {
            this.commitMpas.push(...response.data);
            for (let i = 0; i < 5 - response.data.length; i++) {
              this.commitMpas.push({});
            }
          }
        },
        (error) => {
          console.log(
            '%cerror FollowerMap.vue line:103 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
  },
  mounted() {
    // this.getFollowCommitMap();
  },
  filters: {
    truncate: function(text, length, suffix) {
      if (text.length > length) {
        return text.substring(0, length) + suffix;
      } else {
        return text;
      }
    },
  },
};
</script>

<style scoped></style>
