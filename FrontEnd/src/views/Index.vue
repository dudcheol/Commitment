<template>
  <v-app id="inspire">
    <!-- <v-app-bar app color="white" flat hide-on-scroll>
      <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar>

      <v-tabs centered class="ml-n9" color="grey darken-1">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>

      <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32"></v-avatar>
    </v-app-bar> -->
    <v-app-bar app color="white" flat>
      <div class="d-flex flex-row">
        <vs-button circle icon flat>
          <i class="bx bx-tag"></i>
        </vs-button>
        <vs-button circle icon flat>
          <i class="bx bx-search"></i>
        </vs-button>
        <vs-input placeholder="Search"> </vs-input>
      </div>

      <div class="mx-auto">
        <v-tabs background-color="transparent">
          <v-tab v-for="item in items" :key="item" :to="item.route">
            <v-icon>
              {{ item.icon }}
            </v-icon>
          </v-tab>
        </v-tabs>
      </div>

      <div class="d-flex flex-row ml-auto">
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

<style scoped>
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
