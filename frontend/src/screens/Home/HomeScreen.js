import React, { useLayoutEffect } from 'react';
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

export default function HomeScreen(props) {
  const { navigation } = props;

  useLayoutEffect(() => {
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
        <Image style={styles.photo} source={{ uri: item.photo_url }} />
        <Text style={styles.title}>{item.title}</Text>
        <Text style={styles.category}>{getCategoryName(item.categoryId)}</Text>
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
          <Button title='Post a Recipe'>
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
