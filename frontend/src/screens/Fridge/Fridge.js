import {
  View,
  Text,
  TouchableHighlight,
  Image,
  Pressable,
  Button,
} from 'react-native';
import styles from './styles';
import { useEffect, useState } from 'react';
import { ingredients } from '../../data/dataArrays';
import { FlatList } from 'react-native-gesture-handler';
import { exp } from 'react-native-reanimated';
import { recipes } from '../../data/dataArrays';
//import { ingredients } from '../../data/dataArrays';
import ViewIngredientsButton from '../../components/ViewIngredientsButton/ViewIngredientsButton';
import { useSelector } from 'react-redux';
import axios from 'axios';
import RecipeImg from '../../../assets/recipe.png';

const Fridge = (props) => {
  const user = useSelector((state) => state.loginReducer.user);
  const [fridge, setFridge] = useState([]);
  useEffect(() => {
    console.log('fridge');
    console.log(user);
    axios
      .get(`http://localhost:8080/api/inventory?id=${user.id}`)
      .then((res) => {
        console.log(res.data);
        setFridge(res.data);
      });
  }, []);
  const { navigation } = props;
  //const [fridge, setFridge] = useState(ingredients.slice(0, 13));
  const removeItem = (item) => {
    console.log(item);
    let body = {};
    axios
      .delete(
        `http://localhost:8080/api/inventory/deleteByIngredient?customerId=${user.id}&ingredientId=${item.ingredient.id}`
      )
      .then((res) => {
        console.log(res.data);
        setFridge(
          fridge.filter(
            (ingredient) => ingredient.ingredient.id !== item.ingredient.id
          )
        );
      });
  };
  const handleTextPress = () => {
    //console.log(item);
    axios
      .get(
        `http://localhost:8080/api/recipe/forFridge/ingredients?customerId=${user.id}`
      )
      .then((res) => {
        console.log(res.data);
        navigation.navigate('RecipesList', {
          recipes: res.data,
          title: 'Recipes',
        });
      });
  };
  const renderItem = ({ item }) => {
    return (
      <TouchableHighlight underlayColor='rgba(73,182,77,0.9)'>
        <View style={styles.container}>
          <Image style={styles.photo} source={RecipeImg} />
          <Text style={styles.title}>{item.ingredient.name}</Text>
          <Pressable onPress={() => removeItem(item)}>
            <Text style={styles.removeTextStyle}>{'Remove Ingredient'}</Text>
          </Pressable>
        </View>
      </TouchableHighlight>
    );
  };
  return (
    <View>
      {fridge.length != 0 ? (
        <View style={{ alignItems: 'center', marginBottom: 180 }}>
          <Button
            title={'See what you can cook'}
            onPress={handleTextPress}
          ></Button>
          <FlatList
            vertical
            showsVerticalScrollIndicator={false}
            numColumns={2}
            data={fridge}
            renderItem={renderItem}
            keyExtractor={(item) => `${item.ingredientId}`}
          />
          <ViewIngredientsButton
            title={'Add Some Ingredients'}
            onPress={() => navigation.navigate('Ingredients')}
          />
        </View>
      ) : (
        <View style={{ paddingTop: '45%', alignItems: 'center' }}>
          <Text style={styles.textStyle}>
            {'There are no ingredients in your fridge, add some!'}
          </Text>
          <ViewIngredientsButton
            title={'Add Some Ingredients'}
            onPress={() => navigation.navigate('Ingredients')}
          />
        </View>
      )}
    </View>
  );
};
export default Fridge;
