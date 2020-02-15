import {CALL_API} from "../../middleware/api";

export const GET_REPORTED_INCIDENTS = 'GET_REPORTED_INCIDENTS';


// retrieves reported incidents by current user
export const getReportedIncidents = (lat, lng) => ({
    type: GET_REPORTED_INCIDENTS,
    [CALL_API]: {
        type: GET_REPORTED_INCIDENTS,
        endpoint: '/v1/incidents',
        method: 'get',
        params: {
            lat, lng
        }
    },
});
