package AVeS.recipeFinder.backend.entity.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {

    private Long customerId;

    private Long ingredientId;

    private Double quantity;

    private String unit;
}


