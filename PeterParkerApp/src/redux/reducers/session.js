import {successAction} from '../actions/types';
import {LOG_IN, LOG_OUT, SIGN_UP} from '../actions/session';

const initialState = {
    waitingForCode: false,
    token: null,
    role: null,
};

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case successAction(SIGN_UP): {
            state.waitingForCode = true;
            return state;
        }
        case successAction(LOG_IN): {
            return {
                ...state,
                ...action.response.data,
            };
        }
        case LOG_OUT: {
            return initialState;
        }
        default: {
            return state;
        }
    }
}

