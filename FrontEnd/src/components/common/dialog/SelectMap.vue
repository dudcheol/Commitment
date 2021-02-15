<template>
    <div class="center">
      <vs-button
            icon
            color="#651FFF"
            border
            v-bind="attrs"
            v-on="on"
            :active="active == 2"
            @click="active=!active"
        >
            <i class="bx bxs-select-multiple"></i> 
        </vs-button>
      <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="650px">
        <template #header>
          <div display="grid">
            <v-alert
            outlined
            type="success"
            >
              <MapNational/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapGyeonggi/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapBusan/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapGangwon/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapGwangju/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapSeoul/>
            </v-alert>
            <v-alert
              outlined
              color="blue"
            >
              <MapUlsan/>
            </v-alert>
          </div>
          <div>

          </div>
        </template>
        <div class="con-content">
          <div class="con-form">
            

          </div>

          <div class="footer-dialog">
            <vs-button block>
              선택
            </vs-button>
          </div>
          <!--  -->
        </div>
      </vs-dialog>
    </div>
</template>
<script scoped>
import MapNational from '../map/MapNational'
import MapGyeonggi from '../map/MapGyeonggi'
import MapBusan from '../map/MapBusan'
import MapGangwon from '../map/MapGangwon'
import MapGwangju from '../map/MapGwangju'
import MapSeoul from '../map/MapSeoul'
import MapUlsan from '../map/MapUlsan'
  export default {
    components: { 
      MapNational,
      MapGyeonggi,
      MapBusan,
      MapGangwon,
      MapGwangju,
      MapSeoul,
      MapUlsan
     },
    data: () => ({
    active: false,
    value: '',
    email: '',
    password: '',
    value2: '',
    remember: false,
  }),
  methods: {
  }
}
</script>
<style lang="stylus" scoped>
.v-content .v-card{
  border : 5px red !important;
}
</style>
