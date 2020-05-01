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
import IncidentFormScreen from '../containers/IncidentFormScreen';
import Icon from 'react-native-vector-icons/SimpleLineIcons';

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
        navigationOptions: () => ({
            tabBarIcon: ({tintColor}) => <Icon name="location-pin" size={23}
                                               color={tintColor}/>,
        }),
    },
    'Own Cases': {
        screen: props => (<IncidentsScreen {...props} type="Own"/>),
        navigationOptions: () => ({
            tabBarIcon: ({tintColor}) => <Icon name="exclamation" size={23}
                                               color={tintColor}/>,
        }),
    },
    'Settings': {
        screen: SettingsNavigator,
        navigationOptions: () => ({
            tabBarIcon: ({tintColor}) => <Icon name="settings" size={23}
                                               color={tintColor}/>,
        }),
    },
}, {
    initialRouteName: 'Reported Cases',
    navigationOptions: {
        headerShown: false,
    },
});

const MainReporterScreenNavigator = createStackNavigator({
    BottomTabsNavigator: BottomTabsNavigator,
    IncidentFormScreen: {
        screen: IncidentFormScreen,
        navigationOptions: {title: 'Report Incident'},
    },
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
