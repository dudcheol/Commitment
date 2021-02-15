import { createInstance } from './index.js';

const instance = createInstance();

function likeBoardList(email, page, size, success, fail) {
  instance
    .get('like', { params: { email, page, size } })
    .then(success)
    .catch(fail);
}

function like(email, snsId, writer, success, fail) {
  instance
    .post('like', { email, snsId, writer })
    .then(success)
    .catch(fail);
}

export { likeBoardList, like };
