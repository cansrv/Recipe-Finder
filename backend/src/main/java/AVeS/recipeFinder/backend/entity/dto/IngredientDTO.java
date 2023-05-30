package AVeS.recipeFinder.backend.entity.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    private String name;
    private String description;
    private String shelfLife;

    private NutritionDTO nutrition;
}
