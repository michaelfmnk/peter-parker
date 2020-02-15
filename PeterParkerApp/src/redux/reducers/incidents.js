import {successAction} from '../actions/types';
import {GET_REPORTED_INCIDENTS} from '../actions/incidents';

const initialState = {
    reported: {
        data: [],
        total: 0,
    },
    received: {
        data: [],
        total: 0,
    },
};

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case successAction(GET_REPORTED_INCIDENTS): {
            return {
                ...state,
                reported: action.response.data,
            };
        }
        default: {
            return state;
        }
    }
}

