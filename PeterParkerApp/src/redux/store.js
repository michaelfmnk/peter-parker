import AsyncStorage from '@react-native-community/async-storage';
import autoMergeLevel2 from 'redux-persist/es/stateReconciler/autoMergeLevel2';
import {applyMiddleware, compose, createStore} from 'redux';
import {persistReducer, persistStore} from 'redux-persist';
import thunk from 'redux-thunk';
import reducers from './reducers';
import api from '../middleware/api';
import createSagaMiddleware from 'redux-saga';
import {rootSaga} from '../sagas';

const persistConfig = {
    key: 'root',
    storage: AsyncStorage,
    stateReconciler: autoMergeLevel2,
    whitelist: ['session']
};

const sagaMiddleware = createSagaMiddleware();
const middlewares = [thunk, api, sagaMiddleware];

const composeEnhancers = /*window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || */compose;
const enhancer = composeEnhancers(applyMiddleware(...middlewares));

const persistedReducer = persistReducer(persistConfig, reducers);
const store = createStore(persistedReducer, enhancer);
const persistor = persistStore(store);

sagaMiddleware.run(rootSaga);

export {store, persistor};
