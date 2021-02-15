<template>
  <v-container class="pt-7">
    <v-row>
      <v-col class="sidebar_left" cols="12" sm="2">
        <div class="d-flex justify-center">
          <SelectZone @area="onSelectArea"/>
        </div>
      </v-col>
      <v-col class="mainslot" cols="12" sm="8">
        <v-sheet min-height="70vh" rounded="xl">
          <div class="mainpage pa-5">
            <div class="frame">
              <!-- Top3프로필 -->
              <div class="box">
                <div class="three">
                  <div class="profiles justify-center">
                    <div class="second">
                      <v-avatar size="80">
                        <img :src="imgSrc2" alt="John" />
                      </v-avatar>
                      <h2 class="percentage">{{cnt2}}</h2>
                      <p class="nickname">{{nickname2}}</p>
                      <vs-button
                        border
                        :active="active == 2"
                        @click="active = 2"
                        class="like"
                      >
                        <i class="bx bxs-heart"></i> Follow
                      </vs-button>
                    </div>
                    <div class="first">
                      <v-avatar size="100">
                        <img :src="imgSrc1" alt="John" />
                      </v-avatar>
                      <h2 class="percentage">{{cnt1}}</h2>
                      <p class="nickname">{{nickname1}}</p>
                      <vs-button
                        border
                        :active="active == 2"
                        @click="active = 2"
                        class="likeMiddle"
                      >
                        <i class="bx bxs-heart"></i> Follow
                      </vs-button>
                    </div>
                    <div class="third">
                      <v-avatar size="80">
                        <img :src="imgSrc3" alt="John" />
                      </v-avatar>
                      <h2 class="percentage">{{cnt3}}</h2>
                      <p class="nickname">{{nickname3}}</p>
                      <vs-button
                        border
                        :active="active == 2"
                        @click="active = 2"
                        class="like"
                      >
                        <i class="bx bxs-heart"></i> Follow
                      </vs-button>
                    </div>
                  </div>
                </div>
              </div>

            </div>
            <div class="whitebox">
              <SearchBar @keyword="onInputChange"/>
              <div class="frame">
                <div class="tables">
                  <vs-table class="w-auto text-center">
                    <template #tbody>
                      <vs-tr
                        :key="i"
                        v-for="(tr, i) in users"
                        :data="tr"
                      >
                        <vs-td class="rankNum">
                          {{ tr.ranking }}
                
                        </vs-td>
                        <vs-td>
                          <v-avatar
                          size="50"  
                          >
                            <img v-if="tr.profile!=null"
                              :src="tr.profile.filePath"
                              alt=""
                            >
                          </v-avatar>
                        </vs-td>
                        <vs-td>
                        {{ tr.nickname }}
                        </vs-td>
                        <vs-td class="percentageSmall">
                        {{ tr.cnt }}
                        </vs-td>
                        <vs-td class="temp">
                          <vs-button
                            icon
                            border
                            :active="active == 2"
                            @click="active = 2"
                             class="temp2"
                          >
                            <i class='bx bxs-heart' ></i>
                          </vs-button>
                        </vs-td>
                      </vs-tr>
                    </template>
                  </vs-table>

                </div>
              </div>
            </div>
            <br />
          </div>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script scoped>
import SelectZone from '../../components/index/rank/SelectZone';
import SearchBar from '../../components/index/rank/SearchBar';
import {areaList, totalList, userFindList} from '../../api/rank';

export default {
  name: 'Rank',

  components: {
    SelectZone,
    SearchBar,
  },

  data: () => ({
    items: ['mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account'],
    keyword:"",
    area:"total",
    users:[],
    nickname1:"",
    imgSrc1:'',
    imgSrc2:'',
    imgSrc3:'',
  }),
  methods:{
    onInputChange (inputValue){
      this.users=[];
      this.keyword = inputValue;
      userFindList(
        {keyword:inputValue},
        (response)=>{
          const content = response.data.content;
          for(let i=0;i<content.length;i++){
            const item = content[i];
            console.log("total",item.profile.filePath);
            console.log("total",item.ranking);
            this.users.push(item);
          }
        },
        (error)=>{
          console.log("에러"+error);
        }
      )
    },
    onSelectArea(areaValue){
      this.users=[];
      this.area = areaValue;
      areaList(
        areaValue,
        (response)=>{
          const res = response.data;
          const temp1 = res[0];
          this.nickname1=temp1.nickname;
          this.imgSrc1 = temp1.profile.filePath;
          this.cnt1 = temp1.cnt;

          const temp2 = res[1];
          this.nickname2=temp2.nickname;
          this.imgSrc2 = temp2.profile.filePath;
          this.cnt2 = temp2.cnt;

          const temp3 = res[2];
          this.nickname3=temp3.nickname;
          this.imgSrc3 = temp3.profile.filePath;
          this.cnt3 = temp3.cnt;

          for(let i=3;i<res.length;i++){
            const item = res[i];
            // console.log(item);
            this.users.push(item);
          }
        },
        (error)=>{
          console.log("에러"+error);
        }
      )
    },
  },
  created(){
    totalList(
      (response)=>{
        const res = response.data;
        
        const temp1 = res[0];
        this.nickname1=temp1.nickname;
        this.imgSrc1 = temp1.profile.filePath;
        this.cnt1 = temp1.cnt;

        const temp2 = res[1];
        this.nickname2=temp2.nickname;
        this.imgSrc2 = temp2.profile.filePath;
        this.cnt2 = temp2.cnt;

        const temp3 = res[2];
        this.nickname3=temp3.nickname;
        this.imgSrc3 = temp3.profile.filePath;
        this.cnt3 = temp3.cnt;

        for(let i=3;i<res.length;i++){
          const item = res[i];
          this.users.push(item);

        }

      },
      (error)=>{
        console.log("에러"+error);
      }
    )

  },
  props:{
    Top3:{
      type:String,
      default:"total",

    }
  }
};
</script>

<style scoped>
.temp{
  /* background-color:blue; */
}
.temp2{
  margin-right: -20px;
}
.container {
  max-width: 1200px;
}
.navbar_right {
  display: flex;
  width: min-content;
}
.search {
  display: flex;
  width: min-content;
}
.v-toolbar__content {
  display: flex;
  justify-content: space-between;
}
.whitebox {
  background-color: white;
}
.like{
  margin-top:-10%;
  margin-left:15%;
}
.likeMiddle{
    margin-top:-10%;
    margin-left:20%;
}
.box{
  width: 100%;
  height: 100%;
}
.three {
  display: grid;
}
.profiles {
  display: flex;
  text-align: center;
  height: 50%;
}
.second {
  margin-top: 8%;
  z-index: 1;
}
.first {
  margin-left: 5%;
  margin-right: 5%;
  /* z-index: 1; */
}
.third {
  margin-top: 8%;
  z-index: 1;
}
.nickname {
  color: rgba(0, 0, 0, 0.514);
  margin-top: 2%;
}
.percentage{
  color: rgba(0, 0, 0, 0.589);
}
.tables{
  /* background-color: red; */
  
}
.profiles{
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
.rankNum{
  color:#2196F3;
  /* font-weight: bold; */
}
.percentageSmall{
  color:#1976D2;
  font-size:large;
  /* font-weight: bold; */
}
@media (max-width: 450px) {
  .search {
    display: none;
  }

  .navbar_right {
    display: none;
  }
}
</style>
