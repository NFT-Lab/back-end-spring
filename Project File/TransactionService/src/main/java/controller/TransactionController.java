package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.TransactionServiceInterface;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/TransactionService")
public class TransactionController implements TransactionControllerInterface{

	@Autowired
	private TransactionServiceInterface service;
	
	@PostMapping("/transaction/")
	@Override
	public ResponseEntity<?> buyOpera(@RequestParam String idHash, @RequestParam int userBuyer) {
		try {
			System.out.println("Prima della transaction");
			service.startTransaction(idHash, userBuyer);
			return ResponseEntity.ok("Transazione avvenuta con successo");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transazione non effettuata");
		}
	}
	@GetMapping("/transaction/")
	@Override
	public ResponseEntity<?> getOperaTransactions(@RequestParam String idHash) {
		if(idHash.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametro inserito non valido");
		}
		try {
			return ResponseEntity.ok(service.getTransactionByIdHash(idHash));
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella ricerca delle transazioni");
		}
		
	}
	
}
