<template>
  <v-container class="pt-7">
    <v-row>
      <v-col cols="12" sm="2">
        <v-sheet rounded="xl" height="200">
          <BadgeTotalProcess />
        </v-sheet>
      </v-col>
      <v-col class="mainslot" cols="12" sm="8">
        <v-sheet min-height="70vh" rounded="xl">
          <div class="mainpage">
            <br />
            <MainBadge :key="new Date() + mainBadgeKey" />
            <EtcBadge @update-badge="mainBadgeKey++" />
            <br />
          </div>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MainBadge from '../../components/index/badge/MainBadge';
import EtcBadge from '../../components/index/badge/EtcBadge';
import BadgeTotalProcess from '../../components/index/badge/BadgeTotalProcess';

export default {
  name: 'Badge',

  components: {
    MainBadge,
    EtcBadge,
    BadgeTotalProcess,
  },

  data: () => ({
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
    mainBadgeKey: 0,
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
