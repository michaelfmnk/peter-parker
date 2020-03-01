import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Image, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {parsePhoneNumberFromString} from 'libphonenumber-js';

class PersonalInfoCard extends Component {
    parsePhone = () => {
        const rawPhone = this.props.phone;
        if (!rawPhone) {
            return '';
        }
        const parsedPhone = parsePhoneNumberFromString(rawPhone);
        if (!parsedPhone) {
            return '';
        }

        return parsedPhone.formatInternational();
    };

    render() {
        return (
            <TouchableOpacity onPress={this.props.onPress}>
                <View style={styles.container}>
                    <Image
                        style={styles.avatar}
                        source={{uri: 'https://www.fakepersongenerator.com/Face/male/male1084485148713.jpg'}}
                    />
                    <View style={styles.info}>
                        <Text style={styles.name}>{this.props.name}</Text>
                        <Text style={styles.phone}>{this.parsePhone()}</Text>

                    </View>

                </View>
            </TouchableOpacity>
        );
    }
}

PersonalInfoCard.propTypes = {
    onPress: PropTypes.func,
    name: PropTypes.string,
    phone: PropTypes.string,
};

const avatarSize = 75;
const styles = StyleSheet.create({
    info: {
        flex: 40,
        paddingLeft: 20,
        paddingTop: 7,
    },
    name: {
        fontSize: 18,
    },
    phone: {
        paddingTop: 3,
        fontSize: 12,
    },
    avatar: {
        width: avatarSize,
        height: avatarSize,
        borderRadius: avatarSize,
        flex: 10,
    },
    container: {
        backgroundColor: '#ffffff',
        paddingTop: 10,
        paddingBottom: 10,
        paddingLeft: 20,
        display: 'flex',
        flexDirection: 'row',
    },
});

export default PersonalInfoCard;
