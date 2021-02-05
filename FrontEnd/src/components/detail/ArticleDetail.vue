<template>
  <div class="grid-container-article-detail">
    <v-sheet class="mx-auto" elevation="1" rounded="xl">
      <div class="d-flex">
        <div class="avatar mt-4">
          <vs-avatar size="100" circle>
            <img
              src="https://i.pinimg.com/originals/df/30/85/df30856a75229609c25dc94767ebde7b.jpg"
              alt=""
            />
          </vs-avatar>
        </div>
        <div class="username flex-grow-1 ml-8 mt-4">
          <b> {{ username }}</b>
          <div class="introduction">
            {{ introduction }}
          </div>
        </div>

        <div class="follow_btn flex-grow-0 align-center">
          <vs-button> Follow </vs-button>
        </div>
      </div>

      <div class="picture mt-4">
        <v-img
          max-height="600"
          src="https://cdn.crowdpic.net/detail-thumb/thumb_d_897F820200BBD76525D17D2830783B8D.jpg"
          :aspect-ratio="16 / 9"
          :width="width"
        >
        </v-img>
      </div>

      <!-- <div class="created_at ml-4">2hours ago</div> -->
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
          {{ title }}
        </div>
        <div class="mt-3" :content="content">
          {{ content }}
        </div>
      </div>
      <div class="hashtag ml-3 mt-4">
        <v-chip-group>
          <v-chip>#서울</v-chip>
          <v-chip>#광화문</v-chip>
          <v-chip>#당일여행</v-chip>
        </v-chip-group>
      </div>
      <div class="created_at ml-4">
        <div class="d-flex ">
          <vs-avatar>
            <img
              src="https://image.kkday.com/v2/image/get/w_960%2Cc_fit%2Cq_55%2Ct_webp/s1.kkday.com/product_218/20191118021303_4pR1Q/jpg"
              alt=""
            />
          </vs-avatar>
          <div class="ml-4 d-flex align-center">
            <b>Jorge Watson</b>
          </div>
        </div>
        <div class="ml-15 ml-4">
          Oh~ I like your picture! Check the link in our bio
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
export default {
  data: () => ({
    width: 200,
    events: [],
    input: null,
    nonce: 1,
    username: '강아지',
    introduction: '여행을 좋아하는 프로여행러',
    title: 'Gwanghwa..moon.. always 짜릿',
    content:
      '열기구타고 두둥실? 택도없는소리~ 광화문에서 한복입고 사진찍어주는건 언제나 짜릿하다뀨..',
  }),
  /////
  // computed: {
  //   timeline () {
  //     return this.events
  //   },
  // },

  methods: {
    comment() {
      // time 나오는 방식 수정
      // const time = (new Date()).toTimeString()
      // this.events.push({
      //   id: this.nonce++,
      //   text: this.input,
      //   time: time.replace(/:\d{2}\sGMT-\d{4}\s\((.*)\)/, (contents) => {
      //     return ` ${contents.split(' ').map(v => v.charAt(0)).join('')}`
      //   }),
      // })
      // this.input = null
    },
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

@media (max-width: 450px) {
  .grid-container-article-detail {
    display: grid;
    grid-template-columns: 0.1fr 1fr 1fr 1fr;
    grid-template-rows: 35vh max-content max-content max-content max-content max-content max-content;
    gap: 5px 0px;
    grid-template-areas:
      'picture '
      'avatar username . follow_btn'
      'avatar username . follow_btn'
      'buttons buttons buttons buttons'
      'article article article article'
      'hashtag hashtag hashtag hashtag'
      'created_at created_at created_at created_at';
  }

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
    grid-area: username;
    display: flex;
    flex-direction: column;
    align-self: center;
    justify-self: start;
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
}
</style>
