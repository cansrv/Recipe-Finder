package AVeS.recipeFinder.backend.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Inventory {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    private Double quantity;

    private String unit;

}
