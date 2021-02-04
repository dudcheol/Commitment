<template>
  <div class="grid">
    <vs-row class="oneRow">
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/badge1.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <vs-button
                  icon
                  color="danger"
                  border
                  :active="active == 2"
                  @click="active = 2"
                >
                  <i class="bx bxs-heart"></i>
                </vs-button>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/lock2.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="40"
                  color="grey"
                  size="20"
                ></v-progress-circular>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              획득까지 60% 남았어요!
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/lock2.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="40"
                  color="grey"
                  size="20"
                ></v-progress-circular>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
    </vs-row>
    <vs-row class="oneRow">
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/lock2.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="40"
                  color="grey"
                  size="20"
                ></v-progress-circular>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/badge5.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <vs-button
                  icon
                  color="danger"
                  border
                  :active="active == 2"
                  @click="active = 2"
                >
                  <i class="bx bxs-heart"></i>
                </vs-button>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/lock2.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="40"
                  color="grey"
                  size="20"
                ></v-progress-circular>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
    </vs-row>
    <vs-row class="oneRow">
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/badge7.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <vs-button
                  icon
                  color="danger"
                  border
                  :active="active == 2"
                  @click="active = 2"
                >
                  <i class="bx bxs-heart"></i>
                </vs-button>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/lock2.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="40"
                  color="grey"
                  size="20"
                ></v-progress-circular>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num">
        <vs-card type="5" class="etccard">
          <template #img>
            <v-avatar size="100">
              <img src="../../../assets/img/badge/badge9.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <vs-button
                  icon
                  color="danger"
                  border
                  :active="active == 2"
                  @click="active = 2"
                >
                  <i class="bx bxs-heart"></i>
                </vs-button>
              </div>
              <div class="bookmarkDescription">
                <h4>발로 그린 지도!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p>
              2021.01.28 획득
            </p>
          </template>
        </vs-card>
        <p class="badgeName">
          커밋홀릭
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
    </vs-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      num: 4,
      num2: 4,
    };
  },
};
</script>

<style scoped>
.grid {
  text-align: center;

  height: 100%;
}
.oneRow {
  margin-top: 30px;
}
.etccard {
  z-index: 0;
}
.badgeName {
  font-weight: bold;
}
.badgeDescription {
  font-size: 12px;
  color: gray;
  margin-top: -15px;
}
.bookmark {
  display: flex;
}
.bookmarkButton {
  margin-top: -25px;
  margin-left: -60px;
}
.bookmarkDescription {
  margin-left: 20px;
}
.v-progress-circular {
  margin: 1rem;
}
</style>
