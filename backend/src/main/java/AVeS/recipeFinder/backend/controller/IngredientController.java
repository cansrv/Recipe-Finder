package AVeS.recipeFinder.backend.controller;

import AVeS.recipeFinder.backend.entity.dto.IngredientDTO;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/search")
    public List<Ingredient> searchByName(@RequestParam String name){
        return ingredientService.searchByName(name);
    }

    @GetMapping("/get")
    public Ingredient getIngredientById(@RequestParam Long id){
        return ingredientService.getIngredientById(id);
    }

    @PostMapping("/add")
    public Ingredient addIngredient(@RequestBody IngredientDTO ingredientDTO){
        return ingredientService.addIngredient(ingredientDTO);
    }

    @PostMapping("/delete")
    public void deleteIngredient(@RequestParam Long id){
        ingredientService.deleteIngredient(id);
    }

    @PostMapping("/update")
    public void updateIngredient(@RequestParam Ingredient ingredient){
        ingredientService.updateIngredient(ingredient);
    }

}
