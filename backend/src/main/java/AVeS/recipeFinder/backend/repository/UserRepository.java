package AVeS.recipeFinder.backend.repository;

import AVeS.recipeFinder.backend.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
