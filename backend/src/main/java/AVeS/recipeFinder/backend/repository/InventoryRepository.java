package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
