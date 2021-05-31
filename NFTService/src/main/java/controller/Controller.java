package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import opera.Opera;
import service.NFTService;

public class Controller implements ControllerInterface {
	
	private NFTService service;
	
	@Autowired
	public Controller(NFTService service) {
		this.service = service;
	}
	
	@PostMapping("/insertOpera")
	@Override
	public ResponseEntity<?> insertOpera(Opera opera){
		
		return null;
	}
}
