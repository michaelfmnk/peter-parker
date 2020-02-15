import {CALL_API} from '../../middleware/api';

export const LOG_IN = 'LOG_IN';
export const SIGN_UP = 'SIGN_UP';
export const LOG_OUT = 'LOG_OUT';


export const logOut = () => ({
    type: LOG_OUT,
});

export const logIn = (phone, password) => ({
    type: LOG_IN,
    [CALL_API]: {
        type: LOG_IN,
        endpoint: '/v1/auth/login',
        method: 'post',
        body: {
            phone,
            password,
        }
    },
});

export const signUp = (phone, password) => ({
    type: SIGN_UP,
    [CALL_API]: {
        type: SIGN_UP,
        endpoint: '/v1/auth/sign-up',
        method: 'post',
        body: {
            phone,
            password,
        },
    },
});
