import {successAction} from '../actions/types';
import {LOG_IN, LOG_OUT, PLATE_RECENTLY_UPDATED, SIGN_UP, UPDATE_PLATE} from '../actions/session';

const initialState = {
    waitingForCode: false,
    token: null,
    role: null,
    plateRecentlyUpdated: false,
    user: {},
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
        case successAction(UPDATE_PLATE): {
            return {
                ...state,
                user: {
                    ...state.user,
                    plateNumber: action.plateNumber,
                },
            };
        }
        case PLATE_RECENTLY_UPDATED: {
            return {
                ...state,
                plateRecentlyUpdated: action.value,
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

