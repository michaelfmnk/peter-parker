import React, {Component} from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import PropTypes from 'prop-types';

class FullLineButton extends Component {

    constructor(props) {
        super(props);
        this.customStyles = styles(props.textColor);
    }

    render() {
        return (
            <TouchableOpacity onPress={this.props.onPress} style={this.props.style}>
                <View style={this.customStyles.line}>
                    <Text style={this.customStyles.text}>{this.props.text}</Text>
                </View>
            </TouchableOpacity>
        );
    }
}

FullLineButton.propTypes = {
    textColor: PropTypes.string,
    text: PropTypes.string,
    style: PropTypes.object,
    onPress: PropTypes.func,
};
FullLineButton.defaultProps = {
    text: 'Button',
    style: {},
    onPress: () => {
    },
};

const styles = (textColor) => StyleSheet.create({
    line: {
        backgroundColor: '#ffffff',
        borderTopWidth: 1,
        borderTopColor: '#DCE1E5',
        borderBottomColor: '#DCE1E5',
        borderBottomWidth: 1,
        height: 40,
    },
    text: {
        textAlign: 'center',
        lineHeight: 40,
        fontSize: 18,
        color: textColor,
    },
});

export default FullLineButton;
