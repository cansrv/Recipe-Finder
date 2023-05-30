import React, { useEffect, useLayoutEffect } from 'react';
import { FlatList, Text, View, Image, TouchableHighlight } from 'react-native';
import styles from './styles';
import { getIngredientName, getAllIngredients } from '../../data/MockDataAPI';
import { ingredients } from '../../data/dataArrays';
import axios from 'axios';
import RecipeImg from '../../../assets/recipe.png';
import { useSelector } from 'react-redux';

export default function AllIngreditensScreen(props) {
  const [ingredientsArray, setIngredients] = React.useState([]);
  const user = useSelector((state) => state.loginReducer.user);
  const { navigation, route } = props;
  useEffect(() => {
    axios.get('http://localhost:8080/api/ingredient/all').then((res) => {
      console.log(res.data);
      setIngredients(res.data);
    });
  }, []);
  const item = route.params?.ingredients;
  //const ingredientsArray = ingredients;
  //console.log(ingredientsArray);
  useLayoutEffect(() => {
    navigation.setOptions({
      title: route.params?.title,
      headerTitleStyle: {
        fontSize: 16,
      },
    });
  }, []);

  const onPressIngredient = (item) => {
    console.log(item);
    let body = {
      customerId: user.id,
      ingredientId: item.id,
      quantity: item.quantity,
      unit: item.unit,
    };
    axios.post(`http://localhost:8080/api/inventory`, body).then((res) => {
      console.log(res.data);
      setIngredients(
        ingredientsArray.filter((ingredient) => ingredient.id !== item.id)
      );
    });
  };

  const renderIngredient = ({ item }) => (
    <TouchableHighlight
      underlayColor='rgba(73,182,77,0.9)'
      onPress={() => onPressIngredient(item)}
    >
      <View style={styles.container}>
        <Image style={styles.photo} source={RecipeImg} />
        <Text style={styles.title}>{item.name}</Text>
        <Text style={{ color: 'grey' }}>{item[1]}</Text>
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
        keyExtractor={(item) => `${item.recipeId}`}
      />
    </View>
  );
}
