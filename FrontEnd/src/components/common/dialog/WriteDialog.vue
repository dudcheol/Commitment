<template>
  <div class="center">
    <vs-dialog
      scroll
      blur
      overflow-hidden
      auto-width
      prevent-close
      v-model="web"
      v-on:close="close"
    >
      <template #header>
        <div class="d-flex flex-column">
          <h2 class="text-center">커밋하기</h2>
          <v-chip small outlined>
            <i class="bx bxs-map" style="vertical-align:middle"></i
            >{{ address }}
          </v-chip>
        </div>
      </template>
      <div class="con-content dialog">
        <div class="d-flex flex-row">
          <div class="d-flex align-center">
            <vs-avatar circle>
              <img
                src="https://cdnweb01.wikitree.co.kr/webdata/editor/202009/16/img_20200916152758_395c9c8b.webp"
              />
            </vs-avatar>
          </div>
          <div class="flex-column pl-2">
            <h3>{{ username }}</h3>
            <div>
              <vs-select
                placeholder="공개설정"
                v-model="value"
                style="width:85px"
              >
                <vs-option label="공개" value="1">
                  공개
                </vs-option>
                <vs-option label="비공개" value="2">
                  비공개
                </vs-option>
              </vs-select>
            </div>
          </div>
        </div>
        <v-textarea
          solo
          flat
          name="input-7-4"
          :label="username + '님, 여기는 어떤 곳인가요?'"
          auto-grow
        ></v-textarea>
      </div>
      <template #footer>
        <div class="d-flex align-center">
          <h3>게시물에 추가</h3>
          <div class="d-flex flex-row ml-auto">
            <vs-button
              size="l"
              circle
              icon
              color="#00c853"
              flat
              :active="active == 5"
              @click="active = 5"
            >
              <i class="bx bxs-photo-album"></i>
            </vs-button>
            <vs-button
              size="l"
              circle
              icon
              color="#ffd600"
              flat
              :active="active == 5"
              @click="active = 5"
            >
              <i class="bx bxs-smile"></i>
            </vs-button>
            <vs-button
              size="l"
              circle
              icon
              color="#304ffe"
              flat
              :active="active == 5"
              @click="active = 5"
            >
              <i class="bx bxs-purchase-tag"></i>
            </vs-button>
          </div>
        </div>
        <vs-button block flat><h2>Commit</h2></vs-button>
      </template>
    </vs-dialog>
  </div>
</template>

<script>
export default {
  props: ['mobile', 'web'],
  data() {
    return {
      value: '',
      username: 'username',
      address: '서울특별시 강남구 테헤란로 123-1',
    };
  },
  methods: {
    close() {
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
.dialog {
  width: calc(100vw - 56px);
}
</style>
