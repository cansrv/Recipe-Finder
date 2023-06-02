import React, { useEffect, useLayoutEffect } from 'react';
import { FlatList, Text, View, Image, TouchableHighlight } from 'react-native';
import styles from './styles';
import { categories } from '../../data/dataArrays';
import { getNumberOfRecipes } from '../../data/MockDataAPI';
import MenuImage from '../../components/MenuImage/MenuImage';
import axios from 'axios';
import RecipeImg from '../../../assets/recipe.png';
import { ScrollView } from 'react-native-gesture-handler';
export default function CategoriesScreen(props) {
  const [categories, setCategories] = React.useState([]);
  const { navigation } = props;
  useEffect(() => {
    axios.get('http://35.228.238.149:8080/api/recipe').then((res) => {
      //console.log(res.data, 'res');
      const groupedArray = Object.values(
        res.data.reduce((result, obj) => {
          const { category } = obj;
          if (!result[category]) {
            result[category] = [];
          }
          result[category].push(obj);
          return result;
        }, {})
      );
      console.log(groupedArray, 'groupedArray');
      setCategories(groupedArray);
    });
  }, []);

  useLayoutEffect(() => {
    navigation.setOptions({
      headerTitleStyle: {
        fontWeight: 'bold',
        textAlign: 'center',
        alignSelf: 'center',
        flex: 1,
      },
      headerLeft: () => (
        <MenuImage
          onPress={() => {
            navigation.openDrawer();
          }}
        />
      ),
      headerRight: () => <View />,
    });
  }, []);

  const onPressCategory = (item) => {
    console.log(item, 'item');
    const title = item[0].category;
    const category = item[0].category;
    const recipes = item;
    navigation.navigate('RecipesList', { category, title, recipes });
  };

  //const renderCategory = ({ item }) => (
  //  //console.log(item, 'item'),
  //  <TouchableHighlight
  //    underlayColor='rgba(73,182,77,0.9)'
  //    onPress={() => onPressCategory(item)}
  //  >
  //    <View style={styles.categoriesItemContainer}>
  //      <Image style={styles.categoriesPhoto} source={RecipeImg} />
  //      <Text style={styles.categoriesName}>{item.name}</Text>
  //      <Text style={styles.categoriesInfo}>
  //        {getNumberOfRecipes(item.id)} recipes
  //      </Text>
  //    </View>
  //  </TouchableHighlight>
  //);

  return (
    <View>
      <ScrollView>
        {categories.map((item) => (
          <TouchableHighlight
            underlayColor='rgba(73,182,77,0.9)'
            onPress={() => onPressCategory(item)}
          >
            <View style={styles.categoriesItemContainer}>
              <Image style={styles.categoriesPhoto} source={RecipeImg} />
              <Text style={styles.categoriesName}>{item[0].category}</Text>
              <Text style={styles.categoriesInfo}>{item.length} recipes</Text>
            </View>
          </TouchableHighlight>
        ))}
      </ScrollView>
    </View>
  );
}
