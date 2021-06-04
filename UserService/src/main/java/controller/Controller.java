package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import service.UserServiceInterface;
import user.NFTUserRequest;
import user.User;
import user.UserPayload;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class Controller implements ControllerInterface {

	@Autowired
	private UserServiceInterface service;
	
	//methods login/signUp -----------------------------------------------------------------------
	@PostMapping("/login")
	@Override
	public ResponseEntity<?> login(@RequestBody User user){
		if(user.getEmail().isBlank() || user.getPassword().isBlank()) {
			return ResponseEntity.badRequest().body("Email o Password Vuote");
		}
		if(service.checkEmailPassword(user)) {
			return ResponseEntity.ok().body(service.getUserByEmail(user));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("L'utente non esiste");
	}
	@PostMapping("/signup")
	@Override
	public ResponseEntity<?> signUp(@RequestBody User user) {
		if(service.checkEmail(user)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("L'utente è già presente");
		}
		if(service.checkWallet(user.getWallet())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Il wallet non è conforme allo standard");
		}
		
		User tempUser;
		try {
			tempUser = service.addUser(user);
		}catch(Exception e){ //placeholder per prendere l'eccezione se il salvataggio dello user non va a buon fine
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella creazione dell'utente");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(tempUser);
	}
	//methods change user data --------------------------------------------------------------------
	@PutMapping("/user/password")
	@Override
	public ResponseEntity<?> changePassword(@RequestBody UserPayload userPayload) {
		if(userPayload.getEmail().isBlank() || userPayload.getOldPassword().isBlank() || userPayload.getNewPassword().isBlank()) {
			return ResponseEntity.badRequest().body("Dati inseriti sbagliati");
		}
		User user = new User();
		user.setEmail(userPayload.getEmail());
		user.setPassword(userPayload.getOldPassword());
		if(service.checkEmailPassword(user)) {
			User tempUser;
			try {
				tempUser = service.updateUserPassword(user, userPayload.getNewPassword());
				return ResponseEntity.ok().body(tempUser);
				
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/user/{id}")
	@Override
	public ResponseEntity<?> modifyData(@RequestBody User user,@PathVariable("id") int id){
		if(service.checkEmail(user) && service.checkId(id)) {
			User tempUser;
			try {
				tempUser = service.updateUserData(user);
			}catch(Exception e){ //placeholder per prendere l'eccezione se il salvataggio dello user non va a buon fine
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}
			return ResponseEntity.ok().body(tempUser);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	//methods for NFTService -------------------------------------------------------------------------
	@GetMapping("/nftUser/{id}")
	@Override
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		NFTUserRequest user = new NFTUserRequest(service.getUserById(id));
		return ResponseEntity.ok().body(user);
	}
	
}
