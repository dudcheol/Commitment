<template>
  <div>
    <map-national
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-national>
    <map-busan
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-busan>
    <map-gangwon
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gangwon>
    <map-gwangju
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gwangju>
    <map-gyeonggi
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-gyeonggi>
    <map-seoul
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-seoul>
    <map-ulsan
      :size="10"
      :spacing="1"
      :borderRadius="1"
      :datas="datas"
      @square-click="click"
    ></map-ulsan>
  </div>
</template>

<script>
import MapBusan from '../components/MapBusan.vue';
import MapGangwon from '../components/MapGangwon.vue';
import MapGwangju from '../components/MapGwangju.vue';
import MapGyeonggi from '../components/MapGyeonggi.vue';
import MapNational from '../components/MapNational.vue';
import MapSeoul from '../components/MapSeoul.vue';
import MapUlsan from '../components/MapUlsan.vue';

export default {
  components: { MapNational, MapBusan, MapGangwon, MapSeoul, MapUlsan, MapGyeonggi, MapGwangju },
  data() {
    return {
      // temp data
      datas: [
        [12, 14, 2],
        [12, 15, 5],
        [12, 16, 1],
      ],
    };
  },
  methods: {
    click(obj) {
      console.log('%cCommitMapTest.vue line:14 obj', 'color: #007acc;', obj);
    },
  },
};
</script>
