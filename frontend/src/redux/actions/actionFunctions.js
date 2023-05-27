import * as actionTypes from './actionTypes';
export const addToFrdige = (ingredient) => ({
  type: actionTypes.ADD_TO_FRIDGE,
  payload: ingredient,
});
export const login = (user) => ({
  type: actionTypes.LOGIN,
  payload: user,
});

export const logout = () => ({
  type: actionTypes.LOGOUT,
});

export const signUp = (user) => ({
  type: actionTypes.SIGNUP,
  payload: user,
});
