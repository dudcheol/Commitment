<template>
  <div>
    <div>
      <img id = "image_id" src = "image01.jpg">
    </div>
   
  </div>
</template>

<script>
export default {
    data() {
      return {
        nObjectCnt: 0,
        ObjectArray : [
          1,2,3,4,
        ],

      };
    },
    methods: {
      obtimeOut() {

      },
      ShowDefaultRotate() {
          // let ObjectArray = new Array();
          // ObjectArray[1] = "image01.jpg";

          // let nObjectCnt = 0;
        // const obTimeOut;

        this.nObjectCnt++;

        if (this.nObjectCnt < this.ObjectArray.length) {
          document.getElementById("img_id").scr = this.ObjectArray[this.nObjectCnt];
          this.obTimeOut = setTimeout("ShowDefaultRotate()", 1000);
        }
        else
        {
          clearTimeout(this.obTimeOut);
        }
      },
        startAnmation() {
          this.obTimeOut = window.setTimeout(this.ShowDefaultRotate, 100);
        },
      },
    
}
</script>

<style>

</style>