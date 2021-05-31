package service;

import user.User;

public interface UserServiceInterface {
	public User addUser(User user) throws Exception;
	public User updateUserPassword(User user, String newPassword) throws Exception;
	public User updateUserData(User user) throws Exception;
	public User getUserData(User user);
	
	public boolean checkEmail(User user);
	public boolean checkEmailPassword(User user);
	
}
