<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
      <v-btn elevation="" fab x-small class="ml-3">
        <v-icon >
          mdi-magnify
        </v-icon>
      </v-btn>
      <!-- <vs-button circle icon flat>
        <i class="bx bx-search"></i>
      </vs-button>   -->

      <!-- 가운데 탭 항상 가운데에 만들기 버튼개수 상관없이 -->
      <v-tabs grow centered color="grey darken-1" class="d-flex justify-center">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>


    <div class="d-none d-sm-flex">
      <v-btn fab flat x-small dark color="blue darken-2" class="mr-3"
        v-for="item in right_items" :key="item"
      >
      <v-icon>{{ item.icon }}</v-icon>
      </v-btn>

    </div>

    <v-speed-dial 
      class="d-flex d-sm-none"
      v-model="fab"
      direction="bottom"
      transition="slide-x-reverse-transition"
    >
      <template v-slot:activator>
        <v-btn
          x-small
          color="blue"
          dark
          fab
        >
          <v-icon v-if="fab">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-chevron-down
          </v-icon>
        </v-btn>
      </template>
      <v-btn fab dark x-small color="blue"
        v-for="item in right_items" :key="item"
      >
        <v-icon>{{ item.icon }}</v-icon>
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
    fab: false,
    items: [
      { icon: 'mdi-home', route: '/' },
      { icon: 'mdi-map-marker', route: '/sns' },
      { icon: 'mdi-medal', route: '/rank' },
      { icon: 'mdi-heart', route: 'likes' },
    ],
    right_items: [
      { icon: 'mdi-account', route: '/' },
      { icon: 'mdi-bell', route: '/sns' },
      { icon: 'mdi-cog', route: '/rank' },
      { icon: 'mdi-logout', route: 'likes' },
    ]
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
