package AVeS.recipeFinder.backend.repository;

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
    @Query("select r from Recipe r " +
            "inner join r.components c " +
            "where c.ingredient in :ingredients")
    public List<Recipe> findRecipesByComponents(@Param("ingredients") List<Ingredient> ingredients);



}
