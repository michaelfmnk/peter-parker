/* eslint-disable react/display-name */
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
import IncidentFormScreen from "../containers/IncidentFormScreen";

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

const BottomTabsNavigator = createBottomTabNavigator({
    'Reported Cases': {
        screen: props => (<IncidentsScreen {...props} type="Reported"/>),
    },
    'Own Cases': {
        screen: props => (<IncidentsScreen {...props} type="Own"/>),
    },
    'Settings': {screen: SettingsNavigator},
}, {
    initialRouteName: 'Reported Cases',
    navigationOptions: {
        headerShown: false,
    },
});

const MainReporterScreenNavigator = createStackNavigator({
    BottomTabsNavigator: BottomTabsNavigator,
    IncidentFormScreen: {screen: IncidentFormScreen, navigationOptions: {title: 'Report Incident'}},
}, {
    initialRouteName: 'BottomTabsNavigator',
});

const MainNavigator = isLoggedIn => createSwitchNavigator({
    AuthScreen: AuthScreen,
    MapScreen: MapScreen,
    MainReporterScreen: MainReporterScreenNavigator,
}, {
    initialRouteName: isLoggedIn ? 'MainReporterScreen' : 'AuthScreen',
});

nav.setTopLevelNavigator(MainNavigator);

export default MainNavigator;
