import { createInstance } from './index.js';

const instance = createInstance();

function likeBoardList(email, success, fail) {
  instance
    .get('like', { params: { email } })
    .then(success)
    .catch(fail);
}

export { likeBoardList };
