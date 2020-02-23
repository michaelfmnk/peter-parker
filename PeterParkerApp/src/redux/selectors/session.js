export const selectIsLoggedIn = state => !!state.session.token;
export const selectToken = state => state.session.token;
export const selectUserInfo = state => state.session.user;