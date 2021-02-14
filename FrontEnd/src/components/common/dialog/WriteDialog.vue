<template>
  <div class="center">
    <vs-dialog
      scroll
      blur
      overflow-hidden
      auto-width
      prevent-close
      v-model="web"
      v-on:close="close"
    >
      <template #header>
        <div class="d-flex flex-column">
          <h2 class="text-center">커밋하기</h2>
          <v-chip small outlined>
            <i class="bx bxs-map" style="vertical-align:middle"></i
            >{{ address ? address : '위치를 찾는 중...' }}
          </v-chip>
        </div>
      </template>
      <div class="con-content dialog">
        <div class="d-flex flex-row">
          <div class="d-flex align-center">
            <v-avatar v-if="user.profile" circle size="44">
              <img :src="user.profile.filePath" />
            </v-avatar>
            <v-avatar
              v-else
              circle
              size="44"
              color="blue-grey"
              class="font-weight-medium display-2"
            >
              <v-icon color="white">mdi-emoticon-happy</v-icon>
            </v-avatar>
          </div>
          <div class="flex-column pl-2">
            <h3>{{ user.nickname }}</h3>
            <div>
              <vs-select
                placeholder="공개설정"
                v-model="board.value"
                style="width:85px"
                size="small"
              >
                <vs-option label="공개" value="1">
                  공개
                </vs-option>
                <vs-option label="비공개" value="2">
                  비공개
                </vs-option>
              </vs-select>
            </div>
          </div>
        </div>
        <v-textarea
          solo
          flat
          name="input-7-4"
          :label="user.nickname + '님, 여기는 어떤 곳인가요?'"
          v-model="board.content"
          auto-grow
        ></v-textarea>
      </div>
      <div class="image-box">
        <label for="file">
          <b-row
            class="file-preview-container"
            style="overflow: auto; max-height: 120px"
          >
            <div
              v-for="(file, index) in files"
              :key="index"
              class="file-preview-wrapper"
            >
              <div
                class="file-close-button"
                @click="fileDeleteButton"
                :name="file.number"
              ></div>
              <img
                :src="file.preview"
                style="
                    width: 100px;
                    height: 100px;
                    border-radius: 15px;
                    margin-left: 20px;
                  "
              />
            </div>
          </b-row>
        </label>
      </div>
      <template #footer>
        <div class="d-flex align-center">
          <h3>게시물에 추가</h3>

          <div class="d-flex flex-row ml-auto">
            <div class="image-box">
              <label for="file">
                <vs-button
                  size="l"
                  circle
                  icon
                  color="#00c853"
                  flat
                  :active="active == 5"
                  @click="active = 5"
                >
                  <input
                    type="file"
                    id="file"
                    ref="files"
                    @change="selectPhoto"
                    multiple
                  />
                  <i class="bx bxs-photo-album"> </i>
                </vs-button>
              </label>
            </div>
            <vs-button
              size="l"
              circle
              icon
              color="#ffd600"
              flat
              :active="active == 5"
              @click="active = 5"
            >
              <i class="bx bxs-smile"></i>
            </vs-button>
            <vs-button
              size="l"
              circle
              icon
              color="#304ffe"
              flat
              :active="active == 5"
              @click="active = 5"
            >
              <i class="bx bxs-purchase-tag"></i>
            </vs-button>
          </div>
        </div>
        <vs-button block flat class="mx-0" @click="write()"
          ><h2>Commit</h2></vs-button
        >
      </template>
    </vs-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { writeBoard } from '../../../api/board';

export default {
  props: ['mobile', 'web'],
  data() {
    return {
      board: {
        email: 'test@test.com', // 얘 왜 user.email하면 인식 못하지?
        title: 'title',
        commitId: '176', // 넘겨받기
        location: 'national', // 넘겨받기
        content: '',
        value: '1',
      },
      snsId: '48',
      uploaded: false,
      images: {
        file: [],
      },
      ////
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'], address: ['getCurrentAddress'] }),
  },
  methods: {
    write() {
      writeBoard(
        this.board,
        (response) => {
          console.log(response);
          this.snsId = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    selectPhoto() {
      console.log(this.$refs.files.files);

      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
      this.uploaded = true;
    },
    imageAddUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기c
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log(this.files);
      // console.log(this.filesPreview);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
      // console.log(this.files);
    },

    close() {
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
.dialog {
  width: calc(100vw - 56px);
  max-width: 700px;
}

.image-box {
  margin-top: 0px;
  padding-bottom: 0px;
}

.image-box input[type='file'] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
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
  width: 400px;
  height: 400px;
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
