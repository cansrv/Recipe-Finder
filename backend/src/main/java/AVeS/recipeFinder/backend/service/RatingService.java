package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.entity.model.Rating;
import AVeS.recipeFinder.backend.entity.model.Recipe;
import AVeS.recipeFinder.backend.repository.CustomerRepository;
import AVeS.recipeFinder.backend.repository.RatingRepository;
import AVeS.recipeFinder.backend.repository.RecipeRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Resource
    RatingRepository ratingRepository;
    @Resource
    CustomerRepository customerRepository;
    @Resource
    RecipeRepository recipeRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, CustomerRepository customerRepository) {
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
    }

    public void addRating(Long recipeId, String customerName, Double rating, String comment) {
        Rating r = new Rating(0L, rating, comment);
        ratingRepository.save(r);
        Customer c = customerRepository.findByUsername(customerName);
        c.addRating(r);
        customerRepository.save(c);
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.addRating(r);
        recipeRepository.save(recipe);
    }

    public void addRating(Rating r, String customerName, Long recipeId) {
        ratingRepository.save(r);
        Customer c = customerRepository.findByUsername(customerName);
        c.addRating(r);
        customerRepository.save(c);
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.addRating(r);
        recipeRepository.save(recipe);

    }

    public void removeRating(Rating r) {
        Customer customer = customerRepository.findCustomerByRecipesRatedContaining(r);
        Recipe recipe = recipeRepository.findRecipeByRatingsContaining(r);
        customer.removeRating(r);
        recipe.removeRating(r);
        customerRepository.save(customer);
        recipeRepository.save(recipe);
        ratingRepository.delete(r);

    }

    public void updateRating(Rating r) {
        ratingRepository.save(r);
    }
}
