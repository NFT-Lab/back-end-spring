package controller;

import org.springframework.http.ResponseEntity;

import opera.Opera;

public interface ControllerInterface {
	public ResponseEntity<?> insertOpera(Opera opera, int id);
	public ResponseEntity<?> modifyOpera(Opera opera, int id);
	
	public ResponseEntity<?> getAllOpera();
	public ResponseEntity<?> getAllCategory();
	public ResponseEntity<?> getAllOperaByUserId(int id);
	
}
