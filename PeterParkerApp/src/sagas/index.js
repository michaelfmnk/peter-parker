import {all, fork} from 'redux-saga/effects';
import session from "./session";

export function* rootSaga() {
    yield all([
        fork(session)
    ]);
}
