import { createStore } from '@reduxjs/toolkit';
import reducers from './index';

export default function configureStore() {
  return createStore(reducers);
}
