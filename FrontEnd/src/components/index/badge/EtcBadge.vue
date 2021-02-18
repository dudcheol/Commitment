<template>
  <div class="grid">
    <Dialog
      :confirm="modalflag"
      :confirmTitle="'알림'"
      :confirmContent="'대표뱃지로 설정하시겠습니까?'"
      @close="modalflag = false"
      @confirm-ok="repbadge(user.email)"
    ></Dialog>
    <vs-row class="oneRow">
      <vs-col :w="num">
        <template>
          <!--  -->
          <vs-card type="5" class="etccard" @click="modalup(0)">
            <template #img>
              <v-avatar size="100">
                <img
                  v-if="badgesflag[0]"
                  src="../../../assets/img/badge/first_commit.png"
                  alt="John"
                />
                <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
              </v-avatar>
            </template>
            <template #title>
              <div class="bookmark">
                <div class="bookmarkDescription">
                  <h4>여행의 시작</h4>
                </div>
              </div>
            </template>
            <template #text>
              <p v-if="badgesflag[0]">{{ badgesDate[0] }} 획득!</p>
              <p v-else>미 획득</p>
            </template>
          </vs-card>
        </template>
        <p class="badgeName">
          첫 커밋
        </p>
        <p class="badgeDescription">
          첫 커밋 기록!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard" @click="modalup(1)">
          <template #img>
            <v-avatar size="100">
              <img
                v-if="badgesflag[1]"
                src="../../../assets/img/badge/total_commit.png"
                alt="John"
              />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkButton">
                <v-progress-circular
                  :value="commitper"
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
            <p v-if="!badgesflag[1]">지금까지 {{ commitper }}% 달성!</p>
            <p v-else>{{ badgesDate[1] }} 획득!</p>
          </template>
        </vs-card>
        <p class="badgeName">
          전국 커밋
        </p>
        <p class="badgeDescription">
          커밋 100% 최초 달성!
        </p>
      </vs-col>
      <vs-col :w="num">
        <vs-card type="5" class="etccard" @click="modalup(2)">
          <template #img>
            <v-avatar size="100">
              <img
                v-if="badgesflag[2]"
                src="../../../assets/img/badge/first_following.png"
                alt="John"
              />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>인연의 시작</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[2]">{{ badgesDate[2] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          첫 팔로잉
        </p>
        <p class="badgeDescription">
          첫 팔로우 신청 보내기!
        </p>
      </vs-col>
    </vs-row>
    <vs-row class="oneRow">
      <vs-col :w="num">
        <vs-card type="5" class="etccard" @click="modalup(3)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[3]" src="../../../assets/img/badge/dokdo.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>독도는 우리땅!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[3]">{{ badgesDate[3] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          독도 홀릭
        </p>
        <p class="badgeDescription">
          독도에서 첫 커밋!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard" @click="modalup(4)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[4]" src="../../../assets/img/badge/seoul.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>첫 서울 여행</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[4]">{{ badgesDate[4] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          서울 홀릭
        </p>
        <p class="badgeDescription">
          서울에서 첫 커밋!
        </p>
      </vs-col>
      <vs-col :w="num">
        <vs-card type="5" class="etccard" @click="modalup(5)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[5]" src="../../../assets/img/badge/gangwondo.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>첫 강원도 여행!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[5]">{{ badgesDate[5] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          강원도 홀릭
        </p>
        <p class="badgeDescription">
          강원도에서 첫 커밋!
        </p>
      </vs-col>
    </vs-row>
    <vs-row class="oneRow">
      <vs-col :w="num">
        <vs-card type="5" class="etccard" @click="modalup(6)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[6]" src="../../../assets/img/badge/busan.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>첫 부산 여행!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[6]">{{ badgesDate[6] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          부산 홀릭
        </p>
        <p class="badgeDescription">
          부산에서 첫 커밋!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard" @click="modalup(7)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[7]" src="../../../assets/img/badge/gyeonggido.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>첫 경기도 여행!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[7]">{{ badgesDate[7] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          경기도 홀릭
        </p>
        <p class="badgeDescription">
          경기도에서 첫 커밋!
        </p>
      </vs-col>
      <vs-col :w="num2">
        <vs-card type="5" class="etccard" @click="modalup(8)">
          <template #img>
            <v-avatar size="100">
              <img v-if="badgesflag[8]" src="../../../assets/img/badge/ulsan.png" alt="John" />
              <img v-else src="../../../assets/img/badge/lock3.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <div class="bookmark">
              <div class="bookmarkDescription">
                <h4>첫 울산 여행!</h4>
              </div>
            </div>
          </template>
          <template #text>
            <p v-if="badgesflag[8]">{{ badgesDate[8] }} 획득!</p>
            <p v-else>미 획득</p>
          </template>
        </vs-card>
        <p class="badgeName">
          울산 홀릭
        </p>
        <p class="badgeDescription">
          울산에서 첫 커밋!
        </p>
      </vs-col>
    </vs-row>
  </div>
</template>
<script>
import { commitrate, badgeList, mybadge } from '../../../api/badge';
import Dialog from '../../common/dialog/Dialog.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'EtcBadge',
  components: { Dialog },
  data() {
    return {
      num: 4,
      num2: 4,
      mainbadge: '',
      modalflag: false,
      commitper: 0,
      badges: [],
      badgesflag: [],
      badgesDate: ['', '', '', '', '', '', '', '', ''],
      badgename: [
        'first_commit',
        'total_commit',
        'first_following',
        'dokdo',
        'seoul',
        'gangwondo',
        'busan',
        'gyeonggido',
        'ulsan',
      ],
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
  mounted() {
    this.mainbadge = this.user.badge;
    commitrate(
      this.user.email,
      (response) => {
        this.commitper = response.data.totalCommit;
      },
      (error) => {
        console.log(error);
      }
    );
    badgeList(
      this.user.email,
      (response) => {
        var arr = response.data;
        for (let i = 0; i < arr.length; i++) {
          this.badges.push(arr[i]);
        }
        var badgedate = new Array(9);
        this.badgesflag = [false, false, false, false, false, false, false, false, false];
        for (let i = 0; i < this.badges.length; i++) {
          if (this.badges[i].name == 'first_commit') {
            this.badgesflag[0] = true;
            badgedate[0] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'total_commit') {
            this.badgesflag[1] = true;
            badgedate[1] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'first_following') {
            this.badgesflag[2] = true;
            badgedate[2] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'dokdo') {
            this.badgesflag[3] = true;
            badgedate[3] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'seoul') {
            this.badgesflag[4] = true;
            badgedate[4] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'gangwondo') {
            this.badgesflag[5] = true;
            badgedate[5] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'busan') {
            this.badgesflag[6] = true;
            badgedate[6] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'gyeonggido') {
            this.badgesflag[7] = true;
            badgedate[7] = this.badges[i].createdAt.split(' ')[0];
          } else if (this.badges[i].name == 'ulsan') {
            this.badgesflag[8] = true;
            badgedate[8] = this.badges[i].createdAt.split(' ')[0];
          }
        }
        this.badgesDate = badgedate;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    repbadge(email) {
      mybadge(
        email,
        this.mainbadge,
        () => {
          this.$emit('update-badge');
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modalup(num) {
      if (this.badgesflag[num]) {
        //획득한 뱃지라면 대표뱃지 설정가능
        this.modalflag = true;
        this.mainbadge = this.badgename[num];
      }
    },
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
