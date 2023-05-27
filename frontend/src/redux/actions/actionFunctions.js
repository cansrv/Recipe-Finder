import * as actionTypes from './actionTypes';
export const addToFrdige = (ingredient) => ({
  type: actionTypes.ADD_TO_FRIDGE,
  payload: ingredient,
});
