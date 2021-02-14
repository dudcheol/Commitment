<template>
  <div class="external">
    <div class="horizontal-scroll-wrapper">
      <div class="img-wrapper slower">
        <a href="" target="_blank" rel="noopener">
        <img src="https://images.pexels.com/photos/5589596/pexels-photo-5589596.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper faster">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/380707/pexels-photo-380707.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper slower vertical">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/5635201/pexels-photo-5635201.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper slower slower-down">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/4430126/pexels-photo-4430126.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/6306935/pexels-photo-6306935.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper slower">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/4447401/pexels-photo-4447401.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>

      <div class="img-wrapper faster1">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/5774152/pexels-photo-5774152.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="pola">
        </a>
      </div>
      
      <div class="img-wrapper slower slower2">
        <a href="" target="_blank" rel="noopener">
          <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/74321/cafe-table-street.jpg" alt="">
        </a>
      </div>
      
      <div class="img-wrapper">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/6399717/pexels-photo-6399717.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="">
        </a>
      </div>
      
      <div class="img-wrapper slower">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/6306913/pexels-photo-6306913.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="">
        </a>
      </div>
      
      <div class="img-wrapper slower last">
        <a href="" target="_blank" rel="noopener">
          <img src="https://images.pexels.com/photos/6306941/pexels-photo-6306941.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="">
        </a>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  props: ['nickname', 'intro', 'img'],
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.pola{
  margin-bottom: 15%;
}
/* hide scrollbar */
::-webkit-scrollbar {
  width: 1px;
  height: 1px;
}

::-webkit-scrollbar-button {
  width: 1px;
  height: 1px;
}
.external {
  overflow: hidden;
  height: 100vh;
}

.horizontal-scroll-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vh;
  transform: rotate(-90deg) translate3d(0,-100vh,0);
  transform-origin: right top;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 0;
  height: 100vw;
  perspective: 1px;
  transform-style: preserve-3d;
  padding-bottom: 10rem;
}
.img-wrapper {
  transform: rotate(90deg);
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 40vh;
  transform-origin: 50% 50%;
  transform: rotate(90deg) translateZ(.1px) scale(0.9) translateX(0px) translateY(-3vh);
  transition: 1s;
}

.img-wrapper:hover {
 min-height: 65vh;
}

.slower {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(0%) translateY(-10vh);
}
.slower1 {
  transform: rotate(90deg) translateZ(-.25px) scale(1.05) translateX(0%) translateY(8vh);
}
.slower2 {
  transform: rotate(90deg) translateZ(-.3px) scale(1.3) translateX(0%) translateY(2vh);
}
.slower-down {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(0%) translateY(16vh) ;
}
.faster {
  transform: rotate(90deg) translateZ(.15px) scale(0.8) translateX(0%) translateY(14vh) ;
}
.faster1 {
  transform: rotate(90deg) translateZ(.05px) scale(0.8) translateX(0%) translateY(10vh) ;
}
.fastest {
  transform: rotate(90deg) translateZ(.22px) scale(0.7) translateX(-10vh) translateY(-15vh) ;
}
.vertical {
   transform: rotate(90deg) translateZ(-.15px) scale(1.15) translateX(0%) translateY(0%) ;
}
.last {
  transform: rotate(90deg) translateZ(-.2px) scale(1.1) translateX(25vh) translateY(-8vh) ;
}
.scroll-info, header {
  position: absolute;
  left: 1rem;
}


header {
  bottom: 1rem;
}
.scroll-info {
  top: 1rem;
}
a {
  color: inherit;
  font-weight: 500;
  
}
h1 {
  font-weight: 300;
  font-size: 1rem;
}

.img-wrapper a {
  overflow: hidden;
  display: block;
  padding: 1vh ;
  background: #edfbff;
  border: 1px black;
}
img {
  max-width: 45vh;
  max-height: 50vh;
  transition: .5s;
  vertical-align: top;
  /* filter: saturate(40%) sepia(30%) hue-rotate(5deg); */
}
a:hover img {
  filter: none;
}
p {
  margin: 0;
}
.scroll-info {
  display: flex;
  align-items: center;
}
.icon svg {
    width: 50px;
    fill: currentcolor;
}
</style>
