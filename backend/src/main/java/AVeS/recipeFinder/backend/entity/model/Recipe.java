package AVeS.recipeFinder.backend.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ElementCollection
    private List<String> tags;

    private String steps;

    private String description;

    @OneToMany
    private List<Component> components;

    @OneToMany
    private List<Rating> ratings;

    private int duration;

    private String category;

    @OneToOne
    private Nutrition nutrition;

}
