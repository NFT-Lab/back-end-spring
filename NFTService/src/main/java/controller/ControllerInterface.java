package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import opera.Opera;

public interface ControllerInterface {
	public ResponseEntity<?> insertOpera(Opera opera, int id, MultipartFile file);
	public ResponseEntity<?> modifyOpera(Opera opera, int id);
	
	public ResponseEntity<?> getAllOpera();
	public ResponseEntity<?> getAllCategory();
	public ResponseEntity<?> getAllOperaByUserId(int id);
	
}
