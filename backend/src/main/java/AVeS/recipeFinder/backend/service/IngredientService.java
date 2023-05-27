package AVeS.recipeFinder.backend.service;


import AVeS.recipeFinder.backend.DTO.IngredientDTO;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;

    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findIngredientById(id);

    }

    public Ingredient addIngredient (IngredientDTO ingredientDTO){
        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .description(ingredientDTO.getDescription())
                .shelfLife(ingredientDTO.getShelfLife())
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
