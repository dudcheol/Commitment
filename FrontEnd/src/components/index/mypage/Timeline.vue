<template>
  <div class="external">
    <div class="horizontal-scroll-wrapper">
      <div v-for="(item,index) in boardList" :key="index">
        <div v-if="index%2==0">
          <div class="img-wrapper slower ">
            <a target="_blank" rel="noopener">
            <img :src="item.image[0].filePath" alt="" class="pola" @click=moveToDetail(item.id)>
            <p>{{item.commit.createdAt}}<v-spacer></v-spacer> {{item.commit.address}} {{index}}</p>
            </a>
          </div>
        </div>
        <div v-else-if="index%3==0">
          <div class="img-wrapper faster slower-down">
            <a target="_blank" rel="noopener">
            <img :src="item.image[0].filePath" alt="" class="pola" @click=moveToDetail(item.id)>
            <p>{{item.commit.createdAt}}<v-spacer></v-spacer> {{item.commit.address}} {{index}}</p>
            </a>
          </div>
        </div>
        <div v-else-if="index%5==0">
          <div class="img-wrapper faster">
            <a target="_blank" rel="noopener">  
            <img :src="item.image[0].filePath" alt="" class="pola" @click=moveToDetail(item.id)>
            <p>{{item.commit.createdAt}}<v-spacer></v-spacer> {{item.commit.address}} {{index}}</p>
            </a>
          </div>
        </div>
        <div v-else>
          <div class="img-wrapper slower-down">
            <a target="_blank" rel="noopener">
            <img :src="item.image[0].filePath" alt="" class="pola" @click=moveToDetail(item.id)>
            <p>{{item.commit.createdAt}}<v-spacer></v-spacer> {{item.commit.address}} {{index}}</p>
            </a>
          </div>
        </div>

      </div>
    </div>
  </div>

</template>

<script>
import {searchUserByNickname} from '../../../api/account'
import {timelineInfo} from '../../../api/timeline'
export default {
  props: ['nickname', 'intro', 'img'],
  data(){
    return{
      boardList:[],
      id:'dudcheol',//this.$route.params.id로 받은 현재 유저의 닉네임
      //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
      email:'',
    }
  },
    created(){
        searchUserByNickname(
            {keyword : this.id},
            (response)=>{
                const content = response.data.content[0];
                this.email = content.email;
                console.log("email",this.email);
                timelineInfo(
                  this.email,
                  (response)=>{
                      const res = response.data;
                      // console.log("timeline res",res);
                      for(let i=0;i<res.length;i++){
                        const item = res[i];
                        if(item.image[0]==null){
                          continue;
                        }
                        // console.log(item.image[0].filePath);
                        this.boardList.push(item);
                      }
                  },
                  (error)=>{
                      console.log("timeline에러"+error);
                  }
                )
            },
            (error)=>{
                console.log("ct에러"+error);
                
            }
        )
    },
    methods:{
      moveToDetail(data){
        console.log("이동할 게시글 id ",data);
        this.$router.push({ name: 'Detail', params: { id: this.data.id } });
      }
    }
};
</script>

<style scoped>

* {
  box-sizing: border-box;
}
.pola{
  margin-bottom: 7%;
}
/* hide scrollbar */
::-webkit-scrollbar {
  width: 1px;
  height: 1px;
}

::-webkit-scrollbar-button {
  width: 1px;
  height: 1px;
}
.external {
  overflow: hidden;
  height: 100vh;
}

.horizontal-scroll-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vh;
  transform: rotate(-90deg) translate3d(0,-100vh,0);
  transform-origin: right top;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 0;
  height: 100vw;
  perspective: 1px;
  transform-style: preserve-3d;
  padding-bottom: 10rem;
}
.img-wrapper {
  transform: rotate(90deg);
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 40vh;
  transform-origin: 50% 50%;
  transform: rotate(90deg) translateZ(.1px) scale(0.9) translateX(0px) translateY(-3vh);
  transition: 1s;
}

.img-wrapper:hover {
 min-height: 65vh;
}

.slower {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(0%) translateY(-10vh);
}
.slower1 {
  transform: rotate(90deg) translateZ(-.25px) scale(1.05) translateX(0%) translateY(8vh);
}
.slower2 {
  transform: rotate(90deg) translateZ(-.3px) scale(1.3) translateX(0%) translateY(2vh);
}
.slower-down {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(0%) translateY(16vh) ;
}
.faster {
  transform: rotate(90deg) translateZ(.15px) scale(0.8) translateX(0%) translateY(14vh) ;
}
.faster1 {
  transform: rotate(90deg) translateZ(.05px) scale(0.8) translateX(0%) translateY(10vh) ;
}
.fastest {
  transform: rotate(90deg) translateZ(.22px) scale(0.7) translateX(-10vh) translateY(-15vh) ;
}
.vertical {
   transform: rotate(90deg) translateZ(-.15px) scale(1.15) translateX(0%) translateY(0%) ;
}
.last {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(25vh) translateY(-8vh) ;
}
.scroll-info, header {
  position: absolute;
  left: 1rem;
}


header {
  bottom: 1rem;
}
.scroll-info {
  top: 1rem;
}
a {
  color: inherit;
  font-weight: 500;
  
}
h1 {
  font-weight: 300;
  font-size: 1rem;
}

.img-wrapper a {
  overflow: hidden;
  display: block;
  padding: 1vh ;
  background: #ffffff;
  box-shadow: 0 10px 50px #53515082;
}
img {
  max-width: 45vh;
  max-height: 50vh;
  transition: .5s;
  vertical-align: top;
  /* filter: saturate(40%) sepia(30%) hue-rotate(5deg); */
}
a:hover img {
  filter: none;
}
p {
  margin: 0;
}
.scroll-info {
  display: flex;
  align-items: center;
}
.icon svg {
    width: 50px;
    fill: currentcolor;
}
</style>
