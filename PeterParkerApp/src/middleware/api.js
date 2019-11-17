import axios from 'axios';
import queryString from 'query-string';

import {failAction, SEND_REQUEST, startAction, successAction} from '../redux/actions/types';

axios.defaults.baseURL = "http://127.0.0.1";
axios.defaults.timeout = 30000;

const instance = axios.create({
    timeout: 30000,
    withCredentials: true,
});


const paramsSerializer = params => queryString.stringify(params);

export const callApi = (headers, method = 'get', endpoint, body, params, responseType = 'json') => instance({
    headers,
    url: endpoint,
    method,
    data: body,
    params,
    responseType,
    paramsSerializer,
});

// eslint-disable-next-line no-undef
export const CALL_API = Symbol('CALL_API');

function handleError(error, actionWrapper, dispatch, type) {
    return dispatch({
        type: failAction(type),
        response: error.response,
        error: (error.response && error.response.data) || 'Error happened during API call',
    });
}

export default store => next => (action) => {
    const callAPI = action[CALL_API];

    if (typeof callAPI === 'undefined') {
        return next(action);
    }
    const time = new Date().getTime();

    let {endpoint} = callAPI;
    const {
        type, method = 'get', body = {}, params, converter = response => response,
        responseType, submitFormRequest,
    } = callAPI;

    if (typeof endpoint === 'function') {
        endpoint = endpoint(store.getState());
    }

    if (typeof endpoint !== 'string') {
        throw new Error('Specify a string endpoint URL.');
    }

    if (typeof type !== 'string') {
        throw new Error('Expected action type to be string.');
    }

    const actionWith = (data) => {
        const finalAction = {
            ...action,
            ...data,
        };

        delete finalAction[CALL_API];

        return finalAction;
    };

    store.dispatch(actionWith({
        type: startAction(type),
        time,
    }));
    store.dispatch({
        type: SEND_REQUEST,
        requestType: type,
    });
    const headers = {};

    return callApi(headers, method, endpoint, body, params, responseType)
        .then(
            (response) => {
                store.dispatch(actionWith({
                    response: converter(response),
                    type: successAction(type),
                    time,
                }));
                return response;
            },
            error => handleError(error, actionWith, store.dispatch, type, time, submitFormRequest),
        );
};
