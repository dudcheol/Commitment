import {createInstance} from './index.js';

const instance = createInstance();

function editProfileImg(file, success, fail){
    instance
        .post('profile/upload', file)
        .then(success)
        .catch(fail);
}
export{editProfileImg}