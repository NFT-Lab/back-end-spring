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

import io.nfteam.nftlab.contracts.NFTLabStore;
import io.nfteam.nftlab.services.NFTContractService;
import io.nfteam.nftlab.services.NFTEthContractService;
import io.nfteam.nftlab.services.ipfs.IPFSPinataService;
import io.nfteam.nftlab.services.ipfs.IPFSService;

@Configuration
public class WebConfiguration {

    @Bean @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public NFTContractService getNFTContractService() throws Exception{
    	HttpService service = new HttpService("http://127.0.0.1:7545/");
    	
    	Web3j web3j = Web3j.build(service);
    	System.out.println("Ho creato web3j");
    	
    	Credentials cred = Credentials.create("d838d0232840cf3e2e9d6a6d2120f0ba8c7064c42fe2dac3cfd9890074e4906a");
    	System.out.println("Ho creato cred");
    	
    	ContractGasProvider cgp = new StaticGasProvider(BigInteger.valueOf(20000000000L),BigInteger.valueOf(6721975L));
    	System.out.println("Ho creato gas");
    	
    	NFTLabStore store = NFTEthContractService.loadContract("0x1609c9e3ba64afd1b96bc420ecc873f81e08695b",web3j, cred, cgp);
    	System.out.println("Ho creato store " + store.getContractAddress());
    	
    	IPFSService ipfs = new IPFSPinataService("https://api.pinata.cloud/","e46660a6e97946b6c0fc","3ed15d7caa100d83dd46aba6ec5941af8b14bda22d1169a0435c043c02db3189", new RestTemplate());
    	System.out.println("Ho creato ipfs");
    	
    	return new NFTEthContractService(
    			store,
    			ipfs);
    	
    }
}