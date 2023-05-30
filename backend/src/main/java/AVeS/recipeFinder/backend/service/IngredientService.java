package AVeS.recipeFinder.backend.service;


import AVeS.recipeFinder.backend.entity.dto.IngredientDTO;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Nutrition;
import AVeS.recipeFinder.backend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    private NutritionService nutritionService;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, NutritionService nutritionService) {
        this.ingredientRepository = ingredientRepository;
        this.nutritionService = nutritionService;

    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findIngredientById(id);

    }

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient (IngredientDTO ingredientDTO){

        Nutrition nutrition = nutritionService.addNutrition(ingredientDTO.getNutrition());

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .description(ingredientDTO.getDescription())
                .shelfLife(ingredientDTO.getShelfLife())
                .nutrition(nutrition)
                .build();

        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> searchByName (String name){
        return ingredientRepository.searchIngredientsByName(name);
    }

    public void deleteIngredient (Long id){
        ingredientRepository.deleteById(id);
    }
    public void updateIngredient(Ingredient ingredient)
    {
        deleteIngredient(ingredient.getId());
        ingredientRepository.save(ingredient);
    }

}
