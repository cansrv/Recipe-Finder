package AVeS.recipeFinder.backend.entity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    private String steps;

    // set the length of the description to 1000 characters
    @Column(length = 3000)
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
