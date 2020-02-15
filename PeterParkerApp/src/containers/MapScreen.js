import React, {Component} from 'react';
import {StyleSheet} from 'react-native';
import {Body, Container, Header, Title} from 'native-base';
import MapView from "react-native-maps";


class MapScreen extends Component {

    render() {
        return (
            <Container>
                <Header>
                    <Body>
                        <Title>Peter Parker</Title>
                    </Body>
                </Header>

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
