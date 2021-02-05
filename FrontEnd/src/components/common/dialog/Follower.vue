<template>
    <div class="center">
      <vs-button
            danger
            border
            v-bind="attrs"
            v-on="on"
            :active="active == 2"
            @click="active=!active"
        >
            <i class="bx bxs-heart"> 팔로워<span class="numbers"> 30</span></i> 
        </vs-button>
      <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="400px">
        <template #header>
          <h3>
            팔로워
          </h3>
        </template>
        <div class="con-content">
          <commit-card
            v-for="(item, index) in nowCommits"
            :key="index"
            :img="'https://i.picsum.photos/id/499/200/300.jpg?hmac=KWCVeuvGZEdEyxpon_0lUlQ2hJzA3Tli3AQTLM5RLXA'"
            :nickname="item.nickname"
            :intro="item.intro"
            class="mb-2"
          ></commit-card>
          <div class="whole">팔로워 모두 보기</div>
        </div>
      </vs-dialog>
    </div>
</template>
<script scoped>
import CommitCard from '../../index/mypage/FollowLists';
  export default {
    components: { CommitCard },
    data () {
      return {
        active: false,
        input1: '',
        input2: '',
        checkbox1: false,
        nowCommits: [
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
        { nickname: 'nickname', intro: 'hellohellohellohellohellohello' },
      ],
      }
    },
  }
</script>
<style scoped>
.whole{
  text-align: center;
  color:dodgerblue;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: -15%;
}
</style>