<template>
  <v-card class="mx-auto" elevation="0" rounded="xl" style="max-width:680px">
    <div class="px-4 pt-4">
      <div class="d-flex flex-row" style="height:40px">
        <div class="flex-grow-0">
          <vs-avatar circle size="40">
            <img src="https://picsum.photos/200/300" alt="" />
          </vs-avatar>
        </div>
        <div class="flex-grow-1 ml-2">
          <h3>{{ info.name }}</h3>
          <p class="text-caption">{{ info.address }}</p>
        </div>
        <div class="flex-grow-0 align-center">
          <v-btn text rounded color="primary" :ripple="false"
            ><strong>팔로우</strong></v-btn
          >
        </div>
      </div>
    </div>

    <div class="px-6 pt-4">
      {{ info.content }}
    </div>

    <v-img
      src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
      height="auto"
      class="mt-2"
    >
    </v-img>

    <div class="px-4 pt-1 pb-3">
      <div class="d-flex flex-row justify-space-between">
        <div class="align-self-center">
          <v-chip-group>
            <v-chip
              v-for="tag in info.tags"
              :key="tag"
              color="#f5f5f5"
              text-color="#808080"
              label
              :ripple="false"
              small
            >
              {{ tag }}
            </v-chip>
          </v-chip-group>
        </div>
        <div class="d-flex flex-row">
          <vs-button
            icon
            color="danger"
            flat
            :active="active == 2"
            @click="active = 2"
          >
            <i class="bx bxs-heart"></i>{{ info.like }}
          </vs-button>
          <vs-button
            icon
            color="dark"
            flat
            :active="active == 2"
            @click="active = 2"
          >
            <i class="bx bxs-message"></i>{{ info.comment }}
          </vs-button>
        </div>
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      info: {
        name: 'username',
        date: '2020. 1. 28.',
        like: 2,
        comment: 12,
        tags: ['tag1', 'tag2', 'tag3'],
        address: '서울특별시 강남구 테헤란로 123-1',
        content:
          '테스트입니다. 내용이 너무 길어지면 ...으로 표시되는 글자 수 줄일 예정. 사진있으면 1줄내외, 사진없이 글자만있으면 3줄?2줄 내외',
      },
    };
  },
};
</script>

<style scoped></style>
