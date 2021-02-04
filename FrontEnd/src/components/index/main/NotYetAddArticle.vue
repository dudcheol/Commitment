<template>
  <div class="mt-4">
    <div class="mb-2">
      <h2 class="pl-2 font-weight-black">나의 커밋</h2>
    </div>
    <v-expansion-panels
      accordion
      flat
      multiple
      v-model="panel"
      class="rounded-lg"
    >
      <v-expansion-panel style="background-color:transparent">
        <v-expansion-panel-header
          expand-icon="mdi-arrow-down-drop-circle-outline"
          >빈 커밋</v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in empCommits"
            :key="index"
            :address="item"
            style="height:30px; width:100%"
            class="mb-2"
          ></commit-card>
          <v-btn
            block
            :ripple="false"
            rounded
            height="30px"
            color="blue-grey darken-4"
            text
            ><strong>더보기</strong></v-btn
          >
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-expansion-panel style="background-color:transparent">
        <v-expansion-panel-header
          expand-icon="mdi-arrow-down-drop-circle-outline"
          >최근 커밋</v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <commit-card
            v-for="(item, index) in empCommits"
            :key="index"
            :address="item"
            style="height:30px; width:100%"
            class="mb-2"
          ></commit-card>
          <v-btn
            block
            :ripple="false"
            rounded
            height="30px"
            color="blue-grey darken-4"
            text
            ><strong>더보기</strong></v-btn
          >
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import CommitCard from '../../common/card/CommitCard.vue';
export default {
  components: { CommitCard },
  data() {
    return {
      panel: [],
      empCommits: [
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
      ],
      recentCommits: [
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
        '서울특별시 강남구 테헤란로 123-1',
      ],
    };
  },
};
</script>

<style scoped></style>
