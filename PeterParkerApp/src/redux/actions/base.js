export const SET_CURRENT_ROUTE_NAME = 'SET_CURRENT_ROUTE_NAME';

export const setCurrentRouteName = name => {
    return {
        type: SET_CURRENT_ROUTE_NAME,
        data: name,
    };
};
