import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {Button, Container, Input, Item, Text} from "native-base";
import {StyleSheet, View} from "react-native";
import ImagePicker from 'react-native-image-picker'
import {createIncident, upload} from '../redux/actions/incidents';
import Geolocation from "@react-native-community/geolocation";

class IncidentFormScreen extends Component {
    constructor(props) {
        super(props);
        this.setState({
            photo: '',
            description: '',
            uploading: false,
        });
    }

    handleChoosePhoto = () => {
        const options = {
            title: 'Select incident photo',
            maxWidth: 300,
            maxHeight: 300,
        };

        ImagePicker.showImagePicker(options, response => {
            if (response.data) {
                this.setState({uploading: true});
                this.uploadImage(response.data, url => this.setState({photo: url, uploading: false}));
            }
        });

    };

    uploadImage(base64, callback) {
        const r = new XMLHttpRequest();
        const d = new FormData();

        d.append('image', base64);

        r.open('POST', 'https://api.imgur.com/3/image/');
        r.setRequestHeader('Authorization', ` Client-ID d8333afe2290277`);
        r.onreadystatechange = function () {
            console.log(r.responseText);
            if (r.status === 200 && r.readyState === 4) {
                let res = JSON.parse(r.responseText);
                callback(res.data.link);
            }
        };
        r.send(d)

    };

    onDescriptionChanged = (value) => {
        this.setState({description: value});
    };

    onSubmit = () => {
        Geolocation.getCurrentPosition(position => {
            this.props.createIncident(this.state.description, this.state.photo, {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            });
        });

    };

    renderButtonText = () => {
        if (this.state.uploading) {
            return 'Uploading';
        }

        if (this.state.photo) {
            return 'Image Selected';
        }

        return 'Choose Photo';
    }

    render() {
        const submitPossible = this.state.uploading || !this.state.photo || !this.state.description;
        return (
            <Container>
                <View>
                    <Item>
                        <Input
                            placeholder="Description"
                            onChangeText={this.onDescriptionChanged}
                        />
                    </Item>

                    <Button onPress={this.handleChoosePhoto} disabled={this.state.uploading}>
                        <Text>{this.renderButtonText()}</Text>
                    </Button>
                    <View style={styles.submitBtnContainer}>
                        <Button onPress={this.onSubmit} disabled={submitPossible}>
                            <Text>Submit</Text>
                        </Button>
                    </View>
                </View>
            </Container>

        );
    }
}

IncidentFormScreen.propTypes = {
    createIncident: PropTypes.func,
}

const styles = StyleSheet.create({
    content: {
        backgroundColor: '#f0eff3',
    },
    submitBtnContainer: {
        marginTop: 30,
        alignItems: 'center',
        flex: 1,
    },
});

const mapStateToProps = {
    createIncident,
    upload,
};

export default connect(undefined, mapStateToProps)(IncidentFormScreen);
