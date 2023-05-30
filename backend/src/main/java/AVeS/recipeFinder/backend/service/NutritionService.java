package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.dto.NutritionDTO;
import AVeS.recipeFinder.backend.entity.model.Component;
import AVeS.recipeFinder.backend.entity.model.Nutrition;
import AVeS.recipeFinder.backend.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionService {

    private NutritionRepository nutritionRepository;

    @Autowired
    public NutritionService(NutritionRepository nutritionRepository){
        this.nutritionRepository = nutritionRepository;
    }

    public void deleteNutrition(Long id){
        nutritionRepository.deleteById(id);
    }

    public Nutrition addNutrition(NutritionDTO nutritionDTO){

        if (nutritionDTO != null) {
            Nutrition nutrition = Nutrition.builder()
                    .calorie(nutritionDTO.getCalorie())
                    .carbohydrate(nutritionDTO.getCarbohydrate())
                    .fat(nutritionDTO.getFat())
                    .protein(nutritionDTO.getProtein())
                    .build();

            return nutritionRepository.save(nutrition);
        }
        return null;
    }

    public Nutrition addNutrition(List<Component> components){

        List<Nutrition> nutrients = components.stream().map(component -> {
            Nutrition n = component.getIngredient().getNutrition();
            if (n != null) {
                return n;
            }
            return Nutrition.builder().calorie(0.0).carbohydrate(0.0).fat(0.0).protein(0.0).build();
        }).toList();

        Nutrition newNutrition = new Nutrition(nutrients);
        return nutritionRepository.save(newNutrition);
    }

    public Nutrition getNutritionById(Long id){
        return nutritionRepository.findById(id).orElse(null);
    }



}
