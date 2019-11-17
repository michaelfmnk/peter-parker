import {successAction} from '../actions/types';
import {SIGN_UP} from '../actions/session';


const initialState = {
    waitingForCode: false,
};

export default function (state = initialState, action) {
    switch (action.type) {
        case successAction(SIGN_UP): {
            alert("SIGN_UP");
            state.waitingForCode = true;
            return state;
        }

        default: {
            return state;
        }
    }
}
