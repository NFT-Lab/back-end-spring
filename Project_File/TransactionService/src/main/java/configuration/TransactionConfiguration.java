package configuration;

import java.math.BigInteger;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import io.nfteam.nftlab.contracts.NFTLabStoreEthereum;
import io.nfteam.nftlab.services.NFTContractService;
import io.nfteam.nftlab.services.NFTETHContractService;
import io.nfteam.nftlab.services.ipfs.IPFSPinataService;
import io.nfteam.nftlab.services.ipfs.IPFSService;

@Configuration
public class TransactionConfiguration {
	@Bean @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	 @Bean
	    public NFTContractService getNFTContractService() throws Exception{
		 HttpService service = new HttpService("http://127.0.0.1:7545/");
	    	
	    	Web3j web3j = Web3j.build(service);
	    	System.out.println("Ho creato web3j");
	    	
	    	Credentials cred = Credentials.create("ea0a035dc7c5e70719d2062dd09bf3c93f94f4d4c0837e78a6ce0316a050502e");
	    	System.out.println("Ho creato cred");
	    	
	    	ContractGasProvider cgp = new StaticGasProvider(BigInteger.valueOf(20000000000L),BigInteger.valueOf(6721975L));
	    	System.out.println("Ho creato gas");
	    	
	    	NFTLabStoreEthereum store = NFTETHContractService.load("0x5c4533a6a0e0ccb5cb7d81bb238e9f1c592fed7c",web3j, cred, cgp);
	    	System.out.println("Ho creato store " + store.getContractAddress());
	    	
	    	IPFSService ipfs = new IPFSPinataService("https://api.pinata.cloud/","e46660a6e97946b6c0fc","3ed15d7caa100d83dd46aba6ec5941af8b14bda22d1169a0435c043c02db3189", new RestTemplate());
	    	System.out.println("Ho creato ipfs");
	    	
	    	return new NFTETHContractService(
	    			store,
	    			ipfs);
	    	
	    }
}
