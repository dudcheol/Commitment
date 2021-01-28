<template>

<div>
    <vs-card>

    <template #title>
      <h3>필터</h3>
    </template>

    <template #text>
      <div class="d-flex justify-center">
      선택하세요
      </div>
      <div class="filters">
        <vs-select placeholder="Select" v-model="valueOne">
          <vs-option label="커밋 수" value="1">
            커밋 수
          </vs-option>
          <vs-option label="좋아요 수" value="2">
            좋아요 수
          </vs-option>
        </vs-select>
        <vs-select placeholder="Select" v-model="valueTwo">
          <vs-option label="일간" value="1">
            일간
          </vs-option>
          <vs-option label="주간" value="2">
            주간
          </vs-option>
          <vs-option label="연간" value="3">
            연간
          </vs-option>
        </vs-select>
      </div>

      <div>
          <vs-button
            class="big_button"
            gradient
            :active="active == 0"
            @click="active = 0"
          >
            전국
          </vs-button>
      </div>
      <div class="small_buttons">
          <vs-button
            gradient
            :active="active == 0"
            @click="active = 0"
          >
            서울
          </vs-button>
          <vs-button
            color="rgb(59,222,200)"
            gradient
            :active="active == 1"
            @click="active = 1"
          >
            경기
          </vs-button>
          <vs-button
            danger
            gradient
            :active="active == 2"
            @click="active = 2"
          >
            울산
          </vs-button>
      </div>
      <div class="small_buttons">
        <vs-button
          warn
          gradient
          :active="active == 3"
          @click="active = 3"
        >
          강원
        </vs-button>
        <vs-button
        color="#7d33ff"
        gradient
        :active="active == 5"
        @click="active = 5"
      >
        광주
      </vs-button>
      <vs-button
        color="rgb(59,222,200)"
        gradient
        :active="active == 6"
        @click="active = 6"
      >
        부산
      </vs-button>
      </div>
    </template>

    <template #interactions>
    </template>
  </vs-card>

</div>

</template>

<script>
export default {
    data:()=>({
      valueOne: '',
      valueTwo: '',
    })
}
</script>

<style>
.filters{
  margin-bottom: 15px;
}
.vs-card__title {
    display: flex;
    justify-content: center;
}
.profile {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.big_button {
    width: 95%;
    display: flex;
    justify-content: center;
}

.small_buttons {
    display: flex;
    justify-content: center;
}
</style>