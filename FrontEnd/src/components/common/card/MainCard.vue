<template>
  <v-card class="mx-auto" elevation="0" rounded="xl" style="max-width:680px">
    <div class="px-4 pt-4">
      <div class="d-flex flex-row" style="height:40px">
        <div class="flex-grow-0">
          <vs-avatar circle size="40">
            <img :src="data.user.profile" alt="" />
          </vs-avatar>
        </div>
        <div class="flex-grow-1 ml-2">
          <h3>{{ data.user.nickname }}</h3>
          <p class="text-caption">{{ data.address }}</p>
        </div>
        <div class="flex-grow-0 align-center" v-if="user.email != data.email">
          <v-btn text rounded color="primary" :ripple="false"><strong>팔로우</strong></v-btn>
        </div>
        <div class="flex-grow-0 align-center" v-else>
          <v-menu offset-y left rounded="lg" transition="slide-y-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-btn :ripple="false" icon v-bind="attrs" v-on="on">
                <v-icon>mdi-dots-horizontal</v-icon>
              </v-btn>
            </template>

            <v-list>
              <v-list-item-group>
                <v-list-item v-for="(item, index) in etc" :key="item" :ripple="false" dense>
                  <v-list-item-icon>
                    <v-icon
                      v-text="item.icon"
                      :color="index == etc.length - 1 ? 'error' : ''"
                    ></v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title
                      v-text="item.text"
                      :class="index == etc.length - 1 ? 'error--text' : ''"
                    ></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-menu>
        </div>
      </div>
    </div>

    <div class="px-6 pt-4">
      {{ data.content }}
    </div>

    <v-img :src="data.image[0]" height="auto" class="mt-2"> </v-img>

    <div class="px-4 pt-1 pb-3">
      <div class="d-flex flex-row justify-space-between">
        <div class="align-self-center">
          <v-chip-group>
            <v-chip
              v-for="tag in data.tag"
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
          <vs-button icon color="danger" flat :active="active == 2" @click="active = 2">
            <i class="bx bxs-heart"></i>{{ data.like.length }}
          </vs-button>
          <vs-button icon color="dark" flat :active="active == 2" @click="active = 2">
            <i class="bx bxs-message"></i>{{ data.comment.length }}
          </vs-button>
        </div>
      </div>
    </div>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  props: ['data'],
  data() {
    return {
      etc: [
        { icon: 'mdi-pencil-outline', text: '수정' },
        { icon: 'mdi-trash-can-outline', text: '삭제' },
      ],
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
};
</script>

<style scoped></style>
