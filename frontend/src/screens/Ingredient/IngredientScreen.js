import React, { useEffect, useLayoutEffect } from 'react';
import {
  FlatList,
  ScrollView,
  Text,
  View,
  Image,
  TouchableHighlight,
} from 'react-native';
import styles from './styles';
import {
  getIngredientUrl,
  getRecipesByIngredient,
  getCategoryName,
} from '../../data/MockDataAPI';
import RecipeImg from '../../../assets/recipe.png';
import axios from 'axios';
export default function IngredientScreen(props) {
  const { navigation, route } = props;
  const [recipes, setRecipes] = React.useState([]);

  const ingredientId = route.params?.ingredient.id;
  const ingredient = route.params?.ingredient;
  const nutritiom = route.params?.ingredient.nutrition;
  //const ingredientUrl = getIngredientUrl(ingredientId);
  const ingredientName = route.params?.name;

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/api/recipe/searchByIngredient?ingredientId=${ingredientId}`
      )
      .then((res) => {
        console.log(res.data);
        setRecipes(res.data);
      });
  }, []);

  useLayoutEffect(() => {
    console.log(ingredient);
    navigation.setOptions({
      title: route.params?.name,
    });
  }, []);

  const onPressRecipe = (item) => {
    navigation.navigate('Recipe', { item });
  };

  const renderRecipes = ({ item }) => (
    <TouchableHighlight
      underlayColor='rgba(73,182,77,0.9)'
      onPress={() => onPressRecipe(item)}
    >
      <TouchableHighlight
        underlayColor='rgba(73,182,77,0.9)'
        onPress={() => onPressRecipe(item)}
      >
        <View style={styles.container}>
          <Image style={styles.photo} source={RecipeImg} />
          <Text style={styles.title}>{item.name}</Text>
          <Text style={styles.category}>{item.category}</Text>
        </View>
      </TouchableHighlight>
    </TouchableHighlight>
  );

  return (
    <ScrollView style={styles.mainContainer}>
      <View
        style={{
          borderBottomWidth: 0.4,
          marginBottom: 10,
          borderBottomColor: 'grey',
        }}
      >
        <Image style={styles.photoIngredient} source={RecipeImg} />
      </View>
      <Text style={styles.ingredientInfo}>
        Nutrition Facts of {ingredientName}:
      </Text>
      <Text style={styles.ingredientInfo}>{`Fat: ${nutritiom.fat}g`}</Text>
      <Text
        style={styles.ingredientInfo}
      >{`Carbs: ${nutritiom.carbohydrate}g`}</Text>
      <Text
        style={styles.ingredientInfo}
      >{`Protein: ${nutritiom.protein}g`}</Text>
      <Text
        style={styles.ingredientInfo}
      >{`Total Calories: ${nutritiom.calorie}kCal`}</Text>

      <Text style={styles.ingredientInfo}>Recipes with {ingredientName}:</Text>
      <View>
        <FlatList
          vertical
          showsVerticalScrollIndicator={false}
          numColumns={2}
          data={recipes}
          renderItem={renderRecipes}
          keyExtractor={(item) => `${item.id}`}
        />
      </View>
    </ScrollView>
  );
}
