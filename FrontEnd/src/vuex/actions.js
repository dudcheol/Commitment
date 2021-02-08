import { findByToken, login, setAuthTokenToHeader, logout } from '../api/account';
import router from '../router';
import axios from 'axios'
// import axios from '../api/index.js'

export default {
  async LOGIN(context, user) {
    let result = false;
    await login(
      user,
      (response) => {
        if (response.data.data === 'success') {
          context.commit('LOGIN', response.data);
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          result = true;
        }
      },
      (error) => {
        console.log('%cactions.js line:13 error', 'color: #007acc;', error);
      }
    );
    return result;
  },
  async GET_MEMBER_INFO(context, token) {
    await findByToken(
      token,
      (response) => {
        let user = response.data.user;
        user.badgeCnt = response.data.badgeCnt;
        user.commitCnt = response.data.commitCnt;
        user.followerCnt = response.data.followerCnt;
        context.commit('GET_MEMBER_INFO', { token, user });
      },
      (error) => {
        console.log('%cactions.js line:26 error', 'color: #007acc;', error);
      }
    );
  },
  LOGOUT(context) {
    context.commit('LOGOUT');
    localStorage.removeItem('auth-token');
    logout();
  },
  CURRENT_POSITION(context) {
    if ('geolocation' in navigator) {
      /* 위치정보 사용 가능 */
      navigator.geolocation.watchPosition(
        (position) => {
          context.commit('CURRENT_POSITION', {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          });
        },
        (error) => {
          if (error.code == error.PERMISSION_DENIED) {
            router.replace('/permission');
          }
        }
      );
    } else {
      /* 위치정보 사용 불가능 */
      console.log('%cactions.js line:50 위치정보를 사용할 수 없음.', 'color: #007acc;');
    }
  },
  // SIGNUP: (store, payload) => {
  //   console.log(payload);
  //   axios
  //     .post("/account/signup", payload)
  //     .then((response) => {
  //       console.log("회원가입 : " + response.data.length);
  //       store.dispatch("SMTP",payload);
  //     })
  //     .catch((response, error) => {
  //       console.log("FAIL : " + response.status);
  //       console.log(error);
  //       router.push("/error");
  //     });
  //   }, 

    SMTP: (store, payload) => {
      console.log(payload);
      axios
        .get('https://i4a308.p.ssafy.io:8080/account/smtp' + payload.email)
        .then((response) => {
          console.log("smtp : " + response.data.length);
        })
        .catch((response, error) => {
          console.log("FAIL : " + response.status);
          console.log(error);
          alert("이메일 발송 실패: 유효하지 않은 이메일 입니다.");
          store.dispatch("DELETE",payload.email);
        });
    },

    // DELETE:(store,payload) => {
    //   console.log(payload);
    //   axios
    //     .delete("/account/delete?email=" + payload)
    //     .then((response) => {
    //       console.log(response);
    //       if (response.data.data == "success") {
    //         console.log("sc");
    //         store.dispatch('logout')
    //       } else {
    //         console.log("fa");
    //       }
    //     })
    //     .catch((response, error) => {
    //       console.log("FAIL : " + response.status);
    //       console.log(error);
    //       router.push("/error");
    //     });
    // }


};
