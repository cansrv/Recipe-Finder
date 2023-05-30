import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import AppContainer from './src/navigations/AppNavigation';
import configureStore from './src/redux/reducers/configStore';
import { Provider } from 'react-redux';
import { Toast } from 'react-native-toast-message/lib/src/Toast';

export default function App() {
  const store = configureStore();
  console.disableYellowBox = true;
  return (
    <Provider store={store}>
      <Toast />
      <AppContainer />
    </Provider>
  );
}
