package service;

import java.util.List;

import transaction.Transaction;

public interface TransactionServiceInterface {
	public void startTransaction(String idHash, int userBuyer) throws Exception;
	public List<Transaction> getTransactionByIdHash(String idhash) throws Exception;
}
