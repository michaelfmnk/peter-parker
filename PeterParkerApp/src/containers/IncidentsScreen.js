import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {getReportedIncidents} from "../redux/actions/incidents";
import {Container, Content, List} from 'native-base';
import {connect} from "react-redux";
import Geolocation from '@react-native-community/geolocation';
import {selectIncidents} from "../redux/selectors/incidents";
import AppHeader from "../components/AppHeader";
import IncidentListItem from "../components/IncidentListItem";


class IncidentsScreen extends Component {

    componentDidMount() {
        this.getIncidentsAround()
    }

    getIncidentsAround() {
        Geolocation.getCurrentPosition(position => {
            const type = this.props.type.toLowerCase();
            this.props.getReportedIncidents(type, position.coords.latitude, position.coords.longitude)
        });
    }

    render() {
        return (
            <Container>
                <AppHeader title={`${this.props.type} Incidents`}/>
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
