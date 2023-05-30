package AVeS.recipeFinder.backend.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double calorie;
    private Double fat;
    private Double protein;
    private Double carbohydrate;

    public Nutrition ( List<Nutrition> nutrition) {
        this.calorie = 0.0;
        this.fat = 0.0;
        this.protein = 0.0;
        this.carbohydrate = 0.0;
        for (Nutrition n: nutrition) {
            this.calorie += n.getCalorie();
            this.fat += n.getFat();
            this.protein += n.getProtein();
            this.carbohydrate += n.getCarbohydrate();
        }
    }
}
