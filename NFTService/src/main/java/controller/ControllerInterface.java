package controller;

import org.springframework.http.ResponseEntity;

import opera.Opera;

public interface ControllerInterface {
	public  ResponseEntity<?> insertOpera(Opera opera);
}
