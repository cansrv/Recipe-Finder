package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.dto.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.Component;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;
    CustomerService customerService;
    IngredientService ingredientService;
    RecipeService recipeService;

    @Autowired
    public InventoryService (InventoryRepository inventoryRepository, CustomerService customerService, IngredientService ingredientService , RecipeService recipeService){
        this.inventoryRepository = inventoryRepository;
        this.customerService = customerService;
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findInventoryById(id);
    }

    public Inventory addInventory (InventoryDTO inventoryDTO)
    {
        Inventory inventory = Inventory.builder()
                .customer(customerService.findCustomerById(inventoryDTO.getCustomerId()))
                .ingredient(ingredientService.getIngredientById(inventoryDTO.getIngredientId()))
                .unit(inventoryDTO.getUnit())
                .quantity(inventoryDTO.getQuantity())
                .build();

        return inventoryRepository.save(inventory);

    }

    public void deleteInventory (Long id)
    {
        inventoryRepository.deleteById(id);
    }

    public void updateInventory(Inventory inventory){
        inventoryRepository.deleteById(inventory.getId());
        inventoryRepository.save(inventory);
    }

    public void decrementInventoryQuantity(Long id, Double consumedAmount){

        Inventory inventory = inventoryRepository.findInventoryById(id);

        if (inventory.getQuantity() < consumedAmount) {
            throw new IllegalArgumentException("Not enough ingredients in inventory");
        }

        else if (inventory.getQuantity() == consumedAmount) {
            deleteInventory(id);
            return;
        }

        inventory.setQuantity(inventory.getQuantity() - consumedAmount);
        inventoryRepository.save(inventory);
    }

    public void incrementInventoryQuantity(Long id, Double addedAmount){

        Inventory inventory = inventoryRepository.findInventoryById(id);

        inventory.setQuantity(inventory.getQuantity() + addedAmount);

        inventoryRepository.save(inventory);
    }

    public List<Inventory> getCustomerFridge(Long customerId) {
        return inventoryRepository.findInventoriesByCustomerEquals(customerId);
    }

    public List<Inventory> searchInventoryByName(String searchTerm, Long customerId){
        return inventoryRepository.searchInventoryByIngredient(searchTerm, customerId);
    }

    public void consumeRecipeIngredients(Long recipeId, Long customerId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        List<Inventory> customerFridge = getCustomerFridge(customerId);
        for (Inventory inventory : customerFridge) {
            for (Component recipeComponent : recipe.getComponents()) {
                if (inventory.getIngredient().getId().equals(recipeComponent.getIngredient().getId())) {
                    decrementInventoryQuantity(inventory.getId(), recipeComponent.getQuantity());
                }
            }
        }
    }



}
