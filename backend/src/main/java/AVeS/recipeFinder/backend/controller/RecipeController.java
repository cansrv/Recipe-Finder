package AVeS.recipeFinder.backend.controller;

import AVeS.recipeFinder.backend.entity.dto.RecipeDTO;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/all")
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody RecipeDTO recipe){
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipesByName(@RequestParam String name){
        return recipeService.searchRecipesByName(name);
    }

    @GetMapping("/searchById")
    public Recipe getRecipeById(@RequestParam Long id){
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/delete")
    public void deleteRecipe(@RequestParam Long id){
        recipeService.deleteRecipe(id);
    }

    @PostMapping("/update")
    public void updateRecipe(Recipe recipe){
        recipeService.updateRecipe(recipe);
    }

    @GetMapping("/searchByCategory")
    public List<Recipe> searchRecipesByCategory(@RequestParam String category){
        return recipeService.searchRecipesByCategory(category);
    }

    @GetMapping("/searchByTags")
    public List<Recipe> searchRecipesByTags(@RequestParam String tag){
        return recipeService.searchRecipesByTags(tag);
    }

    @GetMapping("/searchByDuration")
    public List<Recipe> searchRecipesByDuration(@RequestParam int duration){
        return recipeService.searchRecipesByDuration(duration);
    }

    @GetMapping("/forFridge")
    public List<Recipe> getRecipesForFridge(@RequestParam Long customerId){
        return recipeService.findRecipesForFridge(customerId);
    }

    @GetMapping("/forFridge/ingredients")
    public List<Recipe> getRecipesForFridgeIngredients(@RequestParam Long customerId){
        return recipeService.findRecipesForFridgeIngredients(customerId);
    }

    @GetMapping("/insufficientIngredients")
    public List<Inventory> getInsufficientIngredients(@RequestParam Long customerId, @RequestParam Long recipeId){
        return recipeService.findInsufficientInventory(customerId, recipeId);
    }

    @GetMapping("/available")
    public Boolean isRecipeAvailable(@RequestParam Long customerId, @RequestParam Long recipeId){
        return recipeService.isRecipeAvailable(customerId, recipeId);
    }

}
