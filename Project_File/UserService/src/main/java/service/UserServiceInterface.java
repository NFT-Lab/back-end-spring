package service;

import user.User;

public interface UserServiceInterface {
	public User addUser(User user) throws Exception;
	public User updateUserPassword(User user, String newPassword) throws Exception;
	public User updateUserData(User user) throws Exception;
	public User getUserByEmail(User user);
	public User getUserById(int id);
	
	public boolean checkEmail(User user);
	public boolean checkEmailPassword(User user);
	public boolean checkId(int id);
	public boolean checkWallet(String wallet);
	
	
	
}
