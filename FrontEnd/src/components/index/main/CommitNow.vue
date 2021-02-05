<template>
  <div class="d-flex flex-column ml-lg-16">
    <div class="d-flex align-center mb-2">
      <img src="../../../assets/img/commitnow/LiveMonitoring.gif" style="max-width:16px;" />
      <h2 class="ml-1 font-weight-black">실시간 커밋</h2>
    </div>
    <v-expansion-panels accordion flat class="mb-2 rounded-lg">
      <v-expansion-panel size="x-small">
        <v-expansion-panel-header>
          <div class="d-flex align-center">
            <span class="font-weight-black mr-2">
              반경
            </span>
            <v-chip small>
              <span v-if="commitRange[0] == commitMinRange && commitRange[1] == commitMinRange"
                >{{ commitMinRange }} km 이내</span
              >
              <span v-else-if="commitRange[0] > commitMinRange || commitRange[1] < commitMaxRange">
                {{ commitRange[0] }} ~ {{ commitRange[1] }} km
              </span>
              <span v-else>
                전체
              </span>
            </v-chip>
          </div>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-range-slider
            v-model="commitRange"
            step="0.5"
            :min="commitMinRange"
            :max="commitMaxRange"
          ></v-range-slider>
          <span class="text-caption">슬라이더로 반경을 조절할 수 있어요</span>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <commit-card
      v-for="(item, index) in nowCommits"
      :key="index"
      :img="'https://picsum.photos/200/300'"
      :username="item.username"
      :address="item.address"
      class="mb-2"
    ></commit-card>
  </div>
</template>

<script>
import CommitCard from '../../common/card/CommitCard.vue';
export default {
  components: { CommitCard },
  name: 'CommitNow',
  data() {
    return {
      nowCommits: [
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
        { username: 'username', address: '서울특별시 강남구 테헤란로 123-1' },
      ],
      commitRange: [0, 10],
      commitMinRange: 0.5,
      commitMaxRange: 10,
    };
  },
  methods: {},
};
</script>

<style>
.v-slider__track-container {
  height: 12px !important;
}
.v-slider__track-background {
  border-radius: 6px;
}
.v-messages {
  min-height: 0px;
}
</style>
