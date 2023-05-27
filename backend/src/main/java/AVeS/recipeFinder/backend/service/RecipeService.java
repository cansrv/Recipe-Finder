package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.repository.RecipeRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Resource
    private RecipeRepository recipeRepository;



    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepository.findRecipeByName(name);
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findRecipesByNameLike(name);
    }

    public List<Recipe> searchRecipesByCategory(String category) {
        return recipeRepository.findRecipesByCategoryContaining(category);
    }

    public List<Recipe> searchRecipesByTags(String tag) {
        return recipeRepository.findRecipesByTagsContains(tag);
    }

    public List<Recipe> searchRecipesByTags(List<String> tags) {
        return recipeRepository.findRecipesByTagsIn(tags);
    }

    public List<Recipe> searchRecipesByDuration(int duration) {
        return recipeRepository.findRecipesByDurationLessThanEqual(duration);
    }

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public void updateRecipe(Recipe recipe) {
       deleteRecipe(recipe.getId());
        recipeRepository.save(recipe);
    }
}
