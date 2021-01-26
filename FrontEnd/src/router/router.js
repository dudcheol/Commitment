import Vue from 'vue'
import Router from 'vue-router'
// 연결할 컴포넌트 import
import Header from "./../components/layout/Header.vue"
import Footer from "./../components/layout/Footer.vue"

// 필수
Vue.use(Router)
export default new Router({
    mode: 'history', // history 모드는 자연스러운 url 가능, 지정하지 않으면 해시(#)기호로 url 사용
    routes: [
        {
            path: "/header", // 경로
            name: "Header", // 해당 경로의 이름 
            component: Header // 이동할 컴포넌트
        },
        {
            path: "/footer",
            name: "Footer",
            component: Footer
        },
    ]
})
