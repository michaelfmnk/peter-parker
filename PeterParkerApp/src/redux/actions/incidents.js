import {CALL_API} from '../../middleware/api';

export const GET_INCIDENTS = 'GET_INCIDENTS';


export const getReportedIncidents = (type, lat, lng) => ({
    type: GET_INCIDENTS,
    incidentType: type,
    [CALL_API]: {
        type: GET_INCIDENTS,
        endpoint: `/v1/incidents/${type}`,
        method: 'get',
        params: {
            lat, lng,
        },
    },
});
