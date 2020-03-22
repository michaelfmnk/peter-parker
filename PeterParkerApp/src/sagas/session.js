import {call, put, takeLatest} from 'redux-saga/effects';
import navigation from '../services/navigation';
import {failAction, successAction} from '../redux/actions/types';
import {LOG_IN, LOG_OUT} from '../redux/actions/session';
import {Alert} from 'react-native';
import {
    CREATE_INCIDENT,
    getReportedIncidents,
} from '../redux/actions/incidents';
import Geolocation from '@react-native-community/geolocation';

function watchLogin() {
    navigation.navigate('MainReporterScreen');
}

function watchLogout() {
    navigation.navigate('AuthScreen');
}

function prepareFailedLoginMessage(status) {
    let message = 'There was a problem with server. Please, try again later.';
    if ((`${status}`).startsWith('4')) {
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

const getUserLocation = () => new Promise(resolve => {
    Geolocation.getCurrentPosition(position => resolve(position.coords));
});

function goBack() {
    navigation.goBack();
}

function* navigateBack() {
    const location = yield call(getUserLocation);
    const {latitude, longitude} = location;
    yield put(getReportedIncidents('reported', latitude, longitude));
    yield put(getReportedIncidents('own', latitude, longitude));
}

export default function* watchAuth() {
    yield takeLatest(successAction(LOG_IN), watchLogin);
    yield takeLatest(failAction(LOG_IN), watchFailedLogin);
    yield takeLatest(LOG_OUT, watchLogout);
    yield takeLatest(successAction(CREATE_INCIDENT), goBack);
    yield takeLatest(successAction(CREATE_INCIDENT), navigateBack);
}

