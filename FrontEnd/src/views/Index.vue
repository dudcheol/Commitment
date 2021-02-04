<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      <v-btn
        elevation=""
        fab
        x-small
        class="ml-3 d-none d-sm-flex"
        color="white"
      >
        <v-avatar size="35">
          <img src="../assets/img/main/commitment_logo.jpg" alt="logo" />
        </v-avatar>
      </v-btn>

      <!-- 가운데 탭 항상 가운데에 만들기 버튼개수 상관없이 -->
      <v-tabs centered color="grey darken-1" class="tabs_list">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>

      <div class="d-none d-sm-flex ml-auto">
        <v-btn
          fab
          elevation=""
          x-small
          dark
          color="blue darken-2"
          class="mr-3"
          v-for="item in right_items"
          :key="item"
        >
          <v-icon>{{ item.icon }}</v-icon>
        </v-btn>
      </div>

      <div class="search-box">
        <input type="text" name="" class="search-txt" placeholder="Search" />
        <a class="search-btn" href="#">
          <v-icon>mdi-magnify</v-icon>
        </a>
      </div>

      <v-speed-dial
        class="d-flex d-sm-none ml-auto"
        v-model="fab"
        direction="bottom"
        transition="slide-x-reverse-transition"
      >
        <template v-slot:activator>
          <v-btn x-small color="blue" dark fab>
            <v-icon v-if="fab">
              mdi-close
            </v-icon>
            <v-icon v-else>
              mdi-chevron-down
            </v-icon>
          </v-btn>
        </template>
        <v-btn
          fab
          dark
          x-small
          color="blue"
          v-for="item in right_items"
          :key="item"
        >
          <v-icon>{{ item.icon }}</v-icon>
        </v-btn>
      </v-speed-dial>
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
    ],
    alldatalist: [],
  }),
  methods: {},
};
</script>

<style scoped>
.search-box {
  position: absolute;
  left: 6%;
  background: #6a9ce2;
  height: 40px;
  border-radius: 40px;
  z-index: 2px;
  /* padding: 10px; */
}

.search-box:hover > .search-txt {
  width: 100px;
  padding: 0 6px;
}

.search-box:hover > .search-btn {
  background: white;
  color: black;
}

.search-box .search-btn {
  color: #6ad42c;
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #006aff;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
  color: white;
  cursor: pointer;
}

.search-btn > v-icon {
  font-size: 30px;
}

.search-txt {
  border: none;
  background: none;
  outline: none;
  float: left;
  padding: 0;
  color: white;
  font-size: 16px;
  transition: 0.4s;
  width: 100px;
  font-weight: bold;
}

.tabs_list {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

v-speed-dial {
  z-index: 0 !important;
}

@media (max-width: 450px) {
  .v-tab {
    min-width: 10vw !important;
  }

  .search__container {
    z-index: 2px;
    position: absolute;
    width: 90vw;
    background: grey;
  }
  .search-box {
    position: absolute;
    left: 6%;
    background: #6a9ce2;
    height: 40px;
    border-radius: 40px;
    z-index: 2px;
    /* padding: 10px; */
  }

  .search-box:hover > .search-txt {
    width: 100px !important;
  }

  .search-box:hover > .search-btn {
    background: white;
    color: black;
  }

  .search-box .search-btn {
    color: #6ad42c;
    float: right;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background: #006aff;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: 0.4s;
    color: white;
    cursor: pointer;
  }

  .search-btn > v-icon {
    font-size: 30px;
  }

  .search-txt {
    border: none;
    background: none;
    outline: none;
    float: left;
    padding: 0;
    color: white;
    font-size: 16px;
    transition: 0.4s;
    width: 0px;
    font-weight: bold;
  }
}
</style>
