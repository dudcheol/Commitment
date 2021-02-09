<template>
  <v-app id="inspire">
    <v-main class="grey lighten-3" >
      <v-container >
        <v-row>
          <v-col class="sidebar_left" cols="12" sm="2">
            <div class="d-flex justify-center">
              <SelectZone />
            </div>
          </v-col>
          <v-col class="mainslot" cols="12" sm="8">
            <v-sheet min-height="70vh" rounded="lg">
              <div class="mainpage">
                  <Top3 />
                <div class="whitebox">
                <SearchBar/>
                <After4 />
                </div>
                <br />
              </div>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import SelectZone from '../../components/index/rank/SelectZone';
import Top3 from '../../components/index/rank/Top3';
import SearchBar from '../../components/index/rank/SearchBar';
import After4 from '../../components/index/rank/After4';
export default {
  name: 'Rank',

  components: {
    SelectZone,
    Top3,
    SearchBar,
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
.whitebox{
  background-color: white;
}
@media (max-width: 450px) {
  .search {
    display: none;
  }

  .navbar_right {
    display: none;
  }
}
</style>
