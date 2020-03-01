import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {getReportedIncidents} from '../redux/actions/incidents';
import {Container, Content, List} from 'native-base';
import {connect} from 'react-redux';
import Geolocation from '@react-native-community/geolocation';
import {selectIncidents} from '../redux/selectors/incidents';
import AppHeader from '../components/AppHeader';
import IncidentListItem from '../components/IncidentListItem';

class IncidentsScreen extends Component {
    namesMapping = {
        'Own': 'Own Cases',
        'Reported': 'Reported ',
    };

    componentDidMount() {
        this.getIncidentsAround();
    }

    openIncidentForm = () => {
        console.log(this.props);
        this.props.navigation.navigate('IncidentFormScreen');
    };

    getIncidentsAround() {
        Geolocation.getCurrentPosition(position => {
            const type = this.props.type.toLowerCase();
            this.props.getReportedIncidents(type, position.coords.latitude,
                position.coords.longitude);
        });
    }

    render() {
        return (
            <Container>
                <AppHeader title={this.namesMapping[this.props.type]} onPress={this.openIncidentForm}/>
                <Content>
                    <List>
                        {
                            this.props.incidents.map(incident => (
                                <IncidentListItem
                                    incident={incident}
                                    key={incident.id}
                                />
                            ))
                        }
                    </List>
                </Content>
            </Container>

        );
    }
}

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