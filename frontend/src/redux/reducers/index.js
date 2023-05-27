import { combineReducers } from '@reduxjs/toolkit';
import fridgeReducer from './fridgeReducer';
import { loginReducer } from './auth';

const reducers = combineReducers({
  fridgeReducer,
  loginReducer,
});

export default reducers;
