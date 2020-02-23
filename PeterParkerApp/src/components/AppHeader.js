import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Body, Button, Header, Icon, Left, Right, Title} from 'native-base';

class AppHeader extends Component {
  render() {
    return (
        <Header>
          <Left/>
          <Body>
            <Title>{this.props.title}</Title>
          </Body>
          <Right>
            {
              this.props.addButton && (
                  <Button transparent>
                    <Icon name='add'/>
                  </Button>
              )
            }
          </Right>
        </Header>
    );
  }

}

AppHeader.propTypes = {
  title: PropTypes.string,
  addButton: PropTypes.bool,
};

AppHeader.defaultProps = {
  title: 'Peter Parker',
  addButton: true,
};


export default AppHeader;
