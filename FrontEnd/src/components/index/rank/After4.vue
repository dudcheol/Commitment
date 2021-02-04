<template>

<div class="d-flex justify-center">
  <div class="tables">
    <vs-table>
      <template #thead>
        <vs-tr>
          <vs-th>
            랭크
          </vs-th>
          <vs-th>
            프로필
          </vs-th>
          <vs-th>
            닉네임
          </vs-th>
          <vs-th>
            수
          </vs-th>
        </vs-tr>
      </template>
      <template #tbody>
        <vs-tr
          :key="i"
          v-for="(tr, i) in users"
          :data="tr"
        >
          <vs-td>
            {{ tr.id }}
            
          </vs-td>
          <vs-td>
            <v-avatar
            size="50"  
            >
              <img
                src="https://cdn.vuetifyjs.com/images/john.jpg"
                alt="John"
              >
            </v-avatar>
          </vs-td>
          <vs-td>
          {{ tr.nickname }}
          </vs-td>
                  <vs-td>
          {{ tr.count }}
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
            "count": 11,
          },
          {
            "id": 5,
            "nickname": "Ervin Howell",
            "count": 11,
          },
          {
            "id": 6,
            "nickname": "Clementine Bauch",
            "count": 11,
          },
          {
            "id": 7,
            "nickname": "Patricia Lebsack",
            "count": 11,
          },
          {
            "id": 8,
            "nickname": "Chelsey Dietrich",
            "count": 11,
          },
          {
            "id": 9,
            "nickname": "Mrs. Dennis Schulist",
            "count": 11,
          },
          {
            "id": 10,
            "nickname": "Kurtis Weissnat",
            "count": 11,
          },
        ],
    })
}
</script>

<style scoped>
.three{
  display:grid;
}
.profiles{
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
</style>