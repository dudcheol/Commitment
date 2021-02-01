<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
      <v-btn elevation="" fab small>
        <v-icon >
          mdi-pencil
        </v-icon>
      </v-btn>
      <!-- <vs-button circle icon flat>
        <i class="bx bx-search"></i>
      </vs-button>   -->

      <v-tabs grow centered color="grey darken-1" class="d-flex justify-center">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>

    <v-speed-dial 
      direction="bottom"
      transition="slide-x-reverse-transition"
    >
      <template v-slot:activator>
        <v-btn
          small
          color="blue"
          dark
          fab
        >
          <v-icon v-if="fab">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-account-circle
          </v-icon>
        </v-btn>
      </template>
      <v-btn
        fab
        dark
        small
        color="green"
      >
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
      <v-btn
        fab
        dark
        small
        color="indigo"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
      <v-btn
        fab
        dark
        small
        color="red"
      >
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </v-speed-dial> 





      <!-- <v-avatar class="hidden-sm-and-down" color="grey darken-1 shrink" size="32"></v-avatar> -->
    </v-app-bar>
    <!-- <v-app-bar app color="white" flat>
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
    </v-app-bar> -->

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

v-speed-dial {
  z-index: 0 !important
}


@media (max-width: 450px) {
  .v-tab {
    min-width: 10vw !important

  }
}
  

/* .sidebar_left {
  position: -webkit-sticky;
  position: sticky;
  top: 4px;
}

.mainslot {
  overflow: auto;

}

@media (max-width: 450px) {

  .tabs__container {
    max-width: 90vw;
  }
} */



</style>
