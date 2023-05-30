package AVeS.recipeFinder.backend.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComponentDTO {

    private Long ingredientId;

    private Double quantity;

    private String unit;
}
