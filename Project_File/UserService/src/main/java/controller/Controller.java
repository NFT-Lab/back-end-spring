package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.UserServiceInterface;
import user.NFTUserRequest;
import user.User;
import user.UserPayload;

@RestController
@RequestMapping("/UserService")
public class Controller implements ControllerInterface {

	private UserServiceInterface service;
	@Autowired
	public Controller(UserServiceInterface service) {
		this.service = service;
	}
	
	//methods login/signUp -----------------------------------------------------------------------
	/*methods for login -> response:
		- Correct finding of user = HttpStatus.OK + user data
		- Incorrect value request = HttpStatus.badRequest + error message
		- Incorrect email/password = HttpStatus.notFound + error message
	*/
	@PostMapping("/login")
	@Override
	public ResponseEntity<?> login(@RequestBody User user){
		if(user.getEmail().isBlank() || user.getPassword().isBlank()) {
			return ResponseEntity.badRequest().body("Email o Password Vuote");
		}
		if(service.checkEmailPassword(user)) {
			return ResponseEntity.ok().body(service.getUserByEmail(user));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utente non esiste");
	}
	/*methods for signup -> response:
		- Correct insertion of new user = HttpStatus.created + user data
		- User already in the database = HttpStatus.conflict + error message
		- Incorrect value of wallet = HttpStatus.conflict + error message
		- Error insertion of new user = HttpStatus.internalServerError + error message
	 */
	@PostMapping("/signup")
	@Override
	public ResponseEntity<?> signUp(@RequestBody User user) {
		if(service.checkEmail(user)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("L'utente è già presente");
		}
		if(service.checkWallet(user.getWallet()) && user.getWallet()!=null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Il wallet non è conforme allo standard");
		}
		User tempUser;
		try {
			tempUser = service.addUser(user);
		}catch(Exception e){ 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella creazione dell'utente");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(tempUser);
	}
	//methods change user data --------------------------------------------------------------------
	/*methods for change password-> response:
		- Correct update of password = HttpStatus.ok + user data
		- User not found = HttpStatus.notFound + error message
		- Incorrect userPayload value = HttpStatus.badRequest + error message
		- Error update password = HttpStatus.expectationFailed + error message
	 */
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
	/*methods for change user data-> response:
		- Correct update of data = HttpStatus.ok + user data
		- User not found = HttpStatus.notFound + error message
		- Error update data = HttpStatus.expectationFailed + error message
	 */
	@PutMapping("/user/{id}")
	@Override
	public ResponseEntity<?> modifyData(@RequestBody User user,@PathVariable("id") int id){
		if(service.checkEmail(user) && service.checkId(id)) {
			User tempUser;
			try {
				user.setId(id);
				tempUser = service.updateUserData(user);
			}catch(Exception e){
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
			}
			return ResponseEntity.ok().body(tempUser);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//methods for NFTService -------------------------------------------------------------------------
	@GetMapping("/nftUser/{id}")
	@Override
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		NFTUserRequest user = new NFTUserRequest(service.getUserById(id));
		return ResponseEntity.ok().body(user);
	}
	@GetMapping("/user/transaction/{id}")
	@Override
	public ResponseEntity<?> getUserWalletById(@PathVariable("id") int id){
		return ResponseEntity.ok().body(service.getUserById(id).getWallet());
	}
	
}
