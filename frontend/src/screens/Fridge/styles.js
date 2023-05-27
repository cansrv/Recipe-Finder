import { StyleSheet } from 'react-native';
import { RecipeCard } from '../../AppStyles';

const styles = StyleSheet.create({
  container: RecipeCard.container,
  photo: RecipeCard.photo,
  title: RecipeCard.title,
  category: RecipeCard.category,
  textStyle: {
    fontSize: 17,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#444444',
    marginTop: 3,
    marginRight: 5,
    marginLeft: 5,
  },
  removeTextStyle: {
    fontSize: 17,

    textAlign: 'center',
    color: 'red',
    marginTop: 3,
    marginRight: 5,
    marginLeft: 5,
    marginBottom: 5,
  },
});

export default styles;
