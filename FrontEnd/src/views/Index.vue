<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      <v-btn elevation="" fab x-small class="ml-3 d-none d-sm-flex" color="white">
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
        <v-btn fab dark x-small color="blue" v-for="item in right_items" :key="item">
          <v-icon>{{ item.icon }}</v-icon>
        </v-btn>
      </v-speed-dial>

      <div class="search-box">
        <div>
          <input type="text" name="" class="search-txt" placeholder="Search" />
          <a class="search-btn" href="#">
            <v-icon color="white">mdi-magnify</v-icon>
          </a>
        </div>
        <div class="result-box">
          <div class="result-list">
            <li>hello</li>
            <li>it's me</li>
            <li>I've</li>
            <li>been</li>
            <li>wandering</li>
          </div>
        </div>
      </div>
    </v-app-bar>
    <v-main class="blue-grey lighten-5">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions } from 'vuex';
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
  methods: {
    ...mapActions(['CURRENT_POSITION']),
  },
  created() {
    this.CURRENT_POSITION();
  },
};
</script>

<style scoped>
.search-box {
  position: absolute;
  left: 6%;
  background: #cfd8dc;
  height: 40px;
  border-radius: 40px;
  margin-top: 4px;
  z-index: 2px;
}

.search-box .result-box {
  /* padding: 10px 0px; */
  font-size: 1em;
  border-radius: 20px;
}

.result-box li {
  list-style: none;
  padding: 10px 15px;
  width: 100%;
  cursor: default;
  display: none;
  border: none;
}

.result-list {
  background-color: rgba(236, 239, 241);
  margin-top: 40px;
  border-radius: 0px 0px 20px 20px;
}

.search-box:hover > .result-box li {
  display: block;
}

.result-box li:hover {
  background: white;
  border-radius: 20px;
}

.search-box:hover {
  border-radius: 20px 20px 0px 0px;
}

.search-box:hover > .search-txt {
  width: 150px;
  padding: 0 6px;
}

.search-box:hover > .search-btn {
  color: black;
}

.search-box .search-btn {
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #cfd8dc;
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
  margin-top: 9px;
  margin-left: 14px;
  padding: 0;
  color: black;
  font-size: 14px;
  transition: 0.4s;
  width: 150px;
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

@media (max-width: 1200px) {
  .search-box {
    position: absolute;
    left: 10px;
    background: #cfd8dc;
    border-radius: 40px;
    background-color: white;
  }

  .search-box:hover {
    width: 25vw;
    background-color: #cfd8dc;
  }

  .search-box:hover > .search-txt {
    width: 100vw;
    padding: 0 6px;
    margin-top: 8px;
  }

  .search-btn {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: 0.4s;
    color: white;
    cursor: pointer;
  }

  .search-box:hover > .search-btn {
    background: #cfd8dc;
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
    color: #263238;
    font-size: 16px;
    transition: 0.4s;
    width: 0px;
    font-weight: bold;
  }
}

@media (max-width: 450px) {
  .v-tab {
    min-width: 10vw !important;
  }

  .search-box:hover {
    width: 80vw;
  }
}
</style>
