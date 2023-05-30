package AVeS.recipeFinder.backend.entity.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDTO {

    private List<ComponentDTO> components;
    private String description;
    private int duration;
    private String name;
    private String steps;
    private List<String> tags;
    private String category;
    private NutritionDTO nutrition;




}
