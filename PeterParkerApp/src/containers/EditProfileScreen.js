import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {StyleSheet} from 'react-native';
import {Container, Content} from 'native-base';
import FullLineButton from '../components/FullLineButton';
import {logOut} from '../redux/actions/session';

class EditProfileScreen extends Component {
    render() {
        return (
            <Container>
                <Content style={styles.content}>
                    <FullLineButton
                        onPress={this.props.logOut}
                        text="Log Out"
                        textColor="red"
                    />
                </Content>
            </Container>
        );
    }
}

EditProfileScreen.propTypes = {
    logOut: PropTypes.func,
};

const styles = StyleSheet.create({
    content: {
        backgroundColor: '#f0eff3',
    },
});

const mapDispatchToProps = {
    logOut,
};

export default connect(undefined, mapDispatchToProps)(EditProfileScreen);
