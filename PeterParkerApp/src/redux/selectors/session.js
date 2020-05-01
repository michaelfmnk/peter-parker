export const selectIsLoggedIn = state => !!state.session.token;
export const selectToken = state => state.session.token;
export const selectUserInfo = state => state.session.user;
export const selectPlateNumber = state => state.session.user.plateNumber;
export const selectPlateRecentlyUpdated = state => state.session.plateRecentlyUpdated;