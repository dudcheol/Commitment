import { findByToken, login, setAuthTokenToHeader, logout, signup, smtp } from '../api/account';
import { latlngToAddress } from '../api/commit';
import router from '../router';
// import axios from 'axios';

export default {
  async LOGIN(context, user) {
    let result = false;
    await login(
      user,
      (response) => {
        context.commit('LOGIN', response.data.data);
        if (response.data.data === 'success') {
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          context.dispatch('GET_MEMBER_INFO', response.data['auth-token']);
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
  CURRENT_LATLNG(context) {
    if ('geolocation' in navigator) {
      /* 위치정보 사용 가능 */
      navigator.geolocation.watchPosition(
        (position) => {
          const latlng = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          context.commit('CURRENT_LATLNG', latlng);
          context.dispatch('LATLNG_TO_ADDRESS', latlng);
        },
        (error) => {
          console.log(
            '%cerror actions.js line:60 ',
            'color: red; display: block; width: 100%;',
            error
          );
          router.replace('/permission');
          context.commit('CURRENT_LATLNG', null);
          context.commit('LATLNG_TO_ADDRESS', null);
        }
      );
    } else {
      /* 위치정보 사용 불가능 */
      console.log('%cactions.js line:50 위치정보를 사용할 수 없음.', 'color: #007acc;');
      router.replace('/permission');
    }
  },
  LATLNG_TO_ADDRESS(context, latlng) {
    latlngToAddress(
      latlng,
      (response) => {
        context.commit('LATLNG_TO_ADDRESS', response.data);
      },
      (error) => {
        console.log('%cactions.js line:74 error', 'color: #007acc;', error);
        context.commit(
          'LATLNG_TO_ADDRESS',
          this.currentAddress == this.SEARCHING_POSITIOND_TEXT || this.currentAddress.trim
            ? this.SEARCHING_POSITIOND_TEXT
            : this.currentAddress
        );
      }
    );
  },
  SIGNUP (context, payload) {
    console.log("SIGNUP actionjs line93")
    // payload가 user 정보가 담겨져있음
    console.log(payload)
    let result = false;
    signup(
      payload,
      (response) => {
        // const userdata = {
        //   email: payload.email,
        // }
        // var jsonObj = JSON.parse(response);
        console.log("mydata is" , response);
        context.commit('SIGNUP', payload);
        // 여기서 다시 SMTP 호출하고싶은경우?
        context.dispatch('SMTP', response.data);
        console.log("SIGNUP ACTIONSJS ACTIVATE")
        result = true;
      },
      (error) => {
        console.log("Signup Error" , error)
        result= false;
        console.log("SIGNUP actionjs line107")
      }
    );
    return result    
  },
  SMTP (context, payload){
    console.log("SMTP payload" , payload),
    smtp(
      payload.email,
      (response) => {
        console.log("SMTP response success" , response.data)
        context.commit("SMTP", response.data)
      },
      (error) => {
        console.log("SMTP ERROR" + error)
      }

    )

  },
  async GOOGLE_LOGIN(context, user) {
    let result = false;
    await login(
      user,
      (response) => {
        context.commit('GOOGLE_LOGIN', response.data.data);
        if (response.data.data === 'success') {
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          context.dispatch('GET_MEMBER_INFO', response.data['auth-token']);
          result = true;
        }
      },
      (error) => {
        console.log('%cactions.js line:13 error', 'color: #007acc;', error);
      }
    );
    return result;
  },
    // axios
    //   .post("https://i4a308.p.ssafy.io:8080/account/signup", payload)
    //   .then((response) => {
    //     console.log("회원가입 : " + response.data);
    //     store.dispatch("SMTP",payload);
    //   })
    //   .catch((response, error) => {
    //     console.log("잘못됐음" + response.status);
    //     console.log(error);
    //   });
    // }, 

    // SMTP: (store, payload) => {
    //   console.log(payload);
    //   axios
    //     .get('https://i4a308.p.ssafy.io:8080/account/smtp?email=' + payload.email)
    //     .then((response) => {
    //       console.log("smtp : " + response.data.length);
    //     })
    //     .catch((response, error) => {
    //       console.log("FAIL : " + response.status);
    //       console.log(error);
    //       alert("이메일 발송 실패: 유효하지 않은 이메일 입니다.");
    //       store.dispatch("DELETE",payload.email);
    //     });
    // },
};
