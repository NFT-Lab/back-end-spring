package controller;

import org.springframework.http.ResponseEntity;

public interface TransactionControllerInterface {
	public ResponseEntity<?> buyOpera(String idHash, int userBuyer);
	public ResponseEntity<?> getOperaTransactions(String idHash);
}
