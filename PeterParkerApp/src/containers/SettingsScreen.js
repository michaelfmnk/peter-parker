import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {StyleSheet} from 'react-native';

import {Container, Content} from 'native-base';
import PersonalInfoCard from '../components/PersonalInfoCard';
import {selectUserInfo} from '../redux/selectors/session';
import {connect} from 'react-redux';

class SettingsScreen extends Component {

  formatName = () => {
    let user = this.props.user;
    return `${user.firstName} ${user.lastName}`;
  };

  render() {
    return (
        <Container>
          <Content style={styles.content}>
            <PersonalInfoCard
                name={this.formatName()}
                phone={this.props.user.phone}
                onPress={() => this.props.navigation.navigate(
                    'EditProfileScreen')}
            />

          </Content>
        </Container>

    );
  }
}

SettingsScreen.propTypes = {
  navigation: PropTypes.object,
  user: PropTypes.object,
};

SettingsScreen.defaultProps = {
  user: {
    firstName: '',
    lastName: '',
  },
};

const styles = StyleSheet.create({
  content: {
    backgroundColor: '#f0eff3',
  },
});

const mapStateToProps = state => ({
  user: selectUserInfo(state),
});

export default connect(mapStateToProps)(SettingsScreen);