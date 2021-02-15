import {createInstance} from './index.js';

const instance = createInstance();

function timelineInfo(email, success, fail){
    instance
        .get('timeline', {params:{email}})
        .then(success)
        .catch(fail);
}
export{timelineInfo}