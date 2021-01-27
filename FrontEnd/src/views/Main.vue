<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
    <div class="search">
      <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
      <vs-button circle icon flat >
         <i class="bx bx-tag"></i>
      </vs-button>
      <vs-button circle icon flat >
         <i class="bx bx-search"></i>
      </vs-button>
      <vs-input placeholder="Search">
      </vs-input>
    </div>

      <!-- tab 바뀌는부분 -->
    <div>
      <v-tabs
      background-color="transparent"
      
      >
        <v-tab
          v-for="item in items"
          :key="item"
        >
        <v-icon>
          {{ item }}
        </v-icon>
        </v-tab>
      </v-tabs>
    </div>
    <!-- 아바타 바뀌는 지역 -->
    <div>
      <!-- 일단 정렬을 위한 div -->
    </div>
    <div class="navbar_right">
      <vs-button
        circle
        icon
        flat
      >
        <i class='bx bx-plus' ></i>
      </vs-button>

      <vs-button
        
        circle
        icon
        flat
      >
        <i class='bx bxl-whatsapp' ></i>
      </vs-button>

      <vs-button
       
        circle
        icon
        flat
      >
        <i class='bx bx-up-arrow-alt' ></i>
      </vs-button>
    </div>
    </v-app-bar>
    <!-- 메인페이지파트 -->
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col
            class="sidebar_left"
            cols="12"
            sm="2"
          >

            <ProfileSummary />
              <!-- 첫번째 넣어야할거 -->

            <NotYetAddArticle />

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
                <FollowerMap />
                <MyState />
                <AllArticle />
              </div>
            </v-sheet>
          </v-col>

          <v-col
            cols="12"
            sm="2"
          >
          <div class="radar mt-4 mb-3">
            <v-slider
            :thumb-size="15"
            thumb-label="always"
            ></v-slider>
          </div>
            <v-sheet
              rounded="lg"
              min-height="200"
            >
            <CommitNow />
            </v-sheet>
              
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  import AllArticle from './components/mainpage/AllArticle'
  import FollowerMap from './components/mainpage/FollowerMap'
  import MyState from './components/mainpage/MyState'
  import CommitNow from './components/mainpage/CommitNow'
  import NotYetAddArticle from './components/mainpage/NotYetAddArticle'
  import ProfileSummary from './components/mainpage/ProfileSummary'

  export default {
    name: "MainPage",

    components: {
      AllArticle,
      FollowerMap,
      MyState,
      CommitNow,
      NotYetAddArticle,
      ProfileSummary,

    },

    data: () => ({
      items: [
          'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
        ],  
    }),
  }
</script>

<style >

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