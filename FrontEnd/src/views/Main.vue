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
      <vs-avatar text="Logo"/>
      <vs-avatar color="primary" text="primary"/>
      <vs-input placeholder="Search">
      </vs-input>
    </div>

      <!-- tab 바뀌는부분 -->
    <div>
      <v-tabs>
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
      <vs-avatar color="danger" icon="photo_camera" />
      <vs-avatar color="warning" icon="filter_vintage" />
    </div>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col
            cols="12"
            sm="2"
          >
            <v-sheet
              rounded="lg"
              min-height="300"
              class="mb-3"
            >
              <!-- 첫번째 넣어야할거 -->
            </v-sheet>
            <v-sheet
              rounded="lg"
              min-height="300">

            </v-sheet>
          </v-col>

          <v-col
            cols="12"
            sm="8"
          >
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >
              <router-view></router-view>
            </v-sheet>
          </v-col>

          <v-col
            cols="12"
            sm="2"
          >
          <div class="radar mt-4 mb-3">
            <v-slider
             v-model="slider"
            :thumb-size="15"
            thumb-label="always"
            ></v-slider>
          </div>
            <v-sheet
              rounded="lg"
              min-height="268"
            >
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    data: () => ({
      items: [
          'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
        ], 
      value1: 1, 
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

v-tab {
  width: 20px;

}

</style>