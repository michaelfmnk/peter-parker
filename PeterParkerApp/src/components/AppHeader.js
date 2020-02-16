import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Body, Button, Header, Icon, Right, Title} from "native-base";


class AppHeader extends Component {


    render() {
        return (
            <Header>
                <Body>
                    <Title>{this.props.title}</Title>
                </Body>
                <Right>
                    <Button transparent>
                        <Icon name='add'/>
                    </Button>
                </Right>
            </Header>
        );
    }

}

AppHeader.propTypes = {
    title: PropTypes.string,
};

AppHeader.defaultProps = {
    title: 'Peter Parker',
};


export default AppHeader;
