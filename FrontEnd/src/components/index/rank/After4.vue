<template>

<div class="frame">
  <div class="tables">
    <vs-table>
      <template #tbody>
        <vs-tr
          :key="i"
          v-for="(tr, i) in users"
          :data="tr"
        >
          <vs-td class="rankNum">
            {{ tr.id }}
            
          </vs-td>
          <vs-td>
            <v-avatar
            size="50"  
            >
              <img
                src="https://t1.daumcdn.net/cfile/tistory/251A1742591983A922"
                alt="John"
              >
            </v-avatar>
          </vs-td>
          <vs-td>
          {{ tr.nickname }}
          </vs-td>
          <vs-td class="percentage">
          {{ tr.count }}%
          </vs-td>
          <vs-td class="percentage">
            <vs-button
              icon
              border
              :active="active == 2"
              @click="active = 2"
            >
              <i class='bx bxs-heart' ></i>
            </vs-button>
          </vs-td>
        </vs-tr>
      </template>
    </vs-table>
  </div>
</div>

</template>

<script>
export default {
    data:()=>({
        users: [
          {
            "id": 4,
            "nickname": "Leanne Graham",
            "count": 57.01,
          },
          {
            "id": 5,
            "nickname": "Ervin Howell",
            "count": 46.22,
          },
          {
            "id": 6,
            "nickname": "Clementine Bauch",
            "count": 43.55,
          },
          {
            "id": 7,
            "nickname": "Patricia Lebsack",
            "count": 36.95,
          },
          {
            "id": 8,
            "nickname": "Chelsey Dietrich",
            "count": 31.25,
          },
          {
            "id": 9,
            "nickname": "Mrs. Dennis Schulist",
            "count": 24.59,
          },
          {
            "id": 10,
            "nickname": "Kurtis Weissnat",
            "count": 22.14,
          },
        ],
    })
}
</script>

<style scoped>
.tables{
  background-color: white;
  
}
.profiles{
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
.rankNum{
  color:#2196F3;
  /* font-weight: bold; */
}
.percentage{
  color:#1976D2;
  font-size:large;
  /* font-weight: bold; */
}
</style>