<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col class="sidebar_left" cols="12" sm="2">
            <div class="d-flex justify-center">
              <SelectZone />
            </div>
          </v-col>
          <v-col class="mainslot" cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <div class="mainpage">
                <br />
                <MainBadge />
                <EtcBadge />
                <br />
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
import SelectZone from '../../components/index/main/ProfileSummary';
import MainBadge from '../../components/index/badge/MainBadge';
import EtcBadge from '../../components/index/badge/EtcBadge';
import BadgeTotalProcess from '../../components/index/badge/BadgeTotalProcess';
export default {
  name: 'Badge',

  components: {
    SelectZone,
    MainBadge,
    EtcBadge,
    BadgeTotalProcess,
  },

  data: () => ({
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
  }),
};
</script>

<style scoped>
.container {
  max-width: 1200px;
}
.navbar_right {
  display: flex;
  width: min-content;
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
