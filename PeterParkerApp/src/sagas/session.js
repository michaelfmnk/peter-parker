import {takeLatest} from 'redux-saga/effects';
import navigation from "../services/navigation";
import {successAction} from "../redux/actions/types";
import {LOG_IN, LOG_OUT} from "../redux/actions/session";

function watchLogin() {
    navigation.navigate('MainReporterScreen');
}

function watchLogout() {
    navigation.navigate('AuthScreen');
}

export default function* watchAuth() {
    yield takeLatest(successAction(LOG_IN), watchLogin);
    yield takeLatest(LOG_OUT, watchLogout);
}

