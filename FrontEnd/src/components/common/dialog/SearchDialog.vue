<template>
  <vs-dialog width="450px" not-center v-model="alert" v-on:close="close" not-close>
    <template #header>
      <v-text-field
        class="mt-2"
        v-model="searchValue"
        append-icon="mdi-magnify"
        label="닉네임으로 검색"
        @keyup.enter="search()"
        single-line
        hide-details
        solo
        outlined
        dense
        flat
        rounded
        autofocus
        placeholder="닉네임으로 검색"
      ></v-text-field>
    </template>
    <template v-if="!users || users.length == 0 || searchValue.length == 0">
      <SearchNoDataCard :icon="'magnify'" :text="'검색 결과가 없습니다'"> </SearchNoDataCard>
    </template>
    <template v-else>
      <v-card
        class="overflow-y-auto"
        color="blue-grey lighten-5"
        flat
        height="400"
        max-height="400"
        rounded="xl"
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
  </vs-dialog>
</template>
<script>
import SearchCard from '../card/SearchCard.vue';
import { searchNickname } from '../../../api/search';
import SearchNoDataCard from '../../../components/common/card/SearchNoDataCard.vue';
export default {
  components: { SearchCard, SearchNoDataCard },
  props: ['alert', 'searchValue'],
  data() {
    return {
      users: '',
    };
  },
  methods: {
    close() {
      this.$emit('close');
    },
    search() {
      searchNickname(
        this.searchValue,
        (response) => {
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
        this.searchValue = '';
        this.users = '';
      } else {
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
