import {createSwitchNavigator} from 'react-navigation';
import AuthLoadingScreen from '../containers/AuthLoadingScreen';

export default createSwitchNavigator({
    AuthLoading: AuthLoadingScreen,
}, {
    initialRouteName: 'AuthLoading',
});
