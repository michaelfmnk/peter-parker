import {CALL_API} from "../../middleware/api";

export const GET_REPORTED_INCIDENTS = 'GET_REPORTED_INCIDENTS';


export const getReportedIncidents = (type, lat, lng) => ({
    type: GET_REPORTED_INCIDENTS,
    [CALL_API]: {
        type: GET_REPORTED_INCIDENTS,
        endpoint: `/v1/incidents/${type}`,
        method: 'get',
        params: {
            lat, lng
        }
    },
});
