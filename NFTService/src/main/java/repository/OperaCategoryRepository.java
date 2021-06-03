package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opera.OperaCategory;
@Repository
public interface OperaCategoryRepository extends JpaRepository<OperaCategory, Integer>{
	public List<OperaCategory> findAllByIdOpera(String idOpera);
}
