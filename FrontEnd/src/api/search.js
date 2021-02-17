import { createInstance } from './index.js';

const instance = createInstance();

function searchNickname(keyword, success, fail) {
    instance
      .get('/search/nickname', { params: {  keyword} })
      .then(success)
      .catch(fail);
  }
  export{searchNickname}