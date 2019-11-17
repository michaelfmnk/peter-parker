import {CALL_API} from '../../middleware/api';

export const LOG_IN = 'LOG_IN';
export const SIGN_UP = 'SIGN_UP';

export const logIn = () => ({
    type: LOG_IN,
    [CALL_API]: {
        type: LOG_IN,
        endpoint: '/user-api/v1/auth/login',
        method: 'post',
    },
});


export const signUp = (phone, password) => ({
    type: SIGN_UP,
    [CALL_API]: {
        type: SIGN_UP,
        endpoint: '/user-api/v1/auth/sign-up',
        method: 'post',
        body: {
            phone,
            password,
        },
    },
});
