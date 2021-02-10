<template>
  <div>

  </div>
</template>

<script>
export default {
    created() {

    },
    methods: {
      ShowDefaultRotate() {
          // let obTimeOut;
          // let ObjectArray = new Array();
          // ObjectArray[1] = "image01.jpg";

          // let nObjectCnt = 0;

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