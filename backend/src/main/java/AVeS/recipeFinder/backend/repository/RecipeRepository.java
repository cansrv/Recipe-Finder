package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
