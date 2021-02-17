<template>
  <div class="d-flex justify-center">
    <!-- top -->
    <div class="top" :justify="dynamicJustify">
      <div>
        <vs-dialog
          blur
          scroll
          overflow-hidden
          not-close
          v-model="active"
          width="400px"
        >
          <template #header>
            <h3>
              프로필 사진 변경
            </h3>
          </template>
          <div class="con-content">
            <div id="mobileHidden" >
              <input type="file" @change="fileSelected" />
              <img v-if="image" :src="image" width="300" />
            </div>
            <div></div>
            <div class="footer-dialog">
              <vs-button block @click="upload">
                업로드
              </vs-button>
            </div>
          </div>
        </vs-dialog>

        
        <div class="profileImg " v-if="imgSrc!=null">
          <v-list-item-avatar size="150">
            <img :src="imgSrc" alt="picture" @click="showModal()" />
          </v-list-item-avatar>
        </div>
        <div class="profileImg " v-else>
          <v-avatar
              circle
              size="150"
              color="blue-grey"
              class="font-weight-medium display-2"
            >
              <v-icon color="white" size="100">mdi-emoticon-happy</v-icon>
            </v-avatar>
        </div>
      </div>
      <v-card class="mx-auto" flat :width="width">
        <v-card-title>
          {{ this.age }} |
          <span v-if="this.gender == 'm'">남성</span>
          <span v-else-if="this.gender == 'w'">여성</span>
        </v-card-title>
        <v-card-subtitle>
          {{ this.userId }}
        </v-card-subtitle>

        <div class="detail_left">
          <vs-button size="l" circle icon color="success" flat>
            <i class="bx bxs-check-square"></i>{{ this.cnt }}
          </vs-button>
          <Follower @close="followingKey++" />
          <Following :key="followingKey" @close="followingKey++" />
          <v-spacer></v-spacer>
          <div class="badge" v-if="this.user.email == this.email">
            <ProfileEdit />
          </div>
        </div>

        <v-card-actions>
          <v-btn icon @click="show = !show">
            <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show">
            <v-card-text>
              {{ this.mystory }}
            </v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
      <div>
<<<<<<< Updated upstream
        <div class="badge" v-if="badge!=null">
          <v-list-item-avatar size="70">
            <img :src="require(`@/assets/img/badge/${badge}.png`)" alt="" />
          </v-list-item-avatar>
        </div>
        <div class="badge" v-else>
          <v-list-item-avatar size="70">
            <img :src="require(`@/assets/img/badge/badge0.png`)" alt="" />
          </v-list-item-avatar>
        </div>
=======
         <div class="badge" v-if="badge!=null">
      <v-list-item-avatar size="70">
        <img :src="require(`@/assets/img/badge/${badge}.png`)" alt="" />
      </v-list-item-avatar>
    </div>
    <div class="badge" v-else>
      <v-list-item-avatar size="70">
        <img :src="require(`@/assets/img/badge/badge0.png`)" alt="" />
      </v-list-item-avatar>
    </div>
>>>>>>> Stashed changes
      </div>
    </div>
  </div>
</template>
<script scoped>
import { mapGetters } from "vuex";
import Follower from "../../common/dialog/Follower";
import Following from "../../common/dialog/Following";
import ProfileEdit from "../../common/dialog/ProfileEdit";
import { searchUserByNickname } from "../../../api/account";
import { userCommitCount } from "../../../api/commit";
import { editProfileImg } from "../../../api/img";
export default {
  components: {
    Follower,
    Following,
    ProfileEdit,
  },
  data: () => ({
    active: false,
    show: false,
<<<<<<< Updated upstream
    //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
    email: '',
    gender: '',
    badge: '',
    age: '',
    imgSrc: '',
    mystory: '',
=======
    id: "dudcheol", //this.$route.params.id로 받은 현재 유저의 닉네임
    //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
    email: "",
    gender: "",
    badge: "badge0",
    age: "",
    imgSrc: "",
    mystory: "",
>>>>>>> Stashed changes
    //email로 /commit/total에서 가져온 커밋수
    cnt: 0,
    image: "",
    file: null,
    followingKey: 0,
  }),
  computed: {
    ...mapGetters({
      user: ["getUserInfo"],
      userId: ["getSelectedUserId"],
    }),

    width() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "200px";
        case "sm":
          return "200px";
        case "md":
          return "500px";
        case "lg":
          return "600px";
        case "xl":
          return "900px";
      }
      return 700;
    },
  },
  methods: {
    showModal() {
      if (this.user.email == this.email) {
        this.active = true;
      }
    },
    fileSelected(evt) {
      this.file = evt.target.files.item(0);
      console.log(typeof this.file);
      const reader = new FileReader();
      reader.addEventListener("load", this.imageLoaded);
      reader.readAsDataURL(this.file);
    },
    imageLoaded(evt) {
      this.image = evt.target.result;
    },
    upload() {
      const form = new FormData();
      form.append("file", this.file);
      form.append("email", this.email);
      editProfileImg(
        form,
        (response) => {
          console.log("성공" + response);
        },
        (error) => {
          console.log("에러" + error);
        }
      );
    },
  },
  created() {
    searchUserByNickname(
      { keyword: this.userId },
      (response) => {
        
        const content = response.data.content[0];
        console.log(this.userId+"로 받아온 이메일"+content.email);
        this.email = content.email;
        this.gender = content.gender;
        this.age = content.age;
        if(content.profile!=null){
          this.imgSrc = content.profile.filePath;
        }else{
          this.imgSrc = null;
        }
        this.badge = content.badge;
        this.mystory = content.mystory;
<<<<<<< Updated upstream
=======
        console.log(this.email, "의 마이스토리", this.mystory);
>>>>>>> Stashed changes
        userCommitCount(
          this.email,
          (response) => {
            this.cnt = response.data;
          },
          (error) => {
            console.log("cnt에러" + error);
          }
        );
      },
      (error) => {
        console.log("img에러" + error);
      }
    );
  },
};
</script>

<style scoped>
@media screen and (max-width: 1000px) {
  #mobileHidden {
    display: none;
  }
}
.profileImg {
  /* background-color:rgba(0,0,0,.7); */
  /* width:100%;
    height:100%; */
  /* position:absolute; */
  opacity: 1;
  transition: opacity 0.5s;
}
.profileImg:hover {
  opacity: 0.5;
}
.badgeImg {
  width: 35%;
  text-align: center;
  margin-top: -70%;
}
.detail {
  display: flex;
  justify-content: space-between;
}
.detail_left {
  display: flex;
}
.numbers {
  font-weight: bold;
  color: grey;
}
.top {
  display: flex;
}
.nickname {
  font-size: 20px;
  font-weight: bold;
  color: black;
}
.texts {
  color: rgb(72, 199, 72);
}

.image-box {
  margin-top: 0px;
  padding-bottom: 0px;
}

.image-box label {
  display: inline-block;
  padding: 0px 0px;
  /* color: #fff; */
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}
.file-preview-wrapper {
  padding: 10px;
  position: center;
}
.file-preview-wrapper > img {
  position: center;
  max-width: 300px;
  max-height: 300px;
  z-index: 10;
}

.header-fixed {
  position: fixed;
  top: 0;
  height: 56px;
  width: 100%;
  z-index: 999;
  background-color: white;
}
#container {
  min-height: 100vh;
}
</style>
