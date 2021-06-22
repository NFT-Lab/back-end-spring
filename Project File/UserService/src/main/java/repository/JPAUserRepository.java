package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.User;

@Repository
public interface JPAUserRepository extends JpaRepository<User,Integer>{
	public User findUsersByEmail(String email);
	
	public boolean existsUsersByEmail(String email);
	
	public boolean existsUsersByEmailAndPassword(String email, String password);
	
}
