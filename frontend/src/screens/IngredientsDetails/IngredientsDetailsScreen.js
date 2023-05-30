import React, { useLayoutEffect } from 'react';
import { FlatList, Text, View, Image, TouchableHighlight } from 'react-native';
import styles from './styles';
import { getIngredientName, getAllIngredients } from '../../data/MockDataAPI';
import RecipeImg from '../../../assets/recipe.png';
export default function IngredientsDetailsScreen(props) {
  const { navigation, route } = props;

  const item = route.params?.ingredients;
  const ingredientsArray = item;

  useLayoutEffect(() => {
    navigation.setOptions({
      title: route.params?.title,
      headerTitleStyle: {
        fontSize: 16,
      },
    });
  }, []);

  const onPressIngredient = (item) => {
    //console.log(ingredientsArray, 'ingredientsArray');
    //console.log(item, 'onPressIngredient');
    let name = item.ingredient.name;
    let ingredient = item.ingredient;
    navigation.navigate('Ingredient', { ingredient, name });
  };

  const renderIngredient = ({ item }) => (
    <TouchableHighlight
      underlayColor='rgba(73,182,77,0.9)'
      onPress={() => onPressIngredient(item)}
    >
      <View style={styles.container}>
        <Image style={styles.photo} source={RecipeImg} />
        <Text style={styles.title}>{item.ingredient.name}</Text>
        <Text style={{ color: 'grey' }}>{`${item.quantity}${item.unit}`}</Text>
      </View>
    </TouchableHighlight>
  );

  return (
    <View>
      <FlatList
        vertical
        showsVerticalScrollIndicator={false}
        numColumns={3}
        data={ingredientsArray}
        renderItem={renderIngredient}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
}
