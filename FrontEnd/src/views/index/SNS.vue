<template>
  <v-container fluid class="blue-grey lighten-5">
    <v-col
      class="mt-4 pa-0 d-flex justify-center flex-column"
      :class="dynamicPosition"
      cols="12"
      md="3"
    >
      <v-sheet rounded="xl" class="mb-4 px-4 pt-6" style="width:100%">
        <div class="d-flex align-center">
          <div class="font-weight-black mr-2 display-1">
            {{ commitRange[0] == 0.5 && commitRange[1] == 10 ? '모든 커밋' : address }}
          </div>
          <div class="blue-grey darken-1 px-1 rounded-lg white--text font-weight-bold" rounded="lg">
            <span v-if="commitRange[0] == commitMinRange && commitRange[1] == commitMinRange"
              >{{ commitMinRange }} km 이내</span
            >
            <span v-else-if="commitRange[0] > commitMinRange || commitRange[1] < commitMaxRange">
              {{ commitRange[0] }} ~ {{ commitRange[1] }} km
            </span>
            <span v-else>
              전체
            </span>
          </div>
        </div>
        <v-range-slider
          v-model="commitRange"
          step="0.5"
          :min="commitMinRange"
          :max="commitMaxRange"
          dense
          color="blue-grey lighten-3"
          thumb-color="blue-grey lighten-1"
          track-color="blue-grey lighten-5"
        ></v-range-slider>
      </v-sheet>
      <GmapMap
        :center="{ lat: pos.lat, lng: pos.lng }"
        :zoom="mapZoom"
        :options="mapOptions"
        map-type-id="terrain"
        style="width:100%; min-height:300px; overflow: hidden; border-radius:24px"
        v-if="pos"
      >
      </GmapMap>
      <no-data-card
        :icon="'emoticon-confused-outline'"
        :text="'현재 위치 정보를 알 수 없어요'"
        style="width:100%"
        v-else
      ></no-data-card>
    </v-col>

    <v-row :justify="dynamicJustify">
      <v-col class="mainslot" cols="12" md="6">
        <div v-if="feedDatas.length == 0">
          <no-data-card
            :icon="'emoticon-frown-outline'"
            :text="'주변에 커밋이 없어요. 가장 먼저 커밋을 남겨보세요!'"
            class="mt-4"
          ></no-data-card>
        </div>
        <div v-else class="card mt-4" v-for="item in feedDatas" :key="item">
          <main-card :data="item"></main-card>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MainCard from '../../components/common/card/MainCard';
import { likeBoardList } from '../../api/like';
import { mapGetters } from 'vuex';
import NoDataCard from '../../components/common/card/NoDataCard.vue';

export default {
  components: {
    MainCard,
    NoDataCard,
  },
  data() {
    return {
      feedDatas: [],
      date: '7',
      options: '1',
      commitRange: [0, 10],
      commitMinRange: 0.5,
      commitMaxRange: 10,
      mapZoom: 15,
      mapOptions: {
        zoomControl: false,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        disableDefaultUi: false,
        draggable: false,
      },
    };
  },
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
  },
  created() {
    likeBoardList(
      this.user.email,
      (response) => {
        console.log('%cLikes.vue line:146 response', 'color: #007acc;', response);
        this.feedDatas = response.data.content;
      },
      (error) => {
        console.log(
          '%cerror Likes.vue line:95 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
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
