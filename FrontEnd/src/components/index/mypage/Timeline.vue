<template>
  <v-tooltip bottom color="transparent" transition="slide-y-transition">
    <template v-slot:activator="{ on, attrs }">
      <div :class="boardList.length ? 'external' : 'mt-12'">
        <div v-if="loading" style="height:55vh" class="d-flex justify-center align-center">
          <v-progress-circular
            :size="70"
            :width="7"
            color="blue-grey lighten-4"
            indeterminate
          ></v-progress-circular>
        </div>

        <div class="horizontal-scroll-wrapper" v-if="boardList.length" v-bind="attrs" v-on="on">
          <div v-for="(item, index) in boardList" :key="index">
            <div v-if="index % 2 == 0">
              <div class="img-wrapper slower ">
                <a target="_blank" rel="noopener">
                  <img
                    :src="item.image[0].filePath"
                    alt=""
                    class="pola"
                    @click="moveToDetail(item.id)"
                  />
                  <p class="dateFontStyle">{{ item.commit.createdAt }}</p>
                  <v-spacer></v-spacer>
                  <p class="areaFontStyle">{{ item.commit.address }}</p>
                </a>
              </div>
            </div>
            <div v-else-if="index % 3 == 0">
              <div class="img-wrapper faster slower-down">
                <a target="_blank" rel="noopener">
                  <img
                    :src="item.image[0].filePath"
                    alt=""
                    class="pola"
                    @click="moveToDetail(item.id)"
                  />
                  <p class="dateFontStyle">{{ item.commit.createdAt }}</p>
                  <v-spacer></v-spacer>
                  <p class="areaFontStyle">{{ item.commit.address }}</p>
                </a>
              </div>
            </div>
            <div v-else-if="index % 5 == 0">
              <div class="img-wrapper faster">
                <a target="_blank" rel="noopener">
                  <img
                    :src="item.image[0].filePath"
                    alt=""
                    class="pola"
                    @click="moveToDetail(item.id)"
                  />
                  <p class="dateFontStyle">{{ item.commit.createdAt }}</p>
                  <v-spacer></v-spacer>
                  <p class="areaFontStyle">{{ item.commit.address }}</p>
                </a>
              </div>
            </div>
            <div v-else>
              <div class="img-wrapper slower-down">
                <a target="_blank" rel="noopener">
                  <img
                    :src="item.image[0].filePath"
                    alt=""
                    class="pola"
                    @click="moveToDetail(item.id)"
                  />
                  <p class="dateFontStyle">{{ item.commit.createdAt }}</p>
                  <v-spacer></v-spacer>
                  <p class="areaFontStyle">{{ item.commit.address }}</p>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <span class="blue-grey--text">오른쪽으로 스크롤하면 더 많은 사진을 볼 수 있어요</span>
  </v-tooltip>
</template>

<script>
import { getUserInfoByNickname } from '../../../api/account';
import { timelineInfo } from '../../../api/timeline';
import { mapGetters } from 'vuex';
// import NoDataCard from '../../common/card/NoDataCard.vue';
export default {
  // components: { NoDataCard },
  props: ['nickname', 'intro', 'img'],
  data() {
    return {
      boardList: [],
      //이 아래로는 id를 가지고 searchUserByNickname해서 가져온것
      email: '',
      loading: false,
    };
  },
  activated() {
    this.boardList = [];
    this.loading = true;
    getUserInfoByNickname(
      this.userId,
      (response) => {
        const content = response.data;
        this.email = content.user.email;
        timelineInfo(
          this.email,
          (response) => {
            this.loading = false;
            const res = response.data;
            for (let i = 0; i < res.length; i++) {
              const item = res[i];
              if (item.image[0] == null) {
                continue;
              }
              this.boardList.push(item);
            }
          },
          (error) => {
            this.loading = false;
            console.log('timeline에러' + error);
          }
        );
      },
      (error) => {
        this.loading = false;
        console.log('ct에러' + error);
      }
    );
  },
  methods: {
    moveToDetail(data) {
      // this.$router.push({ name: 'Detail', params: { id: data } });
      this.$store.commit('SELECTED_BOARD_ID', data);
      this.$router.push({ name: 'Detail' });
    },
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      userId: ['getSelectedUserId'],
    }),
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.pola {
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
  height: 60vh;
}

.horizontal-scroll-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vh;
  transform: rotate(-90deg) translate3d(0, -100vh, 0);
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
  transform-origin: 100% 50%;
  transform: rotate(90deg) translateZ(0.1px) scale(0.9) translateX(0px) translateY(-3vh);
  transition: 1s;
}

.img-wrapper:hover {
  min-height: 65vh;
}

.slower {
  transform: rotate(90deg) translateZ(-0.2px) scale(1.1) translateX(50%) translateY(-15vh);
}
.slower1 {
  transform: rotate(90deg) translateZ(-0.25px) scale(1.05) translateX(0%) translateY(3vh);
}
.slower2 {
  transform: rotate(90deg) translateZ(-0.3px) scale(1.3) translateX(0%) translateY(-3vh);
}
.slower-down {
  transform: rotate(90deg) translateZ(-0.2px) scale(1.1) translateX(0%) translateY(0vh);
}
.faster {
  transform: rotate(90deg) translateZ(0.15px) scale(0.8) translateX(0%) translateY(9vh);
}
.faster1 {
  transform: rotate(90deg) translateZ(0.05px) scale(0.8) translateX(0%) translateY(5vh);
}
.fastest {
  transform: rotate(90deg) translateZ(0.22px) scale(0.7) translateX(-10vh) translateY(-20vh);
}
.vertical {
  transform: rotate(90deg) translateZ(-0.15px) scale(1.15) translateX(0%) translateY(0%);
}
.last {
  transform: rotate(90deg) translateZ(-0.2px) scale(1.1) translateX(25vh) translateY(-13vh);
}
.scroll-info,
header {
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
  padding: 1vh;
  background: #ffffff;
  box-shadow: 0 10px 50px #53515082;
  border-radius: 10px;
}
img {
  max-width: 35vh;
  max-height: 35vh;
  transition: 0.5s;
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

.dateFontStyle {
  font-family: 'LAB디지털';
  font-size: 20px;
}
.areaFontStyle {
  font-family: 'BBTreeCL';
  font-size: 30px;
  color: gray;
}
@font-face {
  font-family: 'LAB디지털';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/LAB디지털.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'Cafe24Shiningstar';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Cafe24Shiningstar.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'BBTreeCL';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeCL.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
</style>
