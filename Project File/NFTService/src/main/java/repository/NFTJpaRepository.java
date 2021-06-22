package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opera.Opera;
@Repository
public interface NFTJpaRepository extends JpaRepository<Opera, String>{
	public List<Opera> findAllByUserId(int id);
}
