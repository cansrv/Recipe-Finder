package AVeS.recipeFinder.backend.DTO;

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
}
