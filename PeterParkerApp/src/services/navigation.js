import {NavigationActions, StackActions} from 'react-navigation';
import {store} from '../redux/store';
import {setCurrentRouteName} from '../redux/actions/base';

let _navigator;

function getCurrentRouteName(navState) {
    if (Object.prototype.hasOwnProperty.call(navState, 'index')) {
        getCurrentRouteName(navState.routes[navState.index]);
    } else {
        store.dispatch(setCurrentRouteName(navState.routeName));
    }
}

function setTopLevelNavigator(navigatorRef) {
    _navigator = navigatorRef;
}

function navigate(routeName, params) {
    _navigator.dispatch(
        NavigationActions.navigate({
            routeName,
            params,
        }),
    );
}

function navigateWithStackReset(routeName, params) {
    _navigator.dispatch(StackActions.popToTop());
    navigate(routeName, params);
}

function goBack() {
    _navigator.dispatch(NavigationActions.back());
}

export default {
    navigate,
    navigateWithStackReset,
    goBack,
    setTopLevelNavigator,
    getCurrentRouteName,
};
