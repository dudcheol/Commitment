import { createInstance } from './index.js';

const instance = createInstance();

function commitrate(email, success, fail) {
  instance
    .get('badge/commitrate', { params: { email } })
    .then(success)
    .catch(fail);
}

export { commitrate };
