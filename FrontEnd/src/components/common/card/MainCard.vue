<template>
  <div>
    <template>
      <v-card class="mx-auto" elevation="0" rounded="xl" style="max-width:680px">
        <div class="px-4 pt-4">
          <div class="d-flex flex-row" style="height:40px">
            <div class="flex-grow-0 cursor-pointer" @click="clickProfile">
              <v-avatar v-if="data.user.profile" circle size="40">
                <img :src="data.user.profile.filePath" />
              </v-avatar>
              <v-avatar
                v-else
                circle
                size="40"
                color="blue-grey"
                class="font-weight-medium display-2"
              >
                <v-icon color="white">mdi-emoticon-happy</v-icon>
              </v-avatar>
            </div>
            <div class="flex-grow-1 ml-2">
              <h3>
                <span class="cursor-pointer" @click="clickProfile">{{ data.user.nickname }}</span>
              </h3>
              <p class="text-caption">
                {{ data.commit.address }}
              </p>
            </div>
            <div class="flex-grow-0 align-center" v-if="user.email != data.email">
              <v-btn
                text
                rounded
                :color="hasFollowed ? 'primary' : 'blue-grey lighten-3'"
                :ripple="false"
                @click="clickFollow"
                ><strong>{{ hasFollowed ? '팔로우' : '팔로우 취소' }}</strong></v-btn
              >
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
                    <v-list-item
                      v-for="(item, index) in etc"
                      :key="'MainCardEdit' + index"
                      :ripple="false"
                      dense
                      @click="item.func"
                    >
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
          <h3 class="font-weight-regular">{{ data.content }}</h3>
        </div>

        <v-carousel
          v-if="data.image.length > 1"
          class="mt-2"
          hide-delimiter-background
          :continuous="false"
          :ripple="false"
        >
          <v-carousel-item
            v-for="(item, i) in data.image"
            :key="data.id + 'Img' + i"
            :src="item.filePath"
            :ripple="false"
          ></v-carousel-item>
        </v-carousel>

        <v-img
          v-if="data.image.length == 1"
          :src="data.image[0].filePath"
          height="auto"
          class="mt-2"
          max-height="680px"
        >
        </v-img>

        <div class="px-4 pt-1 pb-3">
          <div class="align-self-center">
            <v-chip-group>
              <v-chip
                v-for="tag in data.tag"
                :key="data.id + tag.id"
                color="blue-grey lighten-5"
                text-color="blue-grey lighten-1"
                :ripple="false"
                small
              >
                {{ tag.content }}
              </v-chip>
            </v-chip-group>
          </div>
          <div class="d-flex flex-row justify-end">
            <vs-button icon color="danger" flat @click="clickLike" :active="likeActive">
              <i class="bx bxs-heart"></i>{{ data.like.length }}
            </vs-button>
            <vs-button icon color="dark" flat @click="clickCard">
              <i class="bx bxs-message"></i>{{ data.comment.length }}
            </vs-button>
          </div>
        </div>
      </v-card>
    </template>
    <template>
      <BadgeDialog
        :confirm="badgeflag"
        :confirmTitle="'뱃지 획득!'"
        :confirmContent="badgemsg"
        :badgename="badgename"
        :path="path"
        @close="badgeRemain()"
      ></BadgeDialog>
    </template>
  </div>
</template>

<script>
import { removeBoard } from '../../../api/board';
import { mapActions, mapGetters } from 'vuex';
import { badgeCheck } from '../../../api/badge';
import { like } from '../../../api/like';
import { follow } from '../../../api/follow';
import BadgeDialog from '../../../components/common/dialog/BadgeDialog.vue';

export default {
  components: { BadgeDialog },
  props: ['data'],
  data() {
    return {
      etc: [
        // { icon: 'mdi-pencil-outline', text: '수정', func: this.modify },
        { icon: 'mdi-trash-can-outline', text: '삭제', func: this.remove },
      ],
      likeActive: false,
      hasFollowed: true,
      badgeflag: false,
      badgemsg: '',
      badgename: '',
      badgearr: [],
      badgeIndex: 0,
      path: '',
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'], following: ['getFollowingList'] }),
  },
  watch: {
    following(val) {
      this.hasFollowed = this.checkFollowing(val);
    },
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST']),
    clickCard() {
      this.$store.commit('SELECTED_BOARD_ID', this.data.id);
      this.$router.push({ name: 'Detail' });
    },
    clickProfile() {
      this.$store.commit('SELECTED_USER_ID', this.data.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
    clickLike() {
      if (this.likeActive) {
        this.likeActive = false;
        const compare = this.user.email;
        for (let i = 0; i < this.data.like.length; i++) {
          if (this.data.like[i].email == compare) {
            this.data.like.splice(i, 1);
            break;
          }
        }
      } else {
        this.likeActive = true;
        this.data.like.push({ email: this.user.email });
      }
      like(
        this.user.email,
        this.data.id,
        this.data.user.email,
        () => {},
        (error) => {
          console.log(
            '%cerror MainCard.vue line:142 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    clickFollow() {
      follow(
        this.user.email,
        this.data.user.email,
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);
          badgeCheck(
            this.user.email,
            (response) => {
              this.badgeIndex = 0;
              this.badgearr = response.data;
              if (this.badgearr[0].result == 'yes') {
                this.badgemsg = this.badgearr[0].msg;
                this.badgename = this.badgearr[0].badge;
                this.path = require('../../../assets/img/badge/' + this.badgename + '.png');
                this.badgeflag = true;
                this.badgeIndex++;
              }
            },
            (error) => {
              console.log(error);
            }
          );
        },
        (error) => {
          console.log(
            '%cerror MainCard.vue line:173 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    badgeRemain() {
      this.badgeflag = false;
      if (this.badgeIndex < this.badgearr.length) {
        this.badgemsg = this.badgearr[this.badgeIndex].msg;
        this.badgename = this.badgearr[this.badgeIndex].badge;
        this.path = require('../../../assets/img/badge/' + this.badgename + '.png');
        this.badgeflag = true;
        this.badgeIndex++;
      }
    },
    checkFollowing(list) {
      const compare = this.data.user.email;
      for (let i = 0; i < list.length; i++) {
        if (list[i].email == compare) return false;
      }
      return true;
    },
    checkLike() {
      const compare = this.user.email;
      for (let i = 0; i < this.data.like.length; i++) {
        if (this.data.like[i].email == compare) return true;
      }
      return false;
    },
    remove() {
      removeBoard(
        this.data.id,
        () => {
          this.$store.commit('BOARD_REFRESH');
          this.$emit('removed');
        },
        (error) => {
          console.log(
            '%cerror MainCard.vue line:219 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    modify() {},
  },
  created() {
    this.likeActive = this.checkLike();
    this.hasFollowed = this.checkFollowing(this.following);
  },
};
</script>

<style scoped>
.cursor-pointer {
  cursor: pointer;
}
</style>
