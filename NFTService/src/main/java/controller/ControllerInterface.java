package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import opera.Opera;
import transaction.TransactionPayload;

public interface ControllerInterface {
	public ResponseEntity<?> insertOpera(String opera, int id, MultipartFile file);
	public ResponseEntity<?> modifyOpera(Opera opera, int id);
	
	public ResponseEntity<?> getAllOpera();
	public ResponseEntity<?> getAllCategory();
	public ResponseEntity<?> getAllOperaByUserId(int id);
	
	//methods for transaction
	public ResponseEntity<?> getUserIdByOperaId(String idHash);
	public ResponseEntity<?> modifyOwnerByTransactionPayload(TransactionPayload data);
}
