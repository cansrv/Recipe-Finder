package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Ingredient;
import AVeS.recipeFinder.backend.entity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("select i from Inventory i " +
            "where i.customer.id = :id")
    List<Inventory>findByCustomer(@Param("id")Long id);


    @Query("select i from Inventory i " +
            "where i.customer.id = :id")
    public List<Inventory> findInventoriesByCustomerEquals(@Param("id")Long id);

    @Query("select i from Inventory i " +
            "where i.customer.id = :id and lower(i.ingredient.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Inventory> searchInventoryByIngredient(@Param("searchTerm") String searchTerm,@Param("id")Long id );

    Inventory findInventoryById(Long id);



}
