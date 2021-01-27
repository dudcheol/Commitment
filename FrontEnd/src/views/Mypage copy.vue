<template>
  <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
    <div class="search">
      <!-- <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
      ></v-avatar> -->
      <vs-avatar text="Logo"/>
      <vs-avatar color="primary" text="primary"/>
      <vs-input placeholder="Search">
      </vs-input>
    </div>

      <!-- tab 바뀌는부분 -->
    <div>
      <v-tabs>
        <v-tab
          v-for="item in items"
          :key="item"
        >
        <v-icon>
          {{ item }}
        </v-icon>
        </v-tab>
      </v-tabs>
    </div>
    <!-- 아바타 바뀌는 지역 -->
    <div>
      <!-- 일단 정렬을 위한 div -->
    </div>
    <div class="navbar_right">
      <vs-avatar color="danger" icon="photo_camera" />
      <vs-avatar color="warning" icon="filter_vintage" />
    </div>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-sheet
              min-height="70vh"
              rounded="lg"
            >

<!--              <vs-card type="3">
              <template #title>
                <h3>Pot with a plant</h3>
              </template>
              <template #img>
                <img src="https://1.bp.blogspot.com/-WpzdLW5w-Kc/Xp5FeJVAenI/AAAAAAAAMM4/xAViqRjuTTATHjL1yIP9cWKCU4_25oxawCLcBGAsYHQ/s320/%25EC%25BD%25A9%25EB%258F%258C%25EC%259D%25B4%2B%2526%2B%25EB%25B0%25A4%25EB%258F%258C%25EC%259D%25B4%2B%25E3%2581%25BE%25E3%2582%2581%25E3%2581%258D%25E3%2581%25A1%2526%25E3%2581%25A4%25E3%2581%25B6%25E3%2581%258D%25E3%2581%25A1%2BTimmy%2Band%2BTommy_11.png" alt="">
              </template>
              <template #text>
                <p>
                  Lorem ipsum dolor sit amet consectetur, adipisicing elit.
                </p>
              </template>
              <template #interactions>
                <vs-button danger icon>
                  <i class='bx bx-heart'></i>
                </vs-button>
                <vs-button class="btn-chat" shadow primary>
                  <i class='bx bx-chat' ></i>
                  <span class="span">
                    54
                  </span>
                </vs-button>
              </template>
            </vs-card> -->

            <!-- 제일 상단 -->

            <div style="float:clear;">
              
              
              <v-card
                elevation="2"
                v-card-title="제목"
              >
               <!-- 프로필사진 시작-->
              <div class="photo" style="float:left;">
                <vs-card type="5">
                  <template #title>
                    <h3>나이:26</h3>
                  </template>
                  <template #img>
                    <img src="https://taegon.kim/wp-content/uploads/2018/05/image-5.png" alt="">
                  </template>
                  <template #text>
                    <p>
                      성별:비공개
                    </p>
                  </template>
                  <template #interactions>
                    <vs-button danger icon>
                      <i class='bx bx-heart'></i>
                    </vs-button>
                    <vs-button class="btn-chat" shadow primary>
                      <i class='bx bx-chat' ></i>
                      <span class="span">
                        54
                      </span>
                    </vs-button>
                  </template>
                </vs-card>
              </div>
              <!-- 프로필사진 끝 -->
              <!-- 자기소개 시작 -->
              <div class="nickinfo">
                nickname
              </div>
              <div class="nickinfo">
                자기소개
              </div>
              <div justify-content:space-between>
                커밋수
              </div>
              <div justify-content:space-between>
                팔로워수
              </div>
              <!-- 자기소개 끝 -->
              </v-card>

            </div>
            <!-- 상단 끝 -->
            
            <!-- 중간 시작 -->

            <!-- 중간 끝 -->
            </v-sheet>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    data: () => ({
      items: [
          'mdi-home', 'mdi-web', 'mdi-apple-keyboard-control', 'mdi-account',
        ], 
      value1: 1, 
    }),
  }
</script>

<style >
.navbar_right {
  display: flex;
  width: min-content
}
.search {
  display: flex;
  width: min-content;
}
.v-toolbar__content {
  display: flex;
  justify-content: space-between;
}

v-tab {
  width: 20px;

}

</style>