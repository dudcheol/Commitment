# 개발 일지 🐱‍👓

> 해성 개발 일지 

<br>

## 소셜로그인 정리 한것들

[https://parkjihwan.tistory.com/9](https://parkjihwan.tistory.com/9)

페이스북,구글,카카오로그인

[https://velog.io/@cloudlee711/vue-구글-로그인-후기](https://velog.io/@cloudlee711/vue-%EA%B5%AC%EA%B8%80-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%ED%9B%84%EA%B8%B0)

구글로그인

## 회원가입관련

import axios해온다음에

axios.post('url', this.~~) 이런식으로 바로작성하는데 

이방법이 아니라 다른 방법인가..? 우리 프로젝트에서는 store나뉘어져있고 또  axios경운

directory에서는 account.js에서 하는거같은데 헷갈리니깐 일단 자료 찾아봐서 여기다가 따로 정리하기

```markdown
<!-- src/components/auth/signup.vue -->
......
<script>
  import axios from 'axios';
    
  export default {
    data () {
      return {
        email: '',
        age: null,
        password: '',
        confirmPassword: '',
        country: 'usa',
        hobbyInputs: [],
        terms: false
      }
    },
    methods: {
      ......
      onSubmit () {
        const formData = {
          email: this.email,
          age: this.age,
          password: this.password,
          confirmPassword: this.confirmPassword,
          country: this.country,
          hobbies: this.hobbyInputs.map(hobby => hobby.value),
          terms: this.terms
        }
        console.log(formData)
          
        axios.post('https://<project>.firebaseio.com/users.json', formData)
            .then(res => console.log(res))
            .catch(error => console.log(error))
      }
    }
  }
</script>
```

[https://velog.io/@jyongk/Vue.js-로그인회원가입-예제Front-end](https://velog.io/@jyongk/Vue.js-%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EC%98%88%EC%A0%9CFront-end)

참고 사이트 1

## Infinite autoscrolling

[https://tympanus.net/codrops/2021/01/05/creating-an-infinite-auto-scrolling-gallery-using-webgl-with-ogl-and-glsl-shaders/](https://tympanus.net/codrops/2021/01/05/creating-an-infinite-auto-scrolling-gallery-using-webgl-with-ogl-and-glsl-shaders/)

webgl이라는 것을 이용해서 일단 원통형으로 만든다음에 그위에 사진을 배치하고 

그다음에 원도우사이즈 맞춰서 조정하고 마우스로 스크롤다운업할때마다 알아서  조정해주는느낌