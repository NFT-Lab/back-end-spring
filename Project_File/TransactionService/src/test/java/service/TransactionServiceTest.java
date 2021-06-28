package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.nfteam.nftlab.services.NFTContractService;
import repository.TransactionJpaRepository;
import transaction.Transaction;
import transaction.TransactionPayload;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	
	private TransactionService service;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private NFTContractService contractService;
	@Mock
	private TransactionJpaRepository repo;
	
	@BeforeEach
	public void init() {
		service = new TransactionService(restTemplate, contractService, repo);
		
	}
	
	@Test
	public void startTransactionTest() throws Exception {
		TransactionPayload testTransaction = new TransactionPayload(0,"test", BigInteger.ONE, "testOpera");
		
		Mockito.lenient().when(restTemplate.getForObject(
				"http://nft-service/NFTService/nft/transaction/test", 
				TransactionPayload.class)).thenReturn(testTransaction);
		
		Mockito.lenient().when(restTemplate.getForObject(
				"http://user-service/UserService/user/transaction/buyerTest", 
				String.class)).thenReturn("buyerTest");
		
		Mockito.lenient().when(restTemplate.getForObject(
				"http://user-service/UserService/user/transaction/sellerTest", 
				String.class)).thenReturn("sellerTest");
		
		service.startTransaction("test", 2);
		assertThat(true);
	}
}
