import React, {Component} from 'react';
import {Text} from 'react-native';
import {Provider} from 'react-redux';
import {PersistGate} from 'redux-persist/integration/react';
import {persistor, store} from './redux/store';
import AppNavigator from './navigation';
import {SafeAreaProvider} from 'react-native-safe-area-context';


export default class App extends Component {
    render() {
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

