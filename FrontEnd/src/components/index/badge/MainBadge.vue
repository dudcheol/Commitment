<template>
  <div>
    <div class="d-flex justify-center">
      <div>
        <vs-card class="maincard">
          <template #img>
            <v-avatar size="130">
              <img v-if="!user.badge" src="../../../assets/img/badge/badge0.png" />
              <img v-else :src="require(`../../../assets/img/badge/${user.badge}.png`)" />
            </v-avatar>
          </template>
          <template #title>
            <center>
              <h1>{{ name }}</h1>
            </center>
          </template>
          <template #text>
            <p class="badgeDescription">
              대표 뱃지
            </p>
          </template>
        </vs-card>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';
import { mainbadge } from '../../../api/badge';
export default {
  name: 'MainBadge',
  data() {
    return {
      path: '',
      badge: '',
      name: '',
    };
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
  methods: {
    ...mapActions(['UPDATE_USERINFO_BY_NICKNAME']),
  },
  created() {
    this.badge = this.user.badge;
    mainbadge(
      this.user.email,
      (response) => {
        this.UPDATE_USERINFO_BY_NICKNAME(this.user.nickname);
        this.badge = response.data;
        if (this.badge == 'first_commit') {
          this.name = '첫 커밋';
        } else if (this.badge == 'total_commit') {
          this.name = '전국 커밋';
        } else if (this.badge == 'first_following') {
          this.name = '첫 팔로잉';
        } else if (this.badge == 'dokdo') {
          this.name = '독도 홀릭';
        } else if (this.badge == 'seoul') {
          this.name = '서울 홀릭';
        } else if (this.badge == 'gangwondo') {
          this.name = '강원도 홀릭';
        } else if (this.badge == 'busan') {
          this.name = '부산 커밋';
        } else if (this.badge == 'gyenggido') {
          this.name = '경기도 커밋';
        } else if (this.badge == 'ulsan') {
          this.name = '울산 홀릭';
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped>
.percentageBar {
  text-align: center;
  width: 100%;
}
.percentageNum {
  color: rgb(224, 136, 150);
  font-size: 15px;
  left: 70%;
}
.maincard {
  width: 200px;
}
.badgeDescription {
  text-align: center;
}
</style>
