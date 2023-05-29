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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    private String steps;

    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Component> components;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Rating> ratings;

    private int duration;

    private String category;

    @OneToOne(fetch = FetchType.EAGER)
    private Nutrition nutrition;

    public void addRating(Rating r) {
        ratings.add(r);
    }

    public void removeRating(Rating r) {
        for (Rating rating: ratings) {
            if (rating.getId().equals(r.getId())) {
                ratings.remove(rating);
                break;
            }
        }
    }
}
