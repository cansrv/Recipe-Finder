import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import AppContainer from './src/navigations/AppNavigation';
import configureStore from './src/redux/reducers/configStore';
import { Provider } from 'react-redux';

export default function App() {
  const store = configureStore();
  return (
    <Provider store={store}>
      <AppContainer />
    </Provider>
  );
}
