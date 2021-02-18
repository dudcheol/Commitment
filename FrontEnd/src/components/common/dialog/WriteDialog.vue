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
      :loading="uploading"
    >
      <template #header>
        <div class="d-flex flex-column">
          <h2 class="text-center">빈 커밋 채우기</h2>
          <v-chip small outlined>
            <i class="bx bxs-map" style="vertical-align:middle"></i>{{ address }}
          </v-chip>
        </div>
      </template>
      <div class="con-content dialog" style="max-height:40vh">
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
          <div class="flex-column pl-2 my-auto">
            <h2>{{ user.nickname }}</h2>
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

      <template #footer>
        <div class="file-preview-container d-flex overflow-auto">
          <div
            v-for="(file, index) in files"
            :key="'write-dialog' + index"
            class="file-preview-wrapper"
          >
            <div class="file-close-button d-flex justify-end">
              <v-icon color="error" @click="fileDeleteButton" :name="file.number"
                >mdi-close-circle</v-icon
              >
            </div>
            <img
              :src="file.preview"
              style="
                    width: 100x;
                    height: 100px;
                    border-radius: 5px;
                  "
            />
          </div>
        </div>
        <div class="d-flex align-center">
          <h3>게시물에 추가</h3>
          <div class="d-flex flex-row ml-auto">
            <vs-button id="reg1" size="l" circle icon color="success" flat active>
              <div class="image-box d-flex align-center">
                <label for="file">
                  <input type="file" id="file" ref="files" @change="selectPhoto" multiple />
                  <i class="bx bxs-photo-album"> </i>
                </label>
              </div>
            </vs-button>
            <!-- <vs-button size="l" circle icon color="warning" flat active>
              <i class="bx bxs-smile"></i>
            </vs-button> -->
            <vs-button size="l" circle icon color="warning" flat active @click="tagArea = !tagArea">
              <i class="bx bxs-purchase-tag"></i>
            </vs-button>
          </div>
        </div>
        <v-combobox
          v-show="tagArea"
          v-model="tags"
          chips
          clearable
          label="이곳에 태그를 입력하고 엔터를 누르세요"
          multiple
          solo
          flat
          small-chips
          class="ma-0 pa-0"
          dense
          style="max-height:40px; overflow:auto;"
        >
          <template v-slot:selection="{ attrs, item, select, selected }">
            <v-chip
              v-bind="attrs"
              :input-value="selected"
              close
              text-color="blue-grey darken-2"
              color="blue-grey lighten-5"
              @click="select"
              @click:close="remove(item)"
              :ripple="false"
              class="ma-1"
            >
              <span class="font-weight-bold blue-grey--text">{{ item }}</span>
            </v-chip>
          </template>
        </v-combobox>
        <vs-button :disabled="!board.content.trim()" block flat class="mx-0" @click="write()"
          ><h2>Commit</h2></vs-button
        >
      </template>
    </vs-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { writeBoard, imageUpload } from '../../../api/board';

export default {
  props: ['mobile', 'web'],
  data() {
    return {
      board: {
        email: '',
        title: 'title',
        commitId: '', // 넘겨받기
        location: '', // 넘겨받기
        content: '',
        value: '1',
      },
      images: {
        file: [],
      },
      ////
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
      start: 0,
      tagArea: false,
      tags: [],
      uploading: false,
    };
  },
  watch: {
    tags(val) {
      if (val.length > 5) {
        this.$nextTick(() => this.tags.pop());
      }
    },
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      commitId: ['getCommitId'],
      address: ['getCommitAddress'],
      userId: ['getSelectedUserId'],
    }),
  },
  methods: {
    write() {
      this.uploading = true;
      const tmp = [];
      for (let i = 0; i < this.tags.length; i++) {
        tmp.push({ content: this.tags[i] });
      }
      this.board.tag = tmp;
      this.board.commitId = this.commitId;
      writeBoard(
        this.board,
        (response) => {
          this.images.file = this.files;
          var frm = new FormData();
          for (var i = 0; i < this.images.file.length; i++) {
            frm.append('file', this.images.file[i].file);
          }

          imageUpload(
            frm,
            response.data,
            () => {
              this.$store.commit('BOARD_REFRESH');
              this.close();
              this.uploading = false;
            },
            (error) => {
              console.log(error);
              this.uploading = false;
            }
          );
        },
        (error) => {
          console.log(error);
          this.uploading = false;
        }
      );
    },
    selectPhoto() {
      //하나의 배열로 넣기
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
            number: this.start,
          },
        ];
        this.start++;
      }
      this.uploadImageIndex = this.start; //이미지 index의 마지막 값 + 1 저장
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter((data) => data.number !== Number(name));
    },
    close() {
      this.$emit('close');
      this.init();
    },
    remove(item) {
      this.tags.splice(this.tags.indexOf(item), 1);
      this.tags = [...this.tags];
    },
    init() {
      this.board = {
        email: '',
        title: 'title',
        commitId: '', // 넘겨받기
        location: '', // 넘겨받기
        content: '',
        value: '1',
      };
      this.board.email = this.user.email;
      this.board.location = this.user.region_name;
      this.images = {
        file: [],
      };
      this.files = [];
      this.filesPreview = [];
      this.uploadImageIndex = 0;
      this.start = 0;
      this.tagArea = false;
      this.tags = [];
      this.uploading = false;
    },
  },
  created() {
    this.init();
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
  padding: 20px;
  overflow: hidden;
  border: 0;
  cursor: pointer;
}

.image-box label {
  display: inline-block;
  padding: 0px 0px;
  color: #fff;
  vertical-align: middle;
  font-size: 0px;
  cursor: pointer;
  /* border-radius: 5px; */
}

.file-preview-wrapper {
  padding: 10px;
  position: center;
}

.file-preview-content-container {
  height: 100%;
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
