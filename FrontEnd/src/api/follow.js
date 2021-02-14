import {createInstance} from './index.js';

const instance = createInstance();

function follwerList(area, success, fail){
    instance
        .get('rank/'+area)
        .then(success)
        .catch(fail);
}

function totalList(success, fail){
    instance
        .get('rank/total')
        .then(success)
        .catch(fail);
}

function userFindList(keyword, success, fail){
    instance
        .get('search/nickname',{params:keyword})
        .then(success)
        .catch(fail);
}

export{follwerList, totalList, userFindList}