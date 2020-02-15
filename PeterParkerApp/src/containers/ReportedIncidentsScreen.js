import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {getReportedIncidents} from "../redux/actions/incidents";
import {Body, Container, Content, Header, List, ListItem, Text, Title} from 'native-base';
import {connect} from "react-redux";
import Geolocation from '@react-native-community/geolocation';


class ReportedIncidentsScreen extends Component {

    componentDidMount() {
        this.getIncidentsAround()
    }

    getIncidentsAround() {
        Geolocation.getCurrentPosition(position => {
            this.props.getReportedIncidents(position.coords.latitude, position.coords.longitude)
        });
    }

    render() {
        return (
            <Container>
                <Header>
                    <Body>
                        <Title>Reported Incidents</Title>
                    </Body>
                </Header>
                <Content>
                    <List>
                        <ListItem>
                            <Text>Hello</Text>
                        </ListItem>
                        <ListItem>
                            <Text>Hello</Text>
                        </ListItem>
                    </List>

                </Content>


            </Container>

        );
    }
}

ReportedIncidentsScreen.propTypes = {
    getReportedIncidents: PropTypes.func,
};

const mapStateToProps = state => ({});

const mapDispatchToProps = {
    getReportedIncidents
};

export default connect(mapStateToProps, mapDispatchToProps)(ReportedIncidentsScreen);
