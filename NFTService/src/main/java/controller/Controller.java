package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import opera.Opera;
import service.NFTService;
@RestController
public class Controller implements ControllerInterface {
	
	private NFTService service;
	
	@Autowired
	public Controller(NFTService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/nft/user/{userId}", consumes = "APPLICATION/MULT_PART_FORM_DATA")
	@Override
	public ResponseEntity<?> insertOpera(@RequestPart() Opera opera, @PathVariable("userId")int id) {
		if(opera.getEncoding().isBlank() || id != opera.getUserId()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Alcune informazioni fondamentali sono mancanti");
		}
		try {
			opera.setUserId(id);
			service.saveOpera(opera);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è riscontrato un errore nel salvataggio dell'opera");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(opera);
	}
	
	@PutMapping("/nft/user/{userId}")
	@Override
	public ResponseEntity<?> modifyOpera(@RequestBody Opera opera, @PathVariable("userId")int id) {
		if(opera.getUserId() != id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'opera non corrisponde all'utente");
		}
		if(!(service.checkExistsOpera(opera.getId()))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Opera non trovata");
		}
		try {
			opera = service.modifyOpera(opera);
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è vericato un errore durante la modifica dell'opera");
		}
		return ResponseEntity.ok(opera);
	}
	@GetMapping("/nft")
	@Override
	public ResponseEntity<?> getAllOpera() {
		return ResponseEntity.ok(service.getAllOpera());
	}
	@GetMapping("/categories")
	@Override
	public ResponseEntity<?> getAllCategory() {
		return ResponseEntity.ok(service.getAllCategories());
	}
	@GetMapping("/nft/user/{userId}")
	@Override
	public ResponseEntity<?> getAllOperaByUserId(@PathVariable("userId")int id) {
		return ResponseEntity.ok(service.getAllOperaByUserId(id));
	}
}
