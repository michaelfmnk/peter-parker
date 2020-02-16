import {takeLatest} from 'redux-saga/effects';
import navigation from "../services/navigation";
import {failAction, successAction} from "../redux/actions/types";
import {LOG_IN, LOG_OUT} from "../redux/actions/session";
import {Alert} from "react-native";

function watchLogin() {
    navigation.navigate('MainReporterScreen');
}

function watchLogout() {
    navigation.navigate('AuthScreen');
}

function prepareFailedLoginMessage(status) {
    let message = 'There was a problem with server. Please, try again later.';
    if ((status + '').startsWith('4')) {
        message = 'Password or login is wrong';
    }
    return message;
}

function* watchFailedLogin(action) {
    const status = action.error.status;
    const message = prepareFailedLoginMessage(status);
    yield new Promise(resolve => Alert.alert(
        message,
        '',
        [{text: "OK", onPress: resolve}],
    ));
}

export default function* watchAuth() {
    yield takeLatest(successAction(LOG_IN), watchLogin);
    yield takeLatest(failAction(LOG_IN), watchFailedLogin);
    yield takeLatest(LOG_OUT, watchLogout);
}

