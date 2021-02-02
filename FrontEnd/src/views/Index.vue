<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat hide-on-scroll>
      
      <v-btn elevation="" fab x-small class="ml-3 d-none d-sm-flex" color="white"
      >
        <v-avatar size="35">
          <img src="../assets/img/main/commitment_logo.jpg" alt="logo">
        </v-avatar>
      </v-btn>

      <div class="search__container ml-3 mt-2 d-flex">
        <div class="search">
          <v-text-field 
          class="search__input"></v-text-field>
          <v-btn class="search__submit" fab elevation="" x-small dark color="blue darken-2">
            <v-icon>mdi-magnify</v-icon></v-btn>
        </div>
      </div>


      <!-- 가운데 탭 항상 가운데에 만들기 버튼개수 상관없이 -->
      <v-tabs centered color="grey darken-1" class="tabs_list">
        <v-tab v-for="item in items" :key="item" :to="item.route">
          <v-icon>
            {{ item.icon }}
          </v-icon>
        </v-tab>
      </v-tabs>


    <div class="d-none d-sm-flex ml-auto">
      <v-btn fab elevation="" x-small dark color="blue darken-2" class="mr-3"
        v-for="item in right_items" :key="item"
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
    alldatalist: [

    ],
  }),
  methods: {
   
    }
};
</script>

<style lang="scss" scoped>

.tabs_list {
  position: absolute; 
  left: 50%; 
  transform: translateX(-50%); 
}

.search__container {
  // background: rgb(143, 150, 143);
  width: 20vw;  
  height: calc(min-content + 5px);
  position: relative;
}

.search {
  --size: 28px;
  // border: 1px solid blue;

  display: flex;
  overflow: hidden;
  font-size: 1em;
  position: relative;
  width: calc(var(--size) + 10px);
  height: calc(var(--size) + 10px);
  // 늘어날때 효과 cubic-bezier 참고
  transition: width 450ms cubic-bezier(0.18, 0.89, 0.32, 1.28);
}


.search__input {
  // padding: .25em 1em;
  border: 0;
  flex-grow: 1;
  z-index: 1;
  width: 100%;
  background: transparent;
  opacity: 0;
  cursor: pointer;
}

.search__submit {
  font-size: 1.5rem;
  background: 0;
  border: 0;
  cursor: pointer;
  border-radius: 50%;
  transition: background 200ms ease-out;
  // width: calc(var(--size) - 10px);
  // height: calc(var(--size) - 10px);
}

.search:focus-within {
  
  width: 100%;
  .search__input {
    opacity: 1;
    z-index: initial;
    cursor: initial;
    width: 100%;
  }
  
  // .search__submit {
  //   background: var(--clr-primary);
  //   color: black;
  //   box-shadow: 0 0 10px rgba(0,0,0,.15);
  //   &:hover,
  //   &:focus {
  //     outline: 0;
  //     // transform: rotate(1turn);
  //   }
  // }
}

v-speed-dial {
  z-index: 0 !important
}

@media (max-width: 450px) {
  .v-tab {
    min-width: 10vw !important

  }

  .search__container {
    z-index: 2px;
    position: absolute;
    width: 90vw;
    background: grey;
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
