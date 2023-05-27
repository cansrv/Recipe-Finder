import { View, Text, TouchableHighlight, Image } from 'react-native';
import styles from './styles';
import { useState } from 'react';
import { ingredients } from '../../data/dataArrays';
import { FlatList } from 'react-native-gesture-handler';
import { exp } from 'react-native-reanimated';
import { recipes } from '../../data/dataArrays';
//import { ingredients } from '../../data/dataArrays';
import ViewIngredientsButton from '../../components/ViewIngredientsButton/ViewIngredientsButton';

const Fridge = (props) => {
  const { navigation } = props;
  const [fridge, setFridge] = useState(ingredients.slice(0, 13));
  const renderItem = ({ item }) => {
    return (
      <TouchableHighlight underlayColor='rgba(73,182,77,0.9)'>
        <View style={styles.container}>
          <Image style={styles.photo} source={{ uri: item.photo_url }} />
          <Text style={styles.title}>{item.name}</Text>
          <Text style={styles.removeTextStyle}>{'Remove Ingredient'}</Text>
        </View>
      </TouchableHighlight>
    );
  };
  return (
    <View>
      {fridge.length != 0 ? (
        <View style={{ alignItems: 'center', marginBottom: 180 }}>
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
