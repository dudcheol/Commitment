<template>
  <v-app id="inspire">
    <!-- 메인페이지파트 -->
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col
            class="sidebar_left"
            cols="12"
            sm="2"
          >
            <div class="d-flex justify-center">
              <SelectZone />
            </div>
          </v-col>

          <v-col
            class="mainslot"
            cols="12"
            sm="8"
          >
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >
              <div class="mainpage">
                <!-- 일단 3개컴포넌트 넣어보고 길이 결정해보기 -->
                <br>
                <MainBadge />
                <EtcBadge />
                <br>
              </div>
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2">
            <div class="radar mt-4 mb-3">
              <v-slider :thumb-size="15" thumb-label="always"></v-slider>
            </div>
            <v-sheet rounded="lg" min-height="200">
              <BadgeTotalProcess />
            </v-sheet>
          </v-col>

        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  import SelectZone from '../../components/main/home/ProfileSummary'
  import MainBadge from '../../components/main/badge/MainBadge'
  import EtcBadge from '../../components/main/badge/EtcBadge'
  import BadgeTotalProcess from '../../components/main/badge/BadgeTotalProcess'
  export default {
    name: "Badge",

    components: {
      // CommitNow,
      // NotYetAddArticle,
      SelectZone,
      MainBadge,
      EtcBadge,
      BadgeTotalProcess,
    },

    data: () => ({
      items: [
          'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
        ],  
    }),
  }
</script>

<style scoped>
.container {
  max-width: 1200px;
}
.navbar_right {
  display: flex;
  width: min-content
}
.search {
  display: flex;
  width: min-content;
}
.v-toolbar__content {
  display: flex;
  justify-content: space-between;
}

@media (max-width: 450px) {

.search {
  display: none;
}

.navbar_right {
  display: none;
}

}
</style>
