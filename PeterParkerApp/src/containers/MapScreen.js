import React, {Component} from 'react';
import {Container} from 'native-base';
import {StyleSheet} from 'react-native';
import MapView from "react-native-maps";
import AppHeader from "../components/AppHeader";


class MapScreen extends Component {

    render() {
        return (
            <Container>
                <AppHeader title="Incidents Map"/>
                <MapView
                    style={styles.map}
                    showsBuildings={false}
                    showsUserLocation={true}
                    initialRegion={{
                        latitude: 37.78825,
                        longitude: -122.4324,
                        latitudeDelta: 0.0922,
                        longitudeDelta: 0.0421,
                    }}
                />
            </Container>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    map: {
        flex: 1,
    },
});
export default MapScreen;
