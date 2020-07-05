import React, {Component} from 'react';
import {Text} from 'react-native';
import {Provider} from 'react-redux';
import {PersistGate} from 'redux-persist/integration/react';
import {persistor, store} from './redux/store';
import AppNavigator from './navigation';
import {SafeAreaProvider} from 'react-native-safe-area-context';
import PushNotificationIOS from "@react-native-community/push-notification-ios";
import moment from 'moment';

export default class App extends Component {
    render() {
        let localNotification = PushNotificationIOS.scheduleLocalNotification({
            alertBody: 'Your cas has been noticed parked wrong!',
            isSilent: false,
            alertTitle: 'Parking Alert',
            fireDate: moment().valueOf() + 1000 * 5
        });
        return (
            <Provider store={store}>
                <PersistGate persistor={persistor} loading={<Text>Loading..</Text>}>
                    <SafeAreaProvider>
                        <AppNavigator/>
                    </SafeAreaProvider>
                </PersistGate>
            </Provider>
        );
    }
}

