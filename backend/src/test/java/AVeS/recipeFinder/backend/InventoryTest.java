package AVeS.recipeFinder.backend;


import AVeS.recipeFinder.backend.entity.dto.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.repository.CustomerRepository;
import AVeS.recipeFinder.backend.repository.IngredientRepository;
import AVeS.recipeFinder.backend.repository.InventoryRepository;
import AVeS.recipeFinder.backend.service.CustomerService;
import AVeS.recipeFinder.backend.service.InventoryService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class InventoryTest {
    @Resource
    private IngredientRepository ingredientRepository;
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


        List<Inventory> fridge = inventoryService.getCustomerFridge(1L);
        assertEquals(3.0, fridge.get(0).getQuantity());
        inventoryService.incrementInventoryQuantity(1L, 3);
        List<Inventory> fridge2 = inventoryService.getCustomerFridge(1L);
        assertEquals(6.0, fridge2.get(0).getQuantity());

        inventoryService.decrementInventoryQuantity(1L, 2);
        List<Inventory> fridge3 = inventoryService.getCustomerFridge(1L);
        assertEquals(4.0, fridge3.get(0).getQuantity());

        assertEquals("strawberry",inventoryService.searchInventoryByName("st", 1L).get(0).getIngredient().getName());

        inventoryService.deleteInventory(1L);
        assertNull(inventoryRepository.findInventoryById(1L));
    }
}