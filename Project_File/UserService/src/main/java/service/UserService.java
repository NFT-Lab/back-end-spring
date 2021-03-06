package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.JPAUserRepository;
import user.User;
@Service
public class UserService implements UserServiceInterface {
	
	private final JPAUserRepository repo;
	@Autowired
	public UserService(JPAUserRepository repo) {
		this.repo = repo;
	}
	//operation method for users --------------------------------------------------------
	
	//method to Add new user into the database
	@Override
	public User addUser(User user) throws Exception {
		user.setPassword(Integer.toString(user.getPassword().hashCode()));
		User userData = repo.save(user);
		userData.setId(repo.findUsersByEmail(user.getEmail()).getId());
		return userData;
	}
	//method to Update old password of user with a newer password
	@Override
	public User updateUserPassword(User user, String newPassword) throws Exception {
		user = repo.findUsersByEmail(user.getEmail());
		user.setPassword(newPassword);
		return this.addUser(user);
	}
	//method to update user data into to the database
	@Override
	public User updateUserData(User user) throws Exception {
		user.setId(repo.findUsersByEmail(user.getEmail()).getId());
		return this.addUser(user);
	}
	@Override
	public User getUserByEmail(User user) {
		return repo.findUsersByEmail(user.getEmail());
	}
	@Override
	public User getUserById(int id) {
		return repo.findById(id).get();
	}
	//check methods ------------------------------------------------------------
	@Override
	public boolean checkEmail(User user){
		return repo.existsUsersByEmail(user.getEmail());
	}
	@Override
	public boolean checkEmailPassword(User user) {
		user.setPassword(Integer.toString(user.getPassword().hashCode()));
		boolean temp = repo.existsUsersByEmailAndPassword(user.getEmail(), user.getPassword());
		return temp;
	}
	@Override
	public boolean checkId(int id) {
		return repo.existsById(id);
	}
	@Override
	public boolean checkWallet(String wallet) {
		if(wallet.length() != 42 ) {
			return true;
		}
		return false;
	}
	
	
}
