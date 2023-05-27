package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findIngredientById(Long id);
    List <Ingredient> findIngredientsByName(String name);

    @Query("select i from Ingredient i " +
            "where lower(i.name) like lower(concat('%', :searchTerm, '%')) ")
    List <Ingredient> searchIngredientsByName(@Param("searchTerm") String searchTerm);

}
