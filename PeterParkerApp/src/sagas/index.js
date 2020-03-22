import {all, fork} from 'redux-saga/effects';
import session from './session';
import logger from './logger';

export function* rootSaga() {
    yield all([
        fork(session),
        fork(logger),
    ]);
}
