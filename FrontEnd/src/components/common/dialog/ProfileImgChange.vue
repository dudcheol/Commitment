<template>
    <div class="center">
      <div class="profileImg">
        <v-list-item-avatar size="130">
                    <img src="https://smallstartour.com/wp-content/uploads/2020/05/SA_page3_top1080x700-compressor.jpg" 
                    alt="picture"
                    @click="active=!active"
                    >
        </v-list-item-avatar>
      </div>
      <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="400px">
        <template #header>
          <h3>
            프로필 사진 변경
          </h3>
        </template>
        <div class="con-content">
          <div>
            <input type="file" @change="fileSelected"/>
            <img v-if="image" :src="image" width="300"/>
          </div>
          <div>
          </div>
          <div class="footer-dialog">
            <vs-button block @click="upload">
              업로드
            </vs-button>
          </div>
        </div>
      </vs-dialog>
    </div>
</template>
<script scoped>
import { mapGetters } from 'vuex';
import {searchUserByEmail} from '../../../api/account'
import {editProfileImg} from '../../../api/img'
  export default {
    components: {  },
    data: () => ({
      active: false,
      email:'',
      image: '',
      file: null, 
    }),
    computed:{
      ...mapGetters({
        user:['getUserInfo'],
      })
    },
    created(){
        // console.log(email);
        searchUserByEmail(
            {keyword : this.user.email},
            (response)=>{
                const profile = response.data.content;
                const item = profile[0];
                this.email=item.email;
            },
            (error)=>{
                console.log("img에러"+error);
            }
        )

    },
    methods: {
    fileSelected(evt){
      this.file = evt.target.files.item(0);
      console.log(typeof this.file);
      const reader = new FileReader();
      reader.addEventListener('load', this.imageLoaded);
      reader.readAsDataURL(this.file);
    },
    imageLoaded(evt){ 
      this.image = evt.target.result; 
    },
    upload(){
      const form = new FormData();
      form.append('file', this.file);
      form.append('email', this.email);
      editProfileImg(
          form,
        (response)=>{
          console.log("성공"+response);
        },
        (error)=>{
          console.log("에러"+error);
        }
      )
    },
  }
}
</script>

<style scoped>
.profileImg{
    opacity:1;
    transition:opacity 0.5s;
}
.profileImg:hover {
     opacity:0.5;
}
.image-box {
  margin-top: 0px;
  padding-bottom: 0px;
}
/* .image-box input[type='file'] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
} */
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
/* .file-close-button {
  position: absolute;
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
} */
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
