package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transaction.Transaction;

@Repository
public interface TransactionJpaRepository extends JpaRepository<Transaction, Integer> {
	public List<Transaction> findAllByIdHash(String idhash);
}
