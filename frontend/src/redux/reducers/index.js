import { combineReducers } from '@reduxjs/toolkit';
import fridgeReducer from './fridgeReducer';

const reducers = combineReducers({ fridgeReducer });

export default reducers;
