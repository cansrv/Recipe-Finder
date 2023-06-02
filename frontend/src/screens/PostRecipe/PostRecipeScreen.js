import React, { useState, useRef, useEffect } from 'react';
import { View, Text, Button, TextInput, Pressable } from 'react-native';
import { ingredients } from '../../data/dataArrays';
import DropDownPicker from 'react-native-dropdown-picker';
import { ScrollView } from 'react-native-gesture-handler';
import { log } from 'react-native-reanimated';
import axios from 'axios';
import { Toast } from 'react-native-toast-message/lib/src/Toast';
import { useNavigation } from '@react-navigation/native';
const newIngredients = ingredients.map((ingredient) => {
  return {
    label: ingredient.name,
    value: ingredient.ingredientId,
  };
});

const PostRecipeScreen = () => {
  //useEffect(() => {
  //  axios

  //    .get('http://35.228.238.149:8080/api/ingredient/all')
  //    .then((resp) => {
  //      if (resp.data) {
  //        console.log(resp.data);
  //        setIngredients(resp.data);
  //      }
  //    })
  //    .catch((err) => console.log(err));
  //}, []);

  //const resultIngredients = ingredients.map((ingredient) => {
  //  return {

  const textInputRef = useRef(null);
  const [open, setOpen] = useState(false);
  const [value, setValue] = useState([]);
  const [ingredients, setIngredients] = React.useState([{}]);
  const [items, setItems] = useState(newIngredients);
  const [description, setDescription] = useState('');
  const [title, setTitle] = useState('');
  const [cat, setCat] = useState('');
  const navigation = useNavigation();
  const handleSubmit = () => {
    let body = {
      components: value.map((item) => {
        return {
          ingredientId: item - 35622,
          quantity: '',
          unit: '',
        };
      }),

      description: description,
      duration: 30,
      name: title,
      steps: '',
      tags: [],
      category: cat,
    };
    console.log(body);
    axios
      .post('http://35.228.238.149:8080/api/recipe/add', body)
      .then((resp) => {
        if (resp.data) {
          console.log(resp.data);
          Toast.show({
            type: 'success',
            text1: 'Success',
            text2: 'Recipe added',
          });
          navigation.navigate('Home');
        }
      })
      .catch((err) => console.log(err));
  };
  return (
    //A recipe post screen that makes user create a recipe post
    //The user can add a title, description, ingredients, and steps
    //The user can also add a picture of the recipe

    //The user can then post the recipe
    //The user can also cancel the recipe post
    <ScrollView>
      <View style={{ alignItems: 'center' }}>
        <Text style={{ fontWeight: 'bold', fontSize: '30' }}>
          Post a Recipe!
        </Text>

        <TextInput
          style={{
            height: 40,
            borderColor: 'gray',
            borderWidth: 1,
            borderRadius: 10,
            width: 300,
            margin: 10,
          }}
          placeholder='Title'
          onChangeText={(text) => setTitle(text)}
        />
        <TextInput
          style={{
            height: 40,
            borderColor: 'gray',
            borderWidth: 1,
            borderRadius: 10,
            width: 300,
            margin: 10,
          }}
          placeholder='Category'
          onChangeText={(text) => setCat(text)}
        />
        {/*<View
        style={{
          //  height: '100%',
          //  borderColor: 'gray',
          //  borderWidth: 1,
          //  borderRadius: 10,
          width: 300,
          margin: 10,
        }} 
      >*/}
        <DropDownPicker
          items={items}
          multiple={true}
          open={open}
          setOpen={setOpen}
          setValue={setValue}
          setItems={setItems}
          value={value}
          mode='BADGE'
          style={{ width: 300, marginLeft: 45 }}
          badgeDotColors={[
            '#e76f51',
            '#00b4d8',
            '#e9c46a',
            '#e76f51',
            '#8ac926',
            '#00b4d8',
            '#e9c46a',
          ]}
        />
        {/*</View>*/}
        {/*{value.map((item) => (
          <TextInput
            style={{
              height: 40,
              borderColor: 'gray',
              borderWidth: 1,
              borderRadius: 10,
              width: 300,
              margin: 10,
            }}
            placeholder={`Quantity of ${findName(item)}`}
            onChangeText={(value) => setQuantityOfIngredient(item, value)}
          />
        ))}*/}
        <View>
          <TextInput
            style={{
              height: 300,
              borderColor: 'gray',
              borderWidth: 1,
              borderRadius: 10,
              width: 300,
              margin: 10,
            }}
            placeholder='Description'
            multiline={true}
            onChangeText={(text) => setDescription(text)}
          />
        </View>
        <Pressable
          style={{
            width: 300,
            height: 40,
            backgroundColor: '#34a8eb',
            justifyContent: 'center',
            alignItems: 'center',

            borderWidth: 1,
            borderRadius: 10,
          }}
          onPress={handleSubmit}
        >
          <Text style={{ textAlign: 'center', color: 'white', fontSize: 20 }}>
            {'Post'}
          </Text>
        </Pressable>
      </View>
    </ScrollView>
  );
};
export default PostRecipeScreen;
