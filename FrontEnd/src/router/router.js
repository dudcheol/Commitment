import Vue from 'vue'
import Router from 'vue-router'
// 연결할 컴포넌트 import
import Main from '../views/Main.vue'
import Signup from '../views/Signup.vue'
import Login from '../views/Login.vue'
import Mypage from '../views/Mypage.vue'
// 필수
Vue.use(Router)
export default new Router({
    mode: 'history', // history 모드는 자연스러운 url 가능, 지정하지 않으면 해시(#)기호로 url 사용
    routes: [
        {
            path: "/", // 경로
            name: "Main", // 해당 경로의 이름 
            component: Main, // 이동할 컴포넌트
            children: [
                {
                    path: 'home',
                    name: 'Home',
                    component:()=>import('../views/main/Home.vue'),
                },
                { // 예시임
                    path: 'sns',
                    name: 'SNS',
                    component:()=>import('../views/main/SNS.vue'),
                },

            ],
        },
        {
            path: "/signup",
            name: "Signup",
            component: Signup,
        },
        {
            path: "/login",
            name: "Login",
            component: Login,
        },
        {
            path: "/mypage",
            name: "Mypage",
            component:Mypage,
        }
    ]
})