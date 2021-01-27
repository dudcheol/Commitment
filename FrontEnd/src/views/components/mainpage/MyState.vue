<template>
  <div class="mt-6">
    <div class="my_location">
        <v-icon class="ml-4 mr-2">mdi-pin</v-icon>
        서울특별시 강남구 역삼동 701
    </div>

    <div class="commit_input mt-4 mb-10 ml-4 mr-4" height="10vh">

        <div class="d-flex">
            <vs-avatar>
              <img src="https://picsum.photos/200/300" alt="">
            </vs-avatar>
            <vs-input class="ml-2" placeholder="게시글을 작성해주세요">
            </vs-input>
        </div>
        <div class="buttons"> 
            <vs-button color="#6690FF">이미지</vs-button>
            <vs-button color="#254EDB">등록</vs-button>
        </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.my_location {
    display: flex;
  align-items: center;

}
.commit_input {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    align-content: center;
}

.buttons {
    display: flex;
    flex-direction: row;
}

.commit_input .vs-input {
  width: 50vh ;
}



</style>