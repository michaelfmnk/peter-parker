import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {createAppContainer} from 'react-navigation';
import MainNavigator from "./MainNavigator";
import {isLoggedIn} from "../redux/selectors/session";
import navigation from "../services/navigation";
import {connect} from "react-redux";

class AppNavigator extends Component {
    static propTypes = {
        isLoggedIn: PropTypes.bool,
    };

    render() {
        const Layout = createAppContainer(MainNavigator(this.props.isLoggedIn));
        return (
            <Layout
                onNavigationStateChange={(oldState, newState) =>
                    navigation.getCurrentRouteName(newState)
                }
                ref={navigatorRef => {
                    navigation.setTopLevelNavigator(navigatorRef);
                }}
            />
        )
    }

}


const mapStateToProps = state => ({
    isLoggedIn: isLoggedIn(state),
});

export default connect(mapStateToProps)(AppNavigator)
