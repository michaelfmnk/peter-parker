export const isLoggedIn = state => !!state.session.token;
export const getToken = state => state.session.token;

