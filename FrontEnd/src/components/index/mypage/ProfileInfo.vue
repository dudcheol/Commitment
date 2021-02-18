<template>
  <v-container>
    <!-- top -->
    <v-row no-gutters>
      <v-col class="d-flex justify-center align-center">
        <div class="pl-8">
          <div v-if="imgSrc != null">
            <div class="profileImg" style="position:relative;">
              <v-list-item-avatar :size="width">
                <img :src="imgSrc" alt="picture" @click="showModal()" />
              </v-list-item-avatar>
            </div>
          </div>
          <div class="profileImg mr-4" v-else>
            <v-avatar
              circle
              :size="width"
              color="blue-grey"
              class="font-weight-medium display-2"
              @click="showModal()"
            >
              <v-icon color="white" :size="width / 2">mdi-emoticon-happy</v-icon>
            </v-avatar>
          </div>
          <div
            v-if="userId == user.nickname"
            class="d-flex justify-end"
            style="position:relative; top:-50px; right:15px; cursor: pointer;"
            @click="showModal()"
          >
            <span
              class="rounded-circle lighten-1 pa-1"
              style="border:3px solid white; background-color:#1e90ff;"
            >
              <v-icon color="white">mdi-plus</v-icon>
            </span>
          </div>
        </div>

        <div class="d-flex flex-column align-center justify-center">
          <div class="d-flex">
            <div class="d-flex justify-center align-center">
              <div
                class="font-weight-black text-h3 text-lg-h1 text-md-h2 text-sm-h4"
                style="min-width:150px"
              >
                {{ userId }}
              </div>
            </div>
            <div>
              <div class="badge" v-if="badge">
                <v-list-item-avatar size="70">
                  <img :src="require(`@/assets/img/badge/${badge}.png`)" alt="" />
                </v-list-item-avatar>
              </div>
            </div>
          </div>

          <div class="d-flex">
            <div class="d-flex">
              <vs-button size="l" circle icon color="success" flat>
                <i class="bx bxs-check-square"></i>{{ this.cnt }}
              </vs-button>
              <Follower @close="followingKey++" />
              <Following :key="followingKey" @close="followingKey++" />
            </div>
            <div v-if="userId != user.nickname">
              <v-btn
                text
                rounded
                color="primary"
                @click="clickFollow"
                class="font-weight-black"
                :ripple="false"
                min-width="101"
              >
                {{ alreadyFollow ? '팔로우 취소' : '팔로우' }}
              </v-btn>
            </div>
          </div>
        </div>

        <div class="d-flex align-center justify-center pl-6">
          <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="400px">
            <template #header>
              <h3>
                프로필 사진 변경
              </h3>
            </template>
            <div class="con-content">
              <span>10MB 미만의 파일만 업로드 할 수 있습니다.</span>
              <div>
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
        </div>
      </v-col>
      <v-col cols="6"></v-col>
    </v-row>
  </v-container>
</template>
<script scoped>
import { mapGetters, mapActions } from 'vuex';
import Follower from '../../common/dialog/Follower';
import Following from '../../common/dialog/Following';
import { getUserInfoByNickname } from '../../../api/account';
import { userCommitCount } from '../../../api/commit';
import { editProfileImg } from '../../../api/img';
import { follow, searchFollowings } from '../../../api/follow';
export default {
  components: {
    Follower,
    Following,
  },
  data: () => ({
    active: false,
    show: false,
    email: '',
    gender: '',
    badge: '',
    age: '',
    imgSrc: '',
    mystory: '',
    //email로 /commit/total에서 가져온 커밋수
    cnt: 0,
    image: '',
    file: null,
    followingKey: 0,
    alreadyFollow: false,
    followers: [],
  }),

  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      userId: ['getSelectedUserId'],
      following: ['getFollowingList'],
    }),

    width() {
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          return '100';
        case 'sm':
          return '100';
        default:
          return '150';
      }
    },
  },
  watch: {
    following: {
      handler(val) {
        this.alreadyFollow = this.checkFollowing(val);
      },
    },
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST', 'UPDATE_USERINFO_BY_NICKNAME']),
    showModal() {
      if (this.user.email == this.email) {
        this.active = true;
      }
    },
    fileSelected(evt) {
      this.file = evt.target.files.item(0);
      const reader = new FileReader();
      reader.addEventListener('load', this.imageLoaded);
      reader.readAsDataURL(this.file);
    },
    imageLoaded(evt) {
      this.image = evt.target.result;
    },
    upload() {
      const form = new FormData();
      form.append('file', this.file);
      form.append('email', this.email);
      editProfileImg(
        form,
        () => {
          this.active = false;
          this.UPDATE_USERINFO_BY_NICKNAME(this.user.nickname);
          this.getUserInfo();
        },
        (error) => {
          console.log('에러' + error);
        }
      );
    },
    clickFollow() {
      follow(
        this.user.email, //나
        this.email, //상대
        () => {
          this.GET_FOLLOWING_LIST(this.user.email);
          this.UPDATE_USERINFO_BY_NICKNAME(this.user.nickname);
        },
        (error) => {
          console.log('follow에러', error);
        }
      );
    },
    checkFollowing(followinglist) {
      const compare = this.userId; //this.email(지금 보고있는 마이페이지의 이메일)
      for (let i = 0; i < followinglist.length; i++) {
        if (followinglist[i].nickname == compare) {
          return true; //이미 팔로우중이면 true
        }
      }
      return false; //팔로우중이 아니면 false
    },
    getUserInfo() {
      getUserInfoByNickname(
        this.userId,
        (response) => {
          const content = response.data;
          this.email = content.user.email;
          this.gender = content.user.gender;
          this.age = content.user.age;
          if (content.user.profile != null) {
            this.imgSrc = content.user.profile.filePath;
          } else {
            this.imgSrc = null;
          }
          this.badge = content.user.badge;
          this.mystory = content.user.mystory;
          this.alreadyFollow = this.checkFollowing(this.following); //내가 팔로우중인 사람들 리스트 넣어서 체크
          searchFollowings(
            this.email,
            (response) => {
              const res = response.data;
              for (let i = 0; i < res.length; i++) {
                const item = res[i];
                this.followers.push(item);
              }
            },
            (error) => {
              console.log('follower에러' + error);
            }
          );
          userCommitCount(
            this.email,
            (response) => {
              this.cnt = response.data;
            },
            (error) => {
              console.log('cnt에러' + error);
            }
          );
        },
        (error) => {
          console.log('profileinfo-img에러' + error);
        }
      );
    },
  },
  activated() {
    this.getUserInfo();
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
  cursor: pointer;
}
.profileImg:hover {
  opacity: 0.5;
  cursor: pointer;
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
