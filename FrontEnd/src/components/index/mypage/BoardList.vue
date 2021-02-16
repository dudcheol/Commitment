<template>
  <div class="rounded-lg">
    <commit-list-item
      v-for="(item, index) in scrolls"
      :key="'mypageCommitList' + index"
      class="mb-2"
    >
    </commit-list-item>
  </div>
</template>
<script>
import CommitListItem from '../../common/card/CommitListItem.vue';
export default {
  components: { CommitListItem },
  name: 'BoardList',
  data: () => ({
    benched: 100,
    scrolls: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'],
    colors: ['indigo', 'warning', 'pink darken-2', 'red lighten-1', 'deep-purple accent-4'],
    value1: 1,
    limit: 0, //무한스크롤
  }),
};
</script>

<style scoped>
.scroll {
  margin-top: 5%;
}
</style>
