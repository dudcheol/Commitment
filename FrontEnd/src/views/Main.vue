<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
    <div>
      <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
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
      <v-avatar
        class="hidden-sm-and-down"
        color="grey darken-1 shrink"
        size="32"
      ></v-avatar>
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
            >
              <!-- 첫번째 넣어야할거 -->
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
            <v-sheet
              rounded="lg"
              min-height="268"
            >
              <!--  -->
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
          'mdi-home', 'mdi-web', 'mdi-airplane', 'mdi-apple-keyboard-control', 'mdi-account',
        ],  
    }),
  }
</script>

<style >
.v-toolbar__content {
  display: flex;
  justify-content: space-between;
}

v-tab {
  width: 20px;

}

</style>