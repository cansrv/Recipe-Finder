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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private String mail;

    @OneToMany
    private List<Recipe> recipesCreated;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Rating> recipesRated;

    public void addRating(Rating rating) {
        this.recipesRated.add(rating);
    }

    public void removeRating(Rating rating) {
        for (Rating r: this.recipesRated) {
            if (r.getId().equals(rating.getId())) {
                this.recipesRated.remove(r);
                break;
            }
        }
    }
}
