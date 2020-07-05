import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {ListItem, Text} from 'native-base';
import {Image, StyleSheet, View} from 'react-native';

class IncidentListItem extends Component {
    round(num) {
        return Math.round(num * 100 + Number.EPSILON) / 100;
    }

    render() {
        const incident = this.props.incident;

        return (
            <ListItem button onPress={this.props.onPress} onLongPress={this.props.onLongPress}>
                <View style={styles.textColumn}>
                    <Text style={styles.descriptionText}>{incident.description}</Text>
                    <Text style={styles.distanceText}>{`${this.round(incident.distance)} km away`}</Text>
                </View>
                <Image
                    source={{uri: incident.photo}}
                    resizeMode="contain"
                    style={styles.image}
                />
            </ListItem>
        )
    }
}

IncidentListItem.propTypes = {
    incident: PropTypes.object,
    onPress: PropTypes.func,
    onLongPress: PropTypes.func,
};

IncidentListItem.defaultProps = {
    onPress: () => {
    },
};

const styles = StyleSheet.create({
    descriptionText: {
        height: 22,
        marginRight: 'auto',
    },
    distanceText: {
        height: 19,
        fontSize: 10,
        marginRight: 'auto',
    },
    textColumn: {
        width: 133,
        marginBottom: 68
    },
    image: {
        width: 126,
        height: 109,
        marginLeft: 120,
    }
});

export default IncidentListItem;
