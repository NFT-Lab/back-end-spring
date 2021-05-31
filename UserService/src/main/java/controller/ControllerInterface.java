package controller;

import org.springframework.http.ResponseEntity;

import user.User;

public interface ControllerInterface {
	public ResponseEntity<?> signUp(User user);
	public ResponseEntity<?> login(User user);
	public ResponseEntity<?> changePassword(User user, String newPassword);
	public ResponseEntity<?> modifyData(User user);
}
