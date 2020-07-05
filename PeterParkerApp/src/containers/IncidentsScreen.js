import React, {Component} from 'react';
import {ActionSheetIOS, StyleSheet, View} from 'react-native';
import PropTypes from 'prop-types';
import {getReportedIncidents} from '../redux/actions/incidents';
import {Container, Content, List} from 'native-base';
import {connect} from 'react-redux';
import Geolocation from '@react-native-community/geolocation';
import {selectIncidents} from '../redux/selectors/incidents';
import AppHeader from '../components/AppHeader';
import IncidentListItem from '../components/IncidentListItem';
import SegmentedControlTab from "react-native-segmented-control-tab";
import MapView from "react-native-maps";
import MapMarker from "react-native-maps/lib/components/MapMarker";


const TABS = {
    LIST: 0,
    MAP: 1,
};

class IncidentsScreen extends Component {

    state = {
        selectedIndex: TABS.LIST,
        coords: {
            latitude: 37.78825,
            longitude: -122.4324,
            latitudeDelta: 0.0122,
            longitudeDelta: 0.0221,
        },
        incidentSelected: false,
    };

    componentDidMount() {
        this.getIncidentsAround();
        this.setInitialLocation();
    }

    getIncidentsAround() {
        Geolocation.getCurrentPosition(position => {
            const type = this.props.type.toLowerCase();
            this.props.getReportedIncidents(type, position.coords.latitude,
                position.coords.longitude);
        });
    }

    openIncidentForm = () => {
        console.log(this.props);
        this.props.navigation.navigate('IncidentFormScreen');
    };

    namesMapping = {
        'Own': 'Own Cases',
        'Reported': 'Reported ',
    };

    handleTabChange = index => {
        this.setState({
            selectedIndex: index,
            incidentSelected: index === TABS.LIST,
        });
    }

    isListViewSelected = () => this.state.selectedIndex === 0;
    isMapViewSelected = () => this.state.selectedIndex === 1;

    setInitialLocation = () => {
        Geolocation.getCurrentPosition(position => {
            this.setState({
                coords: {
                    ...this.state.coords,
                    ...position.coords,
                }
            })
        });
    }

    createMapIncidentTitle = (incident) => {
        const input = incident.description;
        const limit = 30;
        if (input.length > limit) return input.substring(0, limit) + '...';
        else return input;
    }

    onListItemPressed = (incident) => () => {
        this.setState({
            selectedIndex: TABS.MAP,
            coords: {
                ...this.state.coords,
                latitude: incident.location.lat,
                longitude: incident.location.lng,
            },
            incidentSelected: true,
        });
    }

    onListItemLongPressed = (incident) => () => {
        ActionSheetIOS.showActionSheetWithOptions({
            options: ["Cancel", "Mark Resolved", "Delete"],
            destructiveButtonIndex: 2,
            cancelButtonIndex: 0
        }, index => {
        })
    }

    render() {
        return (
            <Container>
                <AppHeader title={this.namesMapping[this.props.type]} onPress={this.openIncidentForm}/>
                <SegmentedControlTab
                    tabsContainerStyle={{margin: 20, position: 'absolute', top: 100, zIndex: 100}}
                    values={['List', 'Map']}
                    selectedIndex={this.state.selectedIndex}
                    onTabPress={this.handleTabChange}
                />
                {
                    this.isListViewSelected() && (<Content>


                            <List>
                                <View key={-1} style={{height: 40}}>
                                </View>
                                {
                                    this.props.incidents.map(incident => (
                                        <IncidentListItem
                                            onPress={this.onListItemPressed(incident)}
                                            onLongPress={this.onListItemLongPressed(incident)}
                                            incident={incident}
                                            key={incident.id}
                                        />
                                    ))
                                }

                            </List>

                        </Content>
                    )
                }

                {
                    this.isMapViewSelected() && (
                        <MapView
                            ref={ref => {
                                this.map = ref;
                            }}
                            provider="google"
                            style={styles.map}
                            showsBuildings={false}
                            showsUserLocation={true}
                            showsMyLocationButton={true}
                            liteMode={true}
                            showsCompass={true}
                            initialRegion={this.state.coords}
                            onMapReady={() => {
                                !this.state.incidentSelected && this.props.incidents.length > 0 && this.map.fitToSuppliedMarkers(this.props.incidents.map(it => it.id + ''),
                                    {
                                        edgePadding:
                                            {
                                                top: 50,
                                                right: 50,
                                                bottom: 50,
                                                left: 50
                                            }

                                    })
                            }}>
                            {
                                this.props.incidents.map(incident => (
                                    <MapMarker
                                        key={incident.id}
                                        title={this.createMapIncidentTitle(incident)}
                                        description={incident.id + ''}
                                        identifier={incident.id + ''}
                                        coordinate={{latitude: incident.location.lat, longitude: incident.location.lng}}
                                    />
                                ))
                            }
                        </MapView>
                    )
                }
            </Container>

        );
    }
}

const styles = StyleSheet.create({
    map: {
        flex: 1,
    },
})
IncidentsScreen.propTypes = {
    navigation: PropTypes.object,
    getReportedIncidents: PropTypes.func,
    incidents: PropTypes.array,
    type: PropTypes.oneOf(['Own', 'Reported']),
};

IncidentsScreen.defaultProps = {
    incidents: [],
    type: 'Reported',
};

const mapStateToProps = (state, ownProps) => ({
    incidents: selectIncidents(state, ownProps.type.toLowerCase()),
});

const mapDispatchToProps = {
    getReportedIncidents
};

export default connect(mapStateToProps, mapDispatchToProps)(IncidentsScreen);
