package controller;

import org.springframework.http.ResponseEntity;

import user.User;
import user.UserPayload;

public interface ControllerInterface {
	public ResponseEntity<?> signUp(User user);
	public ResponseEntity<?> login(User user);
	public ResponseEntity<?> changePassword(UserPayload userPayload);
	public ResponseEntity<?> modifyData(User user, int id);
}
