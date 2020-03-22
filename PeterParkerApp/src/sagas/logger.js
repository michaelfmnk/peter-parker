import {takeEvery} from 'redux-saga/effects';
import {Alert} from 'react-native';

function* logError(action) {
  yield new Promise(resolve => Alert.alert(
      'action' + JSON.stringify(action),
      '',
      [{text: 'OK', onPress: resolve}],
  ));

}

export default function* () {
  yield takeEvery((action) => action.type.startsWith('FAILED_'), logError);
}