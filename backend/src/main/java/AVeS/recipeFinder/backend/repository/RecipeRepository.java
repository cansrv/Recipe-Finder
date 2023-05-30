package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Rating;
import AVeS.recipeFinder.backend.entity.model.Component;
import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    public Recipe findRecipeById(Long id);

    public Recipe findRecipeByName(String name);

    public List<Recipe> findRecipesByNameContaining(String name);

    public List<Recipe> findRecipesByCategoryContaining(String category);

    public List<Recipe> findRecipesByTagsContains(String tag);

    public List<Recipe> findRecipesByTagsIn(List<String> tags);

    public List<Recipe> findRecipesByDurationLessThanEqual(int duration);

    public List<Recipe> findRecipesByComponentsIn(List<Component> components);

    // find recipes by components that has the all the ingredients in the list
    @Query("SELECT r FROM Recipe r " +
            "INNER JOIN r.components c " +
            "WHERE c.ingredient IN :ingredients " +
            "GROUP BY r " +
            "HAVING COUNT(DISTINCT c.ingredient) = :ingredientCount")
    public List<Recipe> findRecipesByIngredients(@Param("ingredients") List<Ingredient> ingredients, @Param("ingredientCount") int ingredientCount);


    public Recipe findRecipeByRatingsContaining(Rating rating);


}
