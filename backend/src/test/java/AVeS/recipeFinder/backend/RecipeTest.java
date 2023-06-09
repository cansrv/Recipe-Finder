package AVeS.recipeFinder.backend;

import AVeS.recipeFinder.backend.entity.dto.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.*;
import AVeS.recipeFinder.backend.repository.ComponentRepository;
import AVeS.recipeFinder.backend.repository.IngredientRepository;
import AVeS.recipeFinder.backend.repository.RecipeRepository;
import AVeS.recipeFinder.backend.service.CustomerService;
import AVeS.recipeFinder.backend.service.InventoryService;
import AVeS.recipeFinder.backend.service.RecipeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeTest {
    @Resource
    private IngredientRepository ingredientRepository;
    @Resource
    private ComponentRepository componentRepository;
    @Resource
    private RecipeService recipeService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private CustomerService customerService;

    @Test
    void testCRUD() {
        // Ingredient
        Ingredient hay = new Ingredient();
        hay.setName("Hay");
        hay.setDescription("Hay, just hay");
        hay.setShelfLife("1 year");
        ingredientRepository.save(hay);


        Ingredient strawberry = new Ingredient();
        strawberry.setName("strawberry");
        strawberry.setDescription("berryyyy");
        strawberry.setShelfLife("1 year");
        ingredientRepository.save(strawberry);

        hay = ingredientRepository.findAll().get(0);
        // Component
        componentRepository.save(new Component(null, hay, 1.0, "kg"));
        componentRepository.save(new Component(null, hay, 2.0, "kg"));

        Component hayComponent = componentRepository.findAll().get(0);
        Component hayComponent1 = componentRepository.findAll().get(1);

        Component strawberryComponent = Component.builder()
                .ingredient(strawberry)
                .quantity(2.0)
                .unit("kg")
                .build();

        Component strawberryComponent1 = Component.builder()
                .ingredient(strawberry)
                .quantity(1.0)
                .unit("kg")
                .build();

        componentRepository.save(strawberryComponent);
        componentRepository.save(strawberryComponent1);

        Recipe recipe = new Recipe();
        recipe.setTags(Arrays.asList("vegan", "vegetarian"));
        recipe.setName("Hay");
        recipe.setCategory("Dinner");
        recipe.setDescription("Hay, just hay");
        recipe.setSteps("Put hay in a bowl, eat it");
        recipe.setComponents(Arrays.asList(hayComponent,strawberryComponent));
        recipe.setDuration(1);

        Recipe recipe1 = new Recipe();
        recipe1.setTags(Arrays.asList("vegan", "vegetarian", "healthy"));
        recipe1.setName("Hay1");
        recipe1.setCategory("Dinner");
        recipe1.setDescription("Hay, just hay");
        recipe1.setSteps("Put hay in a bowl, eat it");
        recipe1.setComponents(Arrays.asList(hayComponent1, strawberryComponent1));
        recipe1.setDuration(10);


        // Create
        recipeService.addRecipe(recipe);
        recipeService.addRecipe(recipe1);
        // Read
        recipe = recipeService.getAllRecipes().get(0);
        assertEquals("Hay", recipe.getName());
        recipe = recipeService.searchRecipesByDuration(1).get(0);
        assertEquals("Hay", recipe.getName());
        recipe = recipeService.searchRecipesByCategory("Dinner").get(0);
        assertEquals("Hay", recipe.getName());
        recipe = recipeService.searchRecipesByTags("vegan").get(0);
        assertEquals("Hay", recipe.getName());
        recipe = recipeService.searchRecipesByTags("vegetarian").get(0);
        assertEquals("Hay", recipe.getName());
        recipe = recipeService.getRecipeByName("Hay");
        assertEquals("Hay", recipe.getName());
        List<Recipe> recipes = recipeService.searchRecipesByTags(Arrays.asList("vegan", "vegetarian"));
        assertEquals(2, recipes.size());
        recipes = recipeService.searchRecipesByName("Ha");
        assertEquals(2, recipes.size());

        // component
        Component component = recipeService.getRecipeByName("Hay").getComponents().get(0);
        assertEquals("Hay", component.getIngredient().getName());

        List<Recipe> recipes1 =  recipeService.findRecipesByIngredients(Arrays.asList(hay));
        assertEquals(2, recipes1.size());

        Customer customer = Customer.builder()
                .username("test")
                .password("test")
                .mail("test@test")
                .build();

        customerService.addCustomer(customer);

        InventoryDTO inventoryDTO = InventoryDTO.builder()
                .customerId(customer.getId())
                .ingredientId(hay.getId())
                .quantity(3.0)
                .unit("kg")
                .build();

        inventoryService.addInventory(inventoryDTO);

        InventoryDTO inventoryDTO1 = InventoryDTO.builder()
                .customerId(customer.getId())
                .ingredientId(strawberry.getId())
                .quantity(1.5)
                .unit("kg")
                .build();

        inventoryService.addInventory(inventoryDTO1);

        List<Recipe> availableRecipes = recipeService.findRecipesForFridge(customer.getId());
        assertEquals(1, availableRecipes.size());
        assertEquals(recipe1.getName(), availableRecipes.get(0).getName());

        List <Inventory> inventories = recipeService.findInsufficientInventory(customer.getId(), recipe.getId());

        Boolean isAvailable = recipeService.isRecipeAvailable(customer.getId(), recipe.getId());
        assertEquals(false, isAvailable);
        isAvailable = recipeService.isRecipeAvailable(customer.getId(), recipe1.getId());
        assertEquals(true, isAvailable);

        inventoryService.consumeRecipeIngredients(recipe1.getId(), customer.getId());
        List <Inventory> fridge = inventoryService.getCustomerFridge(customer.getId());
        isAvailable = recipeService.isRecipeAvailable(customer.getId(), recipe1.getId());
        assertEquals(false, isAvailable);

        // Update
        recipe.setName("Hay2");
        recipeService.updateRecipe(recipe);
        recipe = recipeService.getAllRecipes().get(1);
        assertEquals("Hay2", recipe.getName());

        // Delete
        recipeService.deleteRecipe(recipe.getId());
        assertEquals(1, recipeService.getAllRecipes().size());

    }
}
