package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    public Recipe findRecipeByName(String name);

    public List<Recipe> findRecipesByNameContaining(String name);

    public List<Recipe> findRecipesByCategoryContaining(String category);

    public List<Recipe> findRecipesByTagsContains(String tag);

    public List<Recipe> findRecipesByTagsIn(List<String> tags);

    public List<Recipe> findRecipesByDurationLessThanEqual(int duration);



}
