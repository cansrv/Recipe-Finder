package AVeS.recipeFinder.backend.controller;

import AVeS.recipeFinder.backend.entity.dto.InventoryDTO;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import AVeS.recipeFinder.backend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/find")
    public List<Inventory> getFridge(@RequestParam Long id){
        return inventoryService.getCustomerFridge(id);
    }

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody InventoryDTO inventory){
        return inventoryService.addInventory(inventory);
    }

    @PostMapping("/consume")
    public void consumeInventory(@RequestParam Long customerId, @RequestParam Long recipeId){
        inventoryService.consumeRecipeIngredients(customerId, recipeId);
    }



}
