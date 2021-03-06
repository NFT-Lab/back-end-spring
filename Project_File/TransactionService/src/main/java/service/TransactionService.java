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

	private RestTemplate restTemplate;
	
	private NFTContractService contractService;
	
	private TransactionJpaRepository repo;
	@Autowired
	public TransactionService(RestTemplate restTemplate, NFTContractService contractService, TransactionJpaRepository repo) {
		this.restTemplate = restTemplate;
		this.contractService = contractService;
		this.repo = repo;
	}
	
	@Override
	public void startTransaction(String idHash, int userBuyer) throws Exception{
		Transaction transaction = new Transaction();
		transaction.setIdHash(idHash);
		transaction.setUserBuyer(userBuyer);
		TransactionPayload data = restTemplate.getForObject("http://nft-service/NFTService/nft/transaction/" + transaction.getIdHash(), TransactionPayload.class);
		
		data.setIdHash(idHash);
		
		System.out.println("IDhash "+ data.getIdHash());
		System.out.println("IdOwner "+ data.getIdOwner());
		System.out.println("Price "+ data.getPrice());
		System.out.println("TokenId "+ data.getTokenId());
		
		transaction.setUserSeller(data.getIdOwner());
		
		transaction.setWalletBuyer(restTemplate.getForObject("http://user-service/UserService/user/transaction/" + transaction.getUserBuyer(), String.class));
		
		System.out.println("Wallet buyer "+ transaction.getWalletBuyer());
		
		transaction.setWalletSeller(restTemplate.getForObject("http://user-service/UserService/user/transaction/" + data.getIdOwner(), String.class));
		
		System.out.println("Wallet Seller "+ transaction.getWalletSeller());
		
		transaction.setTokenId(data.getTokenId());
		
		transaction.setPrice(data.getPrice());
		
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
		System.out.println("prima del transfer");
		
		try {
			contractService.transfer(transaction.getTokenId(),
					new UserTuple(transaction.getWalletSeller(), BigInteger.valueOf(transaction.getUserSeller())), 
					new UserTuple(transaction.getWalletBuyer(), BigInteger.valueOf(transaction.getUserBuyer())), 
					transaction.getPrice(), 
					transaction.getTimestamp());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("dopo il transfer ");
		data.setIdOwner(userBuyer);
		
		restTemplate.put("http://nft-service/NFTService/nft/transaction/", data);
		
		repo.save(transaction);
		
	}
	@Override
	public List<Transaction> getTransactionByIdHash(String idhash) throws Exception{
		return repo.findAllByIdHash(idhash);
	}
	
}
