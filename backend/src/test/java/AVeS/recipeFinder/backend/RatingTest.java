package AVeS.recipeFinder.backend;

import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.entity.model.Rating;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.repository.CustomerRepository;
import AVeS.recipeFinder.backend.repository.RatingRepository;
import AVeS.recipeFinder.backend.service.CustomerService;
import AVeS.recipeFinder.backend.service.RatingService;
import AVeS.recipeFinder.backend.service.RecipeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RatingTest {
    @Resource
    private RatingRepository ratingRepository;
    @Resource
    private RecipeService recipeService;
    @Resource
    private CustomerService customerService;
    @Resource
    private RatingService ratingService;

    @Test
    void testCRUD() {
        Customer customer = new Customer();
        customer.setUsername("elifcenesiz");
        customerService.addCustomer(customer);
        Recipe recipe = new Recipe();
        recipeService.addRecipe(recipe);

        // Add

        Rating r = new Rating(recipeService.getAllRecipes().get(0).getId(), 5.0, "nice");
        ratingService.addRating(r, "elifcenesiz", recipe.getId());
        assertEquals(ratingRepository.findAll().get(0).getRating(), 5.0);
        assertEquals(ratingRepository.findAll().get(0).getComment(), "nice");
        customer = customerService.findCustomerByUsername("elifcenesiz");
        recipe = recipeService.getAllRecipes().get(0);
        assertEquals(customer.getRecipesRated().get(0).getRating(), recipe.getRatings().get(0).getRating());

        // Update
        r.setRating(4.0);
        r.setComment("not bad");
        ratingService.updateRating(r);
        assertEquals(ratingRepository.findAll().get(0).getRating(), 4.0);
        assertEquals(ratingRepository.findAll().get(0).getComment(), "not bad");
        customer = customerService.findCustomerByUsername("elifcenesiz");
        recipe = recipeService.getAllRecipes().get(0);
        assertEquals(customer.getRecipesRated().get(0).getRating(), r.getRating());
        assertEquals(recipe.getRatings().get(0).getRating(), r.getRating());

        // Delete
        ratingService.removeRating(ratingRepository.findAll().get(0));
        customer = customerService.findCustomerByUsername("elifcenesiz");
        recipe = recipeService.getAllRecipes().get(0);
        assertEquals(ratingRepository.findAll().size(), 0);
        assertEquals(customer.getRecipesRated().size(), 0);
        assertEquals(recipe.getRatings().size(), 0);

    }

}
