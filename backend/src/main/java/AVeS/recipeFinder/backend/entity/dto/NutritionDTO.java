package AVeS.recipeFinder.backend.entity.dto;

import AVeS.recipeFinder.backend.entity.model.Nutrition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionDTO {

    private Double calorie;
    private Double fat;
    private Double protein;
    private Double carbohydrate;

}


