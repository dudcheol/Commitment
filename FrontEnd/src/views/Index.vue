<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat>
      <div class="search">
        <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
        <vs-button circle icon flat>
          <i class="bx bx-tag"></i>
        </vs-button>
        <vs-button circle icon flat>
          <i class="bx bx-search"></i>
        </vs-button>
        <vs-input placeholder="Search"> </vs-input>
      </div>

      <!-- tab 바뀌는부분 -->
      <div>
        <v-tabs background-color="transparent">
          <v-tab v-for="item in items" :key="item" :to="item.route">
            <v-icon>
              {{ item.icon }}
            </v-icon>
          </v-tab>
        </v-tabs>
      </div>
      <!-- 아바타 바뀌는 지역 -->
      <div>
        <!-- 일단 정렬을 위한 div -->
      </div>
      <div class="navbar_right">
        <vs-button circle icon flat>
          <i class="bx bx-plus"></i>
        </vs-button>

        <vs-button circle icon flat>
          <i class="bx bxl-whatsapp"></i>
        </vs-button>

        <vs-button circle icon flat>
          <i class="bx bx-up-arrow-alt"></i>
        </vs-button>
      </div>
    </v-app-bar>
    <!-- 메인페이지파트 -->
    <v-main class="grey lighten-3">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'MainPage',
  data: () => ({
    items: [
      { icon: 'mdi-home', route: '/' },
      { icon: 'mdi-map-marker', route: '/sns' },
      { icon: 'mdi-medal', route: '/rank' },
      { icon: 'mdi-heart', route: 'likes' },
    ],
  }),
  methods: {},
};
</script>

<style>
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

/* .sidebar_left {
  position: -webkit-sticky;
  position: sticky;
  top: 4px;
}

.mainslot {
  overflow: auto;

} */

@media (max-width: 450px) {
  .search {
    display: none;
  }

  .navbar_right {
    display: none;
  }
}
</style>
