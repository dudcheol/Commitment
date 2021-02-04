<template>
  <div class="grid-container">
    <div class="card_list">
      <div class="card mt-4" v-for="item in items" :key="item">
        <main-card></main-card>
      </div>
    </div>
    <div class="category">
      <v-card class="mt-3">
        <div class="date_selector d-flex justify-center mt-3 ml-3 mb-3 mr-3">
          <div class="d-flex align-center justify-center mr-4">
            <i class="bx bxs-calendar-alt"></i>
          </div>
          <vs-select color="primary" placeholder="날짜" v-model="date">
            <vs-option label="1일" value="1">
              1일
            </vs-option>
            <vs-option label="2일" value="2">
              2일
            </vs-option>
            <vs-option label="3일" value="3">
              3일
            </vs-option>
            <vs-option label="1주" value="4">
              1주
            </vs-option>
            <vs-option label="1달" value="5">
              1달
            </vs-option>
            <vs-option label="1년" value="6">
              1년
            </vs-option>
            <vs-option label="전체" value="7">
              전체
            </vs-option>
          </vs-select>
        </div>
        <div class="domian_selector d-flex justify-center mt-2 ml-3 mb-3 mr-3">
          <div class="d-flex align-center justify-center mr-4">
            <i class="bx bxs-landscape"></i>
          </div>
          <vs-select filter multiple placeholder="지역" v-model="options">
            <vs-option label="서울" value="1">
              서울
            </vs-option>
            <vs-option label="경기도" value="2">
              경기도
            </vs-option>
            <vs-option label="강원도" value="3">
              강원도
            </vs-option>
            <vs-option label="울산" value="4">
              울산
            </vs-option>
            <vs-option label="광주" value="5">
              광주
            </vs-option>
            <vs-option label="부산" value="6">
              부산
            </vs-option>
            <vs-option label="제주도" value="7">
              제주도
            </vs-option>
          </vs-select>
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
import MainCard from '../../components/common/card/MainCard';

export default {
  components: {
    MainCard,
  },
  data: () => ({
    items: [1, 2, 3, 4],
    date: '7',
    options: '1',
  }),
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 1.5fr 3fr 1.5fr;
  grid-template-rows: 1fr 1fr;
  gap: 0px 0px;
  grid-template-areas:
    'category card .'
    '. card .';
}

.card_list {
  grid-area: card;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.card_list .card {
  width: 100%;
}

.category {
  grid-area: category;
  display: grid;
  justify-items: center;
}

.v-sheet.v-card {
  display: grid;
  width: 80%;
  border-radius: 10px;
  height: min-content;
}

@media (max-width: 450px) {
  .grid-container {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: min-content 1fr;
    gap: 0px 0px;
    grid-template-areas:
      'category'
      'card';
  }

  .card_list {
    grid-area: card;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .card_list .card {
    width: 100%;
  }

  .category {
    grid-area: category;
    display: grid;
    justify-items: center;
    height: min-content;
  }
}
</style>
