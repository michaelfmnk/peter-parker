import {CALL_API} from '../../middleware/api';

export const GET_INCIDENTS = 'GET_INCIDENTS';
export const CREATE_INCIDENT = 'CREATE_INCIDENT';
export const UPLOAD = 'UPLOAD';


export const getReportedIncidents = (type, lat, lng) => ({
    type: GET_INCIDENTS,
    incidentType: type,
    [CALL_API]: {
        type: GET_INCIDENTS,
        endpoint: `/v1/incidents/${type}`,
        method: 'get',
        params: {
            lat, lng,
            size: 10000,
        },
    },
});


export const createIncident = (desc, url, location) => ({
    type: CREATE_INCIDENT,
    [CALL_API]: {
        type: CREATE_INCIDENT,
        endpoint: `/v1/incidents`,
        method: 'post',
        body: {
            description: desc,
            photo: url,
            location,
        },
    },
});


export const upload = formData => ({
    type: UPLOAD,
    [CALL_API]: {
        type: UPLOAD,
        baseURL: 'https://api.imgur.com',
        endpoint: '/3/image/',
        method: 'post',
        body: formData,
        headers: {
            'Authorization': `Client-ID d8333afe2290277`
        },
    },
});
