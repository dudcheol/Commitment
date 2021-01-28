<template>
  <v-app id="inspire">
    <!-- 메인페이지파트 -->
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col class="sidebar_left" cols="12" sm="2">
            <div class="d-flex justify-center">
              <SelectZone />
            </div>
            <!--             <NotYetAddArticle /> -->
          </v-col>

          <v-col class="mainslot" cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <div class="mainpage">
                <!-- 일단 3개컴포넌트 넣어보고 길이 결정해보기 -->
                <br />
                <Top3 />
                <After4 />
                <br />
              </div>
            </v-sheet>
          </v-col>

          <v-col cols="12" sm="2">
            <div class="radar mt-4 mb-3">
              <v-slider :thumb-size="15" thumb-label="always"></v-slider>
            </div>
            <v-sheet rounded="lg" min-height="200">
              <!--             오른쪽 빈공간 -->
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import SelectZone from '../../components/index/rankpage/SelectZone';
import Top3 from '../../components/index/rankpage/Top3';
import After4 from '../../components/index/rankpage/After4';
export default {
  name: 'Rank',

  components: {
    // CommitNow,
    // NotYetAddArticle,
    SelectZone,
    Top3,
    After4,
  },

  data: () => ({
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
  }),
};
</script>

<style scoped>
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
