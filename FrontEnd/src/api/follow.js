import {createInstance} from './index.js';

const instance = createInstance();

function searchFollowers(email, success, fail){
    instance
        .get('profile/follower',{params:{email}})
        .then(success)
        .catch(fail);
}

function searchFollowings(email, success, fail){
    instance
        .get('profile/following',{params:{email}})
        .then(success)
        .catch(fail);
}

function followerCount(email, success, fail) {
    instance
      .get('profile/followerCnt', { params: { email } })
      .then(success)
      .catch(fail);
  }

export{searchFollowers, searchFollowings, followerCount}