<template>
  <v-card class="mx-auto" elevation="0" rounded="xl">
    <v-row v-if="data">
      <v-col cols="12" md="8" lg="6" xl="4" class="mx-auto mb-10">
        <div v-if="loading" style="height:65vh" class="d-flex justify-center align-center">
          <v-progress-circular
            :size="70"
            :width="7"
            color="blue-grey lighten-4"
            indeterminate
          ></v-progress-circular>
        </div>
        <div v-else class="px-4 pt-4">
          <div class="d-flex flex-row">
            <div class="flex-grow-0 cursor-pointer" @click="clickProfile" style="cursor:pointer">
              <v-avatar v-if="data.user.profile" circle size="80">
                <img :src="data.user.profile.filePath" />
              </v-avatar>
              <v-avatar
                v-else
                circle
                size="80"
                color="blue-grey"
                class="font-weight-medium display-2"
              >
                <v-icon color="white" size="50">mdi-emoticon-happy</v-icon>
              </v-avatar>
            </div>
            <div class="flex-grow-1 ml-2">
              <h1>
                <span class="cursor-pointer" style="cursor:pointer" @click="clickProfile">{{
                  data.user.nickname
                }}</span>
              </h1>
              <h3 class="font-weight-regular">
                {{ data.commit.address }}
              </h3>
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
          <h1 class="font-weight-regular">{{ data.content }}</h1>
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
              >
                {{ tag.content }}
              </v-chip>
            </v-chip-group>
          </div>
          <div class="d-flex flex-row justify-end">
            <vs-button icon color="danger" flat @click="clickLike" :active="likeActive" size="xl">
              <i class="bx bxs-heart"></i>{{ data.like.length }}
            </vs-button>
            <vs-button icon color="dark" flat size="xl">
              <i class="bx bxs-message"></i>{{ data.comment.length }}
            </vs-button>
          </div>
        </div>

        <v-sheet color="blue-grey lighten-5" rounded="xl" class="pa-4 d-flex mx-1">
          <div class="flex-grow-1">
            <v-text-field
              v-model="commentText"
              solo
              flat
              class="ma-0 pr-3"
              rounded
              @keyup.enter="writeComment"
              :disabled="commentable"
            ></v-text-field>
          </div>
          <v-btn
            height="48"
            elevation="0"
            :ripple="false"
            color="blue-grey lighten-3"
            class="font-weight-black white--text"
            rounded
            @click="writeComment"
            >작성</v-btn
          >
        </v-sheet>

        <div>
          <comment-card
            v-for="(item, index) in data.comment"
            :key="data.id + 'comment' + index"
            :data="item"
            @del="delComment(item)"
          ></comment-card>
        </div>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import { like } from '../../api/like';
import { follow } from '../../api/follow';
import { removeBoard } from '../../api/board';
import { addComment, getCommentList, deleteComment } from '../../api/comment';
import CommentCard from '../common/card/CommentCard.vue';
export default {
  components: { CommentCard },
  data() {
    return {
      etc: [{ icon: 'mdi-trash-can-outline', text: '삭제', func: this.remove }],
      likeActive: false,
      hasFollowed: true,
      width: 200,
      input: null,
      commentText: '',
      commentable: false,
      loading: true,
      errorDialog: false,
    };
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      data: ['getBoardDetail'],
      following: ['getFollowingList'],
    }),
  },
  watch: {
    following(val) {
      this.hasFollowed = this.checkFollowing(val);
    },
    data: {
      immediate: true,
      handler() {
        this.loading = false;
      },
    },
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST']),
    clickProfile() {
      this.$router.push({ name: 'MyPage', params: { email: this.data.user.nickname } });
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
          this.$router.replace('/');
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
    writeComment() {
      if (!this.commentText) return;
      this.commentable = true;
      addComment(
        this.user.email,
        this.data.id,
        0,
        this.commentText,
        () => {
          this.loadComment();
          this.commentText = '';
          this.commentable = false;
        },
        (error) => {
          console.log(
            '%cerror ArticleDetail.vue line:286 ',
            'color: red; display: block; width: 100%;',
            error
          );
          this.commentText = '';
          this.commentable = false;
        }
      );
    },
    loadComment() {
      getCommentList(
        this.data.id,
        (response) => {
          this.data.comment = response.data.content;
          setTimeout(() => {
            window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight);
          }, 200);
        },
        (error) => {
          console.log(
            '%cerror ArticleDetail.vue line:296 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
    delComment(comment) {
      deleteComment(
        comment.id,
        () => {
          this.loadComment();
        },
        (error) => {
          console.log(
            '%cerror ArticleDetail.vue line:325 ',
            'color: red; display: block; width: 100%;',
            error
          );
        }
      );
    },
  },
  activated() {
    this.loading = true;
    this.likeActive = this.checkLike();
    this.hasFollowed = this.checkFollowing(this.following);
  },
};
</script>

<style>
.v-text-field__details {
  display: none;
}
.v-input__slot {
  margin: 0;
}
</style>
