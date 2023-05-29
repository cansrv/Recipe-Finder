package AVeS.recipeFinder.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long customerId;

    private Long ingredientId;

    private Double quantity;

    private String unit;
}


