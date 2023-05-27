package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);
}
