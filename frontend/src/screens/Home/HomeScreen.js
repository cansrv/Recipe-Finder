import React, { useEffect, useLayoutEffect, useState } from 'react';
import {
  FlatList,
  Text,
  View,
  TouchableHighlight,
  Image,
  Button,
} from 'react-native';
import styles from './styles';
import { recipes } from '../../data/dataArrays';
import MenuImage from '../../components/MenuImage/MenuImage';
import { getCategoryName } from '../../data/MockDataAPI';
import RecipeImg from '../../../assets/recipe.png';
import axios from 'axios';
import { useIsFocused } from '@react-navigation/native';

export default function HomeScreen(props) {
  const { navigation } = props;
  const [recipes, setRecipes] = useState([]);
  const isFocused = useIsFocused();
  useEffect(() => {
    axios.get('http://localhost:8080/api/recipe').then((res) => {
      console.log(res.data);
      setRecipes(res.data);
    });
  }, []);

  useLayoutEffect(() => {
    //axios.get('http://localhost:8080/api/recipe').then((res) => {
    //  console.log(res.data);
    //  setRecipes(res.data);
    //});
    navigation.setOptions({
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

  const onPressRecipe = (item) => {
    navigation.navigate('Recipe', { item });
  };

  const renderRecipes = ({ item }) => (
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
  );

  return (
    <View>
      <View style={{ width: '100%', flexDirection: 'row' }}>
        <View
          style={{
            color: 'black ',
            //marginLeft: '5%',
            //marginTop: '5%',
            //marginBottom: '5%',
            fontSize: 20,
            fontWeight: 'bold',
          }}
        >
          <Text
            style={{
              color: 'black ',
              marginLeft: '5%',
              marginTop: '5%',
              marginBottom: '5%',
              fontSize: 20,
              fontWeight: 'bold',
            }}
          >
            {'Welcome, Can!'}
          </Text>
        </View>
        <View style={{ alignItems: 'flex-end', marginLeft: 'auto' }}>
          <Button
            title='Post a Recipe'
            onPress={() => navigation.navigate('Post')}
          >
            {/*<Text>{'Post a Rating'}</Text>*/}
          </Button>
        </View>
      </View>
      <FlatList
        vertical
        showsVerticalScrollIndicator={false}
        numColumns={2}
        data={recipes}
        renderItem={renderRecipes}
        keyExtractor={(item) => `${item.recipeId}`}
      />
    </View>
  );
}
