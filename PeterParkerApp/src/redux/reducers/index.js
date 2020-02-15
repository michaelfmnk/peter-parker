import {combineReducers} from 'redux';
import session from './session';
import incidents from './incidents';


const reducers = combineReducers({
    session,
    incidents,
});

export default reducers;
