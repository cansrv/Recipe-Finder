package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
}
