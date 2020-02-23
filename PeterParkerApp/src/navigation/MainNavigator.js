import React from 'react';
import {createStackNavigator} from 'react-navigation-stack';
import {createSwitchNavigator} from 'react-navigation';
import AuthScreen from '../containers/AuthScreen';
import MapScreen from '../containers/MapScreen';
import nav from '../services/navigation';
import {createBottomTabNavigator} from 'react-navigation-tabs';
import IncidentsScreen from '../containers/IncidentsScreen';
import SettingsScreen from '../containers/SettingsScreen';
import EditProfileScreen from '../containers/EditProfileScreen';

const SettingsNavigator = createStackNavigator({
    SettingsOverviewScreen: {
        screen: SettingsScreen,
        navigationOptions: {title: 'Settings'},
    },
    EditProfileScreen: {
        screen: EditProfileScreen,
        navigationOptions: {title: 'Edit Profile'},
    },
}, {
    initialRouteName: 'SettingsOverviewScreen',
});

const MainNavigator = isLoggedIn => createSwitchNavigator({
    AuthScreen: AuthScreen,
    MapScreen: MapScreen,
    MainReporterScreen: createBottomTabNavigator({
        'Reported Cases': {
            screen: props => (<IncidentsScreen {...props} type="Reported"/>),
        },
        'Own Cases': {
            screen: props => (<IncidentsScreen {...props} type="Own"/>),
        },
        'Settings': {screen: SettingsNavigator},
    }, {
        initialRouteName: 'Reported Cases',
    }),
}, {
    initialRouteName: isLoggedIn ? 'MainReporterScreen' : 'AuthScreen',
});

nav.setTopLevelNavigator(navigator);

export default MainNavigator;
