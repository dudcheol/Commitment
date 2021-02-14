<template>
  <div class="grid-container-article-detail">
    <v-sheet class="mx-auto" elevation="1" rounded="xl" max-width="800">
      <div class="d-flex">
        <div class="avatar mt-4">
          <v-avatar size="100">
            <img :src="boardData.profileimage" />
          </v-avatar>
        </div>
        <div class="username flex-grow-1 ml-8 mt-4">
          <b> {{ boardData.username }}</b>

          <div class="introduction">
            {{ boardData.mystory }}
          </div>
        </div>

        <div class="follow_btn flex-grow-0 align-center">
          <vs-button> Follow </vs-button>
        </div>
      </div>

      <div class="picture mt-4">
        <v-carousel>
          <v-carousel-item
            v-for="(item, i) in boardData.contentimage"
            :key="i"
            :src="item.filePath"
            reverse-transition="fade-transition"
            transition="fade-transition"
          ></v-carousel-item>
        </v-carousel>
      </div>
      <div class="buttons mt-2">
        <vs-button icon color="danger">
          <i class="bx bxs-heart"></i>
        </vs-button>

        <vs-button icon color="warn">
          <i class="bx bxs-comment"></i>
        </vs-button>

        <vs-button icon color="success" gradient>
          <i class="bx bxs-purchase-tag"></i>
        </vs-button>
      </div>

      <div class="article ml-4 mt-4">
        <div class="mb-3" :title="title">
          {{ boardData.title }}
        </div>
        <div class="mt-3" :content="content">
          {{ boardData.content }}
        </div>
      </div>
      <div class="hashtag ml-3 mt-4">
        <v-chip-group>
          <v-chip v-for="(tag, i) in boardData.tag" :key="i">{{ tag.content }} </v-chip>
        </v-chip-group>
      </div>
      <div class="created_at ml-4">
        <div class="d-flex ">
          <v-avatar>
            <img
              src="https://image.kkday.com/v2/image/get/w_960%2Cc_fit%2Cq_55%2Ct_webp/s1.kkday.com/product_218/20191118021303_4pR1Q/jpg"
              alt=""
            />
          </v-avatar>
          <div class="ml-4 d-flex align-center">
            <b>Jorge Watson</b>
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
      </div>
    </v-sheet>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {

  created() {
    this.boardData.id = this.$route.params['id'];
  },
  computed: {
    ...mapState(['boardDetail']),
  },

  methods: {
    ...mapActions(['BOARDDETAIL']),
    initDetail() {
      
      // this.BOARDDETAIL(this.boardData.id);
      // this.boardData.title = this.boardDetail.title;
      // this.boardData.content = this.boardDetail.content;
      // this.boardData.username = this.boardDetail.user.nickname;
      // this.boardData.mystory = this.boardDetail.user.mystory;
      // this.boardData.profileimage = this.boardDetail.user.profile.filePath;
      // this.boardData.contentimage = this.boardDetail.image;
      // this.boardData.tag = this.boardDetail.tag;
    
    },
  },

  mounted() {
    console.log('mounted, boardData');
    this.initDetail()
    this.BOARDDETAIL(this.boardData.id);
    this.boardData.title = this.boardDetail.title;
    this.boardData.content = this.boardDetail.content;
    this.boardData.username = this.boardDetail.user.nickname;
    this.boardData.mystory = this.boardDetail.user.mystory;
    this.boardData.profileimage = this.boardDetail.user.profile.filePath;
    this.boardData.contentimage = this.boardDetail.image;
    this.boardData.tag = this.boardDetail.tag;
    this.boardData.comment = this.boardDetail.comment;

  },
  data: () => ({
    width: 200,
    events: [],
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

.created_at {
  grid-area: created_at;
}

.buttons {
  grid-area: buttons;
  display: flex;
  justify-content: flex-end;
}

.article {
  grid-area: article;
  display: flex;
  flex-direction: column;
}

.hashtag {
  grid-area: hashtag;
}

.hastag v-chip {
  margin-left: 3px;
}

.follow_btn {
  grid-area: follow_btn;
  max-height: 50%;
  display: grid;
  align-self: center;
  justify-self: end;
}

.span.v-chip.v-chip--no-color.theme--light.v-size--default {
  margin: 4px;
}

v-sheet {
  width: 80vw !important;
}

@media (max-width: 450px) {

}
</style>
