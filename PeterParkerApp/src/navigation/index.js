import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {createAppContainer} from 'react-navigation';
import MainNavigator from './MainNavigator';
import {selectIsLoggedIn} from '../redux/selectors/session';
import navigation from '../services/navigation';
import {connect} from 'react-redux';

class AppNavigator extends Component {
    static propTypes = {
        isLoggedIn: PropTypes.bool,
    };

    onNavigationStateChange = (_, newState) => navigation.getCurrentRouteName(newState);

    render() {
        const Layout = createAppContainer(MainNavigator(this.props.isLoggedIn));
        return (
            <Layout
                onNavigationStateChange={this.onNavigationStateChange}
                ref={navigatorRef => {
                    navigation.setTopLevelNavigator(navigatorRef);
                }}
            />
        )
    }

}


const mapStateToProps = state => ({
    isLoggedIn: selectIsLoggedIn(state),
});

export default connect(mapStateToProps)(AppNavigator)
