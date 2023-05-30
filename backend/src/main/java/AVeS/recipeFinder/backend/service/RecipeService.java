package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.dto.ComponentDTO;
import AVeS.recipeFinder.backend.entity.dto.RecipeDTO;
import AVeS.recipeFinder.backend.entity.model.*;
import AVeS.recipeFinder.backend.repository.InventoryRepository;
import AVeS.recipeFinder.backend.repository.RecipeRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Resource
    private RecipeRepository recipeRepository;

    @Resource
    private InventoryRepository inventoryRepository;

    @Resource
    private ComponentService componentService;

    @Resource
    private NutritionService nutritionService;




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
        return recipeRepository.findRecipesByNameContaining(name);
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

    public Recipe addRecipe(RecipeDTO recipeDTO) {

        List<Component> components = recipeDTO.getComponents().stream().map(componentDTO -> {
            Component component = componentService.addComponent(componentDTO);
            return component;
        }).toList();

        Nutrition nutrition = nutritionService.addNutrition(components);

        Recipe recipe = Recipe.builder()
                .name(recipeDTO.getName())
                .description(recipeDTO.getDescription())
                .duration(recipeDTO.getDuration())
                .steps(recipeDTO.getSteps())
                .tags(recipeDTO.getTags())
                .category(recipeDTO.getCategory())
                .components(components)
                .nutrition(nutrition)
                .build();

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public void updateRecipe(Recipe recipe) {
       deleteRecipe(recipe.getId());
        recipeRepository.save(recipe);
    }

    public List<Recipe> findRecipesForFridge(Long customerId) {
        // get ingredients from components
        List<Inventory> fridge = inventoryRepository.findByCustomer(customerId);
        List<Ingredient> ingredients = fridge.stream().map(Inventory::getIngredient).toList();
        List<Recipe> recipes = findRecipesByIngredients(ingredients);

        // check if the recipe has all the components in the inventory in the right amount using isRecipeAvailable
        return recipes.stream().filter(recipe -> isRecipeAvailable(recipe.getId(), fridge)).toList();
    }

    public List<Recipe> findRecipesForFridgeIngredients(Long customerId) {
        List<Inventory> inventories = inventoryRepository.findByCustomer(customerId);
        List<Ingredient> ingredients = inventories.stream().map(Inventory::getIngredient).toList();
        return findRecipesByIngredients(ingredients);
    }

    public List<Recipe> findRecipesByIngredients(List<Ingredient> ingredients) {
        return recipeRepository.findRecipesByIngredients(ingredients, ingredients.size());
    }

    // find insufficient inventory items for a recipe and return the missing items with the quantity needed
    public List<Inventory> findInsufficientInventory(Long customerId, Long recipeId) {
        List<Inventory> inventories = inventoryRepository.findByCustomer(customerId);
        Recipe recipe = recipeRepository.findRecipeById(recipeId);
        List<Component> components = recipe.getComponents();
        return components.stream().map(component -> {
            Inventory inventory = inventories.stream().filter(inv -> inv.getIngredient().getId() == component.getIngredient().getId()).findFirst().orElse(null);
            if (inventory == null) {
                return Inventory.builder().quantity(component.getQuantity()).ingredient(component.getIngredient()).unit(component.getUnit()).build();
            }
            if (inventory.getQuantity() < component.getQuantity()) {
                return Inventory.builder().quantity(component.getQuantity() - inventory.getQuantity()).ingredient(component.getIngredient()).unit(component.getUnit()).build();
            }
            return Inventory.builder().quantity(0.0).ingredient(component.getIngredient()).unit(component.getUnit()).build();
        }).toList();
    }

    public Boolean isRecipeAvailable(Long customerId, Long recipeId) {
        List<Inventory> inventories = inventoryRepository.findByCustomer(customerId);
        return isRecipeAvailable(recipeId, inventories);
    }

    public Boolean isRecipeAvailable( Long recipeId, List<Inventory> fridge) {

        Recipe recipe = recipeRepository.findRecipeById(recipeId);
        List<Component> components = recipe.getComponents();
        return components.stream().allMatch(component -> {
            Inventory inventory = fridge.stream().filter(inv -> inv.getIngredient().getId() == component.getIngredient().getId()).findFirst().orElse(null);
            if (inventory == null) {
                return false;
            }
            return inventory.getQuantity() >= component.getQuantity();
        });
    }
}
