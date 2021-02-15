<template>
  <div class="grid-container-article-detail">
    <v-sheet class="mx-auto" elevation="1" rounded="xl" max-width="600">
      <div class="d-flex">
        <div class="avatar mt-4">
          <v-avatar v-if="boardData.profileimag" circle size="40">
            <img :src="boardData.profileimage" />
          </v-avatar>
          <v-avatar v-else circle size="40" color="blue-grey" class="font-weight-medium display-2">
            <v-icon color="white">mdi-emoticon-happy</v-icon>
          </v-avatar>
        </div>
        <div v-if="boardData.username" class="username flex-grow-1 ml-8 mt-4">
          <b> {{ boardData.username }}</b>

          <div v-if="boardData.mystory" class="mystory__div">
            {{ boardData.mystory }}
          </div>
          <div v-else class="mystory__div">
            자기소개가 없습니다
          </div>
        </div>

        <div class="follow_btn flex-grow-0 align-center">
          <vs-button> Follow </vs-button>
        </div>
      </div>

      <div class="picture mt-4">
        <v-carousel v-if="boardData.contentimage">
          <v-carousel-item
            v-for="(item, i) in boardData.contentimage"
            :key="i"
            :src="item.filePath"
            reverse-transition="fade-transition"
            transition="fade-transition"
          ></v-carousel-item>
        </v-carousel>
        <v-card v-else>
          이미지가 없습니다
        </v-card>
      </div>
      <div class="buttons mt-2">
        <vs-button icon color="danger">
          <i class="bx bxs-heart"></i>
        </vs-button>

        <vs-button icon color="warn">
          <i class="bx bxs-comment"></i>
        </vs-button>

      </div>

      <div class="article ml-4 mt-4">
        <div class="mb-3">
          {{ boardData.title }}
        </div>
        <div class="mt-3" >
          {{ boardData.content }}
        </div>
      </div>
      <div class="hashtag ml-3 mt-4">
        <v-chip-group>
          <v-chip v-for="(tag, i) in boardData.tag" :key="i">{{ tag.content }} </v-chip>
        </v-chip-group>
      </div>

        <div class="comment_list mt-4">
          <div v-if="boardData.comment" class="ml-4 d-flex" >
            <v-avatar>
              <img
                src="https://image.kkday.com/v2/image/get/w_960%2Cc_fit%2Cq_55%2Ct_webp/s1.kkday.com/product_218/20191118021303_4pR1Q/jpg"
                alt=""
              />
            </v-avatar>
              <b>Jorge Watson</b>
          </div>
          <div v-else>
            댓글이 없습니다
          </div>
        </div>

        <div class="ml-15 ml-4">
          <!-- 댓글 생성하는곳, 일단은 댓글 갯수 유뮤찾고 거기에 인풋까지 -->
          {{ boardDetail }}
          <div class="d-flex">
            <vs-button icon color="primary" relief class="mt-4">
              <i class="bx bxs-paper-plane"></i> Reply
            </vs-button>
            <vs-input border></vs-input>
          </div>
        </div>

    </v-sheet>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
// import { boardDetail } from '../../api/board';
export default {
    data: () => ({
    width: 200,
    input: null,
    boardData: {
      id: '',
      profileimage: '',
      contentimage: [],
      username: '',
      mystory: '',
      title: '',
      content: '',
      tag: [],
      comment: [],
    },
    // commentCreateData: {
    // id: null,
    // content: null,
    // },
  }),
  created() {
    this.boardData.id = this.$route.params['id'];
    console.log("created", this.boardDetail)
  },
  computed: {
    ...mapGetters(['getBoardDetail']),
  },

  methods: {
  },

  mounted() {
    console.log('mounted', this.boardData);
    this.BOARDDETAIL(this.boardDetail.id);
    if (this.boardDetail.title) {
    this.boardData.title = this.boardDetail.title; }
    this.boardData.content = this.boardDetail.content;
    this.boardData.username = this.boardDetail.user.nickname;
    this.boardData.mystory = this.boardDetail.user.mystory;
    this.boardData.profileimage = this.boardDetail.user.profile.filePath;
    this.boardData.contentimage = this.boardDetail.image;
    this.boardData.tag = this.boardDetail.tag;
    this.boardData.comment = this.boardDetail.comment;
    console.log('mounted after', this.boardData);
  },

};
</script>

<style scoped>

.picture {
  display: flex;
  justify-content: center;
  grid-area: picture;
}

.avatar {
  grid-area: avatar;
  align-self: center;
  justify-self: start;
}

.username {
  font-size: 1.5em;
  grid-area: username;
  display: flex;
  flex-direction: column;
  align-self: center;
  justify-self: start;
}

.username .introduction {
  font-size: 0.8em;
}

.buttons {
  display: flex;
  justify-content: flex-end;
}

.article {
  display: flex;
  flex-direction: column;
}

.hastag v-chip {
  margin-left: 3px;
}

.follow_btn {
  max-height: 50%;
  display: flex;
  align-self: center;
  justify-self: end;
}

.span.v-chip.v-chip--no-color.theme--light.v-size--default {
  margin: 4px;
}

@media (max-width: 450px) {

}
</style>
