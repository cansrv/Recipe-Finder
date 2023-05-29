package AVeS.recipeFinder.backend;


import AVeS.recipeFinder.backend.DTO.CustomerDTO;
import AVeS.recipeFinder.backend.DTO.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.repository.ComponentRepository;
import AVeS.recipeFinder.backend.repository.CustomerRepository;
import AVeS.recipeFinder.backend.repository.IngredientRepository;
import AVeS.recipeFinder.backend.repository.InventoryRepository;
import AVeS.recipeFinder.backend.service.CustomerService;
import AVeS.recipeFinder.backend.service.InventoryService;
import AVeS.recipeFinder.backend.service.RecipeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InventoryTest {
    @Resource
    private IngredientRepository ingredientRepository;
    @Resource
    private ComponentRepository componentRepository;
    @Resource
    private RecipeService recipeService;

    @Resource
    private CustomerService customerService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private CustomerRepository customerRepository;


    @Resource
    private InventoryRepository inventoryRepository;



    @Test
    void testCRUD() {

        Customer newCustomer = Customer.builder()
                .mail("gelifcenesiz@gmail.com")
                .password("elifcen22")
                .username("elifcenesiz")
                .build();

        customerService.addCustomer(newCustomer);

        Ingredient strawberry = Ingredient.builder()
                .name("strawberry")
                .description("nice fruit")
                .shelfLife("5 days")
                .build();

        ingredientRepository.save(strawberry);

        Long ingredientId = ingredientRepository.findAll().get(0).getId();
        Long customerId = customerRepository.findAll().get(0).getId();

        System.out.println(customerId);
        System.out.println(ingredientId);

        InventoryDTO newInventory = InventoryDTO.builder()
                .ingredientId(ingredientId)
                .customerId(customerId)
                .quantity(3.0)
                .unit("kg")
                .build();

        Inventory inventory = Inventory.builder()
                .customer(newCustomer)
                .ingredient(strawberry)
                .unit("kg")
                .quantity(5.0)
                .build();

        inventoryService.addInventory(newInventory);
        //inventoryRepository.save(inventory);


        List<Inventory> fridge = inventoryService.getCustomerFridge(1L);
        System.out.println(fridge.get(0).getQuantity());
        inventoryService.incrementInventoryQuantity(1L, 3);
        List<Inventory> fridge2 = inventoryService.getCustomerFridge(1L);
        System.out.println(fridge2.get(0).getQuantity());

        inventoryService.decrementInventoryQuantity(1L, 2);
        List<Inventory> fridge3 = inventoryService.getCustomerFridge(1L);
        System.out.println(fridge3.get(0).getQuantity());

        System.out.println(inventoryService.searchInventoryByName("st", 1L).get(0).getIngredient().getName());

        inventoryService.deleteInventory(1L);
        System.out.println(inventoryRepository.findInventoryById(1L));
    }
}