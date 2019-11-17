import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {signUp} from '../redux/actions/session';
import {StyleSheet, View} from 'react-native';
import {Body, Button, Container, Content, Header, Input, Item, Text, Title} from 'native-base';


class AuthLoadingScreen extends Component {
    state = {
        phone: '',
        password: '',
    };

    onSubmit = () => {
        const {phone, password} = this.state;
        this.props.signUp(phone, password);
    };

    onPhoneChanged = (value) => {
        this.setState({phone: value});
    };

    onPasswordChanged = (value) => {
        this.setState({password: value});
    };

    render() {
        return (
            <Container>
                <Header>
                    <Body>
                        <Title>Login</Title>
                    </Body>
                </Header>
                <Content>
                    <Item>
                        <Input
                            placeholder="Phone"
                            value={this.state.phone}
                            onChangeText={this.onPhoneChanged}
                        />
                    </Item>
                    <Item>
                        <Input
                            placeholder="Password"
                            value={this.state.password}
                            onChangeText={this.onPasswordChanged}
                        />
                    </Item>
                    <View style={styles.submitBtnContainer}>
                        <Button dark style={styles.submitBtn} onPress={this.onSubmit}>
                            <Text>Submit</Text>
                        </Button>
                    </View>
                </Content>
            </Container>

        );
    }
}

AuthLoadingScreen.propTypes = {
    signUp: PropTypes.func,
};

const styles = StyleSheet.create({
    submitBtnContainer: {
        marginTop: 30,
        alignItems: 'center',
        flex: 1,
    },
    submitBtn: {
        width: '50%',
        justifyContent: 'center',
    },
});


const mapDispatchToProps = {
    signUp,
};


function mapStateToProps(state) {
    return {};
}

export default connect(mapStateToProps, mapDispatchToProps)(AuthLoadingScreen);
