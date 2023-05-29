package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.DTO.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;
    CustomerService customerService;
    IngredientService ingredientService;

    @Autowired
    public InventoryService (InventoryRepository inventoryRepository, CustomerService customerService, IngredientService ingredientService){
        this.inventoryRepository = inventoryRepository;
        this.customerService = customerService;
        this.ingredientService = ingredientService;
    }

    public void addInventory (InventoryDTO inventoryDTO)
    {
        Inventory inventory = Inventory.builder()
                .customer(customerService.findCustomerById(inventoryDTO.getCustomerId()))
                .ingredient(ingredientService.getIngredientById(inventoryDTO.getIngredientId()))
                .unit(inventoryDTO.getUnit())
                .quantity(inventoryDTO.getQuantity())
                .build();

        inventoryRepository.save(inventory);

    }

    public void deleteInventory (Long id)
    {
        inventoryRepository.deleteById(id);
    }

    public void updateInventory(Inventory inventory){
        inventoryRepository.deleteById(inventory.getId());
        inventoryRepository.save(inventory);
    }

    public void decrementInventoryQuantity(Long id, double consumedAmount){

        Inventory inventory = inventoryRepository.findInventoryById(id);

        inventory.setQuantity(inventory.getQuantity() - consumedAmount);

        inventoryRepository.deleteById(id);
        inventoryRepository.save(inventory);
    }

    public void incrementInventoryQuantity(Long id, double addedAmount){

        Inventory inventory = inventoryRepository.findInventoryById(id);

        inventory.setQuantity(inventory.getQuantity() + addedAmount);

        inventoryRepository.deleteById(id);
        inventoryRepository.save(inventory);
    }

    public List<Inventory> getCustomerFridge(Long customerId) {
        return inventoryRepository.findByCustomer(customerId);
    }

    public List<Inventory> searchInventoryByName(String searchTerm, Long customerId){
        return inventoryRepository.searchInventoryByIngredient(searchTerm, customerId);
    }


}