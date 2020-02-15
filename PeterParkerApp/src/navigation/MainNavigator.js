import {createSwitchNavigator} from 'react-navigation';
import AuthScreen from '../containers/AuthScreen';
import MapScreen from "../containers/MapScreen";
import nav from '../services/navigation'
import ReportedIncidentsScreen from "../containers/ReportedIncidentsScreen";
import {createBottomTabNavigator} from "react-navigation-tabs";

const MainNavigator = isLoggedIn => createSwitchNavigator({
    AuthScreen: AuthScreen,
    MapScreen: MapScreen,
    MainReporterScreen: createBottomTabNavigator({
        'Reported Cases': {screen: ReportedIncidentsScreen},
        'Own Cases': {screen: MapScreen},
        'Settings': {screen: MapScreen},
    }, {
        initialRouteName: 'Reported Cases'
    }),
}, {
    initialRouteName: isLoggedIn ? 'MainReporterScreen' : 'AuthScreen',
});

nav.setTopLevelNavigator(navigator);

export default MainNavigator;
