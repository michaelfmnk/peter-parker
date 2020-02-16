import {successAction} from '../actions/types';
import {GET_REPORTED_INCIDENTS} from '../actions/incidents';

const initialState = {
    reported: {
        data: [],
        total: 0,
        page: 0,
        size: 0,
    },
    own: {
        data: [],
        total: 0,
        page: 0,
        size: 0,
    },
};

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case successAction(GET_REPORTED_INCIDENTS): {
            return {
                ...state,
                reported: {
                    data: action.response.data.content,
                    total: action.response.data.total,
                    page: action.response.data.page,
                    size: action.response.data.size,
                },
            };
        }
        default: {
            return state;
        }
    }
}

