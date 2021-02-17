<template>
  <v-container>
    <!-- top -->
    <v-row no-gutters>
      <v-col class="d-flex justify-center align-center">
        <div class="pl-8">
          <div class="profileImg " v-if="imgSrc != null">
            <v-list-item-avatar :size="width">
              <img :src="imgSrc" alt="picture" @click="showModal()" />
            </v-list-item-avatar>
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
        </div>

        <div class="d-flex flex-column align-center justify-center">
          <div class="d-flex align-end">
            <div>
              <h4>
                {{ `${this.age ? this.age + ' / ' : ''} ${this.gender == 'm' ? '남성' : '여성'}` }}
              </h4>
              <div class="text-h2 font-weight-black">{{ this.userId }}</div>
            </div>
            <div class="pl-10">
              <div class="badge" v-if="badge != null">
                <v-list-item-avatar size="70">
                  <img :src="require(`@/assets/img/badge/${badge}.png`)" alt="" />
                </v-list-item-avatar>
              </div>
              <div class="badge" v-else>
                <v-list-item-avatar size="70">
                  <img :src="require(`@/assets/img/badge/badge0.png`)" alt="" />
                </v-list-item-avatar>
              </div>
            </div>
          </div>

          <div class="d-flex">
            <vs-button size="l" circle icon color="success" flat>
              <i class="bx bxs-check-square"></i>{{ this.cnt }}
            </vs-button>
            <Follower @close="followingKey++" />
            <Following :key="followingKey" @close="followingKey++" />
            <div v-if="userId != user.nickname">
              <v-btn
                text
                rounded
                color="primary"
                flat
                @click="clickFollow"
                class="font-weight-black"
              >
                {{ alreadyFollow ? '팔로우 취소' : '팔로우' }}
              </v-btn>
            </div>

            <div class="badge" v-if="this.user.email == this.email">
              <ProfileEdit />
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
              <div id="mobileHidden">
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
import ProfileEdit from '../../common/dialog/ProfileEdit';
import { searchUserByNickname } from '../../../api/account';
import { userCommitCount } from '../../../api/commit';
import { editProfileImg } from '../../../api/img';
import { follow, searchFollowings } from '../../../api/follow';
export default {
  components: {
    Follower,
    Following,
    ProfileEdit,
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

    watch: {
      following(val) {
        this.alreadyFollow = this.checkFollowing(val);
      },
    },
  },
  methods: {
    ...mapActions(['GET_FOLLOWING_LIST']),
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
      // console.log(this.file+"이랑"+this.email);
      const form = new FormData();
      form.append('file', this.file);
      form.append('email', this.email);
      editProfileImg(
        form,
        (response) => {
          console.log('성공' + response);
          this.active = false;
        },
        (error) => {
          console.log('에러' + error);
        }
      );
    },
    clickFollow() {
      // console.log("this.user.email",this.user.email);
      // console.log("this.userId",this.email);
      follow(
        this.user.email, //나
        this.email, //상대
        () => {
          // this.GET_FOLLOWING_LIST(this.user.email);//여기
          // console.log(this.user.email, '가', this.userId, '팔로우 완료');
        },
        (error) => {
          console.log('follow에러', error);
        }
      );
    },
    checkFollowing(followinglist) {
      const compare = this.email; //this.email(지금 보고있는 마이페이지의 이메일)
      console.log('얘', this.email);
      for (let i = 0; i < followinglist.length; i++) {
        console.log(followinglist[i].email, '?');
        if (followinglist[i].email == compare) {
          // console.log("이미 팔로우중");
          return true; //이미 팔로우중이면 true
        }
      }
      // console.log("팔로우안한상태");
      return false; //팔로우중이 아니면 false
    },
  },
  activated() {
    searchUserByNickname(
      { keyword: this.userId },
      (response) => {
        const content = response.data;
        this.email = content.content[0].email;
        this.gender = content.content[0].gender;
        this.age = content.content[0].age;
        if (content.content[0].profile != null) {
          this.imgSrc = content.content[0].profile.filePath;
        } else {
          this.imgSrc = null;
        }
        this.badge = content.content[0].badge;
        this.mystory = content.content[0].mystory;
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
