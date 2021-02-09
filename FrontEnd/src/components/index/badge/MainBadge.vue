<template>
  <div>
    <div class="d-flex justify-center">
      <div>
        <vs-card class="maincard">
          <template #img>
            <v-avatar size="130">
              <img src="../../../assets/img/badge/badge1.png" alt="John" />
            </v-avatar>
          </template>
          <template #title>
            <h3>대표 뱃지</h3>
          </template>
          <template #text>
            <p class="badgeDescription">
              커밋홀릭
            </p>
          </template>
        </vs-card>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
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
