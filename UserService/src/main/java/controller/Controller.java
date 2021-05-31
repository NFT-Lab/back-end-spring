package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.UserService;
import user.User;

@RestController
public class Controller implements ControllerInterface {

	private UserService service;
	@Autowired
	public Controller(UserService service) {
		this.service = service;
	}
	//methods login/signUp -----------------------------------------------------------------------
	@PostMapping("/login")
	@Override
	public ResponseEntity<?> login(@RequestBody User user){
		if(user.getEmail().isBlank() || user.getPassword().isBlank()) return ResponseEntity.badRequest().body("Email o Password Vuote");
		if(service.checkEmailPassword(user)) {
			return ResponseEntity.ok().body(service.getUserData(user));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("L'utente non esiste");
	}
	@PostMapping("/signup")
	@Override
	public ResponseEntity<?> signUp(@RequestBody User user) {
		if(service.checkEmail(user)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		User tempUser;
		try {
			tempUser = service.addUser(user);
		}catch(Exception e){ //placeholder per prendere l'eccezione se il salvataggio dello user non va a buon fine
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(tempUser);
	}
	//methods change user data --------------------------------------------------------------------
	@PutMapping("/change/password")
	@Override
	public ResponseEntity<?> changePassword(@RequestBody User user, @RequestParam(name = "newPassword") String newPassword) {
		if(service.checkEmailPassword(user)) {
			User tempUser;
			try {
				tempUser = service.updateUserPassword(user, newPassword);
				return ResponseEntity.ok().body(tempUser);
				
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/change/data")
	@Override
	public ResponseEntity<?> modifyData(@RequestBody User user){
		if(service.checkEmail(user)) {
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
	
}