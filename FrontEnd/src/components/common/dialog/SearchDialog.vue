<template>
  <vs-dialog
    width="450px"
    not-center
    v-model="alert"
    v-on:close="close"
    not-close
  >
    <template #header>
      <v-text-field
        v-model="searchValue"
        append-icon="mdi-magnify"
        label="Search"
        @keyup.enter="search()"
        single-line
        hide-details
      ></v-text-field>
    </template>
    <template v-if="!users || users.length == 0">
      <NoDataCard :icon="'magnify'" :text="'검색 결과가 없습니다'">
      </NoDataCard>
    </template>
    <template v-else>
      <v-card
        v-scroll.self="onScroll"
        class="overflow-y-auto"
        color="grey lighten-4"
        flat
        height="400"
        max-height="400"
      >
        <search-card
          v-for="(item, uid) in users"
          :key="uid"
          :username="item.nickname"
          :address="item.mystory"
          :img="item.profile"
          @click="mypage(item.nickname)"
          class="mb-2"
        >
        </search-card>
      </v-card>
    </template>

    <template #footer>
      <div class="con-footer">
        <vs-button block @click="close">
          닫기
        </vs-button>
      </div>
    </template>
  </vs-dialog>
</template>
<script>
import SearchCard from "../card/SearchCard.vue";
import { searchNickname } from "../../../api/search";
import NoDataCard from "../../../components/common/card/NoDataCard.vue";
export default {
  components: { SearchCard, NoDataCard },
  props: ["alert"],
  data() {
    return {
      searchValue: "",
      users: "",
    };
  },
  methods: {
    close() {
      this.$emit("close");
    },
    search() {
      searchNickname(
        this.searchValue,
        (response) => {
          console.log(response.data.content);
          this.users = response.data.content;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    mypage(nick) {
      this.close();
      this.$store.commit('SELECTED_USER_ID', nick);
      this.$router.push({ name: 'MyPage' });
    },
  },
  watch: {
    searchValue() {
      if (this.searchValue.trim().length == 0) {
        this.searchValue = "";
        this.users="";
      }else{
            this.search();
      }
    },
  },
};
</script>

<style>
.vs-dialog {
  max-height: 600px;
}
</style>
