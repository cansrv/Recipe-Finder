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
    @OneToMany
    private List<Rating> recipesRated;

    //@OneToMany
    //private List<Inventory> fridge;
}
