import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {StyleSheet} from 'react-native';
import {Container, Content, Icon, Input, Item, Label} from 'native-base';
import FullLineButton from '../components/FullLineButton';
import {logOut, updatePlateNumber} from '../redux/actions/session';
import {selectPlateNumber, selectPlateRecentlyUpdated} from '../redux/selectors/session';

class EditProfileScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            plateNumber: this.props.plateNumber,
        };
    }

    onPlateChanged = (value) => {
        if (/^[A-Za-z0-9]{0,8}$/.test(value)) {
            this.setState({
                plateNumber: value,
            });
        }
    };

    save = () => {
        this.props.updatePlateNumber(this.state.plateNumber);
    };

    render() {
        return (
            <Container>
                <Content style={styles.content}>

                    <Item
                        style={styles.plateNumberInput}
                        inlineLabel>
                        <Label style={{paddingLeft: 15}}>Plate Number</Label>
                        <Input value={this.state.plateNumber} onChangeText={this.onPlateChanged}/>
                        {this.props.plateRecentlyUpdated && <Icon name='checkmark-circle'/>}
                    </Item>

                    <FullLineButton
                        style={{marginTop: 10}}
                        onPress={this.save}
                        text="Save"
                        textColor="black"
                    />

                    <FullLineButton
                        style={{marginTop: 10}}
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
    updatePlateNumber: PropTypes.func,
    plateNumber: PropTypes.string,
    plateRecentlyUpdated: PropTypes.bool,
};

const styles = StyleSheet.create({
    content: {
        backgroundColor: '#f0eff3',
    },
    plateNumberInput: {
        backgroundColor: 'white',
    },
});

const mapDispatchToProps = {
    logOut,
    updatePlateNumber,
};

const mapStateToProps = state => ({
    plateNumber: selectPlateNumber(state),
    plateRecentlyUpdated: selectPlateRecentlyUpdated(state),
});

export default connect(mapStateToProps, mapDispatchToProps)(EditProfileScreen);
