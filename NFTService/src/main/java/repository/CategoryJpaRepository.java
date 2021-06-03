package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import opera.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {

}
