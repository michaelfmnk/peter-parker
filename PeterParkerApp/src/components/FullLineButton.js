import React, {Component} from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import PropTypes from 'prop-types';

class FullLineButton extends Component {
  render() {
    return (
        <TouchableOpacity onPress={this.props.onPress}>
          <View style={styles.line}>
            <Text style={styles.text}>{this.props.text}</Text>
          </View>
        </TouchableOpacity>
    );
  }
}

FullLineButton.propTypes = {
  text: PropTypes.string,
  onPress: PropTypes.func,
};
FullLineButton.defaultProps = {
  text: 'Button',
  onPress: () => {
  },
};

const styles = StyleSheet.create({
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
    color: '#de5246',
  },
});

export default FullLineButton;