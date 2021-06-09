package service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.nfteam.nftlab.services.NFTContractService;
import io.nfteam.nftlab.services.smartcontract.UserTuple;
import repository.TransactionJpaRepository;
import transaction.Transaction;
import transaction.TransactionPayload;

@Service
public class TransactionService implements TransactionServiceInterface {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private NFTContractService contractService;
	@Autowired
	private TransactionJpaRepository repo;
	@Override
	public void startTransaction(String idHash, int userBuyer) throws Exception{
		System.out.println("dentro la transaction");
		Transaction transaction = new Transaction();
		transaction.setIdHash(idHash);
		transaction.setUserBuyer(userBuyer);
		System.out.println("primo template per payload");
		TransactionPayload data = restTemplate.getForObject("http://nft-service/nft/transaction/" + transaction.getIdHash(), TransactionPayload.class);
		data.setIdHash(idHash);
		
		transaction.setUserSeller(data.getIdOwner());
		System.out.println("secondo template per walletbuyer ");
		transaction.setWalletBuyer(restTemplate.getForObject("http://user-service/user/transaction/" + transaction.getUserBuyer(), String.class));
		System.out.println("terzo template per walletseller");
		transaction.setWalletSeller(restTemplate.getForObject("http://user-service/user/transaction/" + data.getIdOwner(), String.class));
		
		transaction.setTokenId(data.getTokenId());
		transaction.setPrice(data.getPrice());
		
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
		System.out.println("prima del transfer");
		/*contractService.transfer(transaction.getTokenId(),
				new UserTuple(transaction.getWalletBuyer(), BigInteger.valueOf(transaction.getUserBuyer())), 
				new UserTuple(transaction.getWalletSeller(), BigInteger.valueOf(transaction.getUserSeller())), 
				transaction.getPrice(), 
				transaction.getTimestamp());*/
		System.out.println("dopo il transfer ");
		data.setIdOwner(userBuyer);
		System.out.println("ultimo template per modificare l'opera ");
		restTemplate.put("http://nft-service/nft/transaction/", data);
		System.out.println("salvo la transazione");
		repo.save(transaction);
		System.out.println("fine metodo");
	}
	@Override
	public List<Transaction> getTransactionByIdHash(String idhash) throws Exception{
		return repo.findAllByIdHash(idhash);
	}
	
}
