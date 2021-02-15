<template>
  <div class="wrapper">
  <h1>사진첩</h1>

  <v-row>
    <v-col
      v-for="item in boardList"
      :key="item"
      class="d-flex child-flex"
      cols="3"
    >
      <div class="col" ontouchstart="this.classList.toggle('hover');">
        <div class="container" @click=moveToDetail(item.id)>
          <div class="front" :style="{ backgroundImage: 'url(' + item.image[0].filePath + ')' }">
          </div>
          <div class="back">
            <div class="inner">
               <p>{{item.commit.createdAt}}</p>
               <p>{{item.commit.address}}</p>
            </div>
          </div>
        </div>
      </div>
    </v-col>
  </v-row>
  </div>
</template>


<script scoped>
import {searchUserByNickname} from '../../../api/account'
import {timelineInfo} from '../../../api/timeline'
export default {
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
                timelineInfo(
                  this.email,
                  (response)=>{
                      const res = response.data;
                      for(let i=0;i<res.length;i++){
                        const item = res[i];
                        if(item.image[0]==null){
                          continue;
                        }
                        this.boardList.push(item);
                      }
                  },
                  (error)=>{
                      console.log("timePC에러"+error);
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
*{
margin: 0;
padding: 0;
-webkit-box-sizing: border-box;
      box-sizing: border-box;
}

h1{
font-size: 2.5rem;
font-family: 'Montserrat';
font-weight: normal;
color: #444;
text-align: center;
margin: 2rem 0;
}

.wrapper{
width: 90%;
margin: 0 auto;
max-width: 80rem;
}

.cols{
display: -webkit-box;
display: -ms-flexbox;
display: flex;
-ms-flex-wrap: wrap;
  flex-wrap: wrap;
-webkit-box-pack: center;
  -ms-flex-pack: center;
      justify-content: center;
}

.col{
width: calc(25% - 2rem);
margin: 1rem;
cursor: pointer;
}

.container{
-webkit-transform-style: preserve-3d;
      transform-style: preserve-3d;
-webkit-perspective: 1000px;
      perspective: 1000px;
}

.front,
.back{
background-size: cover;
box-shadow: 0 4px 8px 0 rgba(0,0,0,0.25);
border-radius: 10px;
background-position: center;
-webkit-transition: -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
-o-transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1), -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
-webkit-backface-visibility: hidden;
      backface-visibility: hidden;
text-align: center;
min-height: 280px;
height: auto;
border-radius: 10px;
color: #fff;
font-size: 1.5rem;
}

.back{
background: #cedce7;
background: -webkit-linear-gradient(45deg,  #cedce7 0%,#596a72 100%);
background: -o-linear-gradient(45deg,  #cedce7 0%,#596a72 100%);
background: linear-gradient(45deg,  #cedce7 0%,#596a72 100%);
}

.front:after{
position: absolute;
top: 0;
left: 0;
z-index: 1;
width: 100%;
height: 100%;
content: '';
display: block;
opacity: .6;
/* background-color: #000; */
-webkit-backface-visibility: hidden;
        backface-visibility: hidden;
border-radius: 10px;
}
.container:hover .front,
.container:hover .back{
-webkit-transition: -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
-o-transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
transition: transform .7s cubic-bezier(0.4, 0.2, 0.2, 1), -webkit-transform .7s cubic-bezier(0.4, 0.2, 0.2, 1);
}

.back{
position: absolute;
top: 0;
left: 0;
width: 100%;
}

.inner{
-webkit-transform: translateY(-50%) translateZ(60px) scale(0.94);
        transform: translateY(-50%) translateZ(60px) scale(0.94);
top: 50%;
position: absolute;
left: 0;
width: 100%;
padding: 2rem;
-webkit-box-sizing: border-box;
        box-sizing: border-box;
outline: 1px solid transparent;
-webkit-perspective: inherit;
        perspective: inherit;
z-index: 2;
}

.container .back{
-webkit-transform: rotateY(180deg);
        transform: rotateY(180deg);
-webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
}

.container .front{
-webkit-transform: rotateY(0deg);
        transform: rotateY(0deg);
-webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
}

.container:hover .back{
-webkit-transform: rotateY(0deg);
      transform: rotateY(0deg);
-webkit-transform-style: preserve-3d;
      transform-style: preserve-3d;
}

.container:hover .front{
-webkit-transform: rotateY(-180deg);
      transform: rotateY(-180deg);
-webkit-transform-style: preserve-3d;
      transform-style: preserve-3d;
}

.front .inner p{
font-size: 2rem;
margin-bottom: 2rem;
position: relative;
}

.front .inner p:after{
content: '';
width: 4rem;
height: 2px;
position: absolute;
background: #C6D4DF;
display: block;
left: 0;
right: 0;
margin: 0 auto;
bottom: -.75rem;
}

.front .inner span{
color: rgba(255,255,255,0.7);
font-family: 'Montserrat';
font-weight: 300;
}

@media screen and (max-width: 64rem){
.col{
width: calc(33.333333% - 2rem);
}
}

@media screen and (max-width: 48rem){
.col{
width: calc(50% - 2rem);
}
}

@media screen and (max-width: 32rem){
.col{
width: 100%;
margin: 0 0 2rem 0;
}
}
</style>
