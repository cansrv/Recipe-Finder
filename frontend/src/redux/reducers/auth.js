import * as actionTypes from '../actions/actionTypes';

const initialState = {
  user: null,
  isLoggedIn: true,
};

export const loginReducer = (state = initialState, action) => {
  let newState;
  switch (action.type) {
    case actionTypes.LOGIN:
      newState = { ...state, user: action.payload, isLoggedIn: true };
      console.log(newState);
      return newState;
    case actionTypes.LOGOUT:
      return (newState = { ...state, user: null, isLoggedIn: false });
    case actionTypes.SIGNUP:
      return (newState = { ...state, user: action.payload, isLoggedIn: true });

    default:
      return state;
  }
};

//export const logoutReducer = (state = initialState, action) => {
//  let newState;
//  switch (action.type) {
//    case actionTypes.LOGOUT:
//      return (newState = { ...state, user: null, isLoggedIn: false });
//    default:
//      return state;
//  }
//};
//export const signUpReducer = (state = initialState, action) => {
//  let newState;
//  switch (action.type) {
//    case actionTypes.SIGNUP:
//      return (newState = { ...state, isLoggedIn: true });
//    default:
//      return state;
//  }
//};
