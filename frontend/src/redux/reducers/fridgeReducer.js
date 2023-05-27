import * as actionTypes from '../actions/actionTypes';

const initialState = {
  fridge: null,
};

const fridgeReducer = (state = initialState, action) => {
  let newState;
  switch (action.type) {
    case actionTypes.ADD_TO_FRIDGE:
      return (newState = [...state, payload]);
    default:
      return state;
  }
};
export default fridgeReducer;
