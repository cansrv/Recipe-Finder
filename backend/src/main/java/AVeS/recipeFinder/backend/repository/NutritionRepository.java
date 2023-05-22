package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
