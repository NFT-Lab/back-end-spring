package configuration;

import java.math.BigInteger;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import io.github.cdimascio.dotenv.Dotenv;
import io.nfteam.nftlab.contracts.NFTLabStore;
import io.nfteam.nftlab.services.NFTContractService;
import io.nfteam.nftlab.services.NFTETHContractService;
import io.nfteam.nftlab.services.ipfs.IPFSPinataService;
import io.nfteam.nftlab.services.ipfs.IPFSService;

@Configuration
//@EnableResourceServer
public class ServerConfiguration /*extends ResourceServerConfigurerAdapter*/{
/*
	@Override
    public void configure(HttpSecurity http) throws Exception {         
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests().antMatchers("/**").permitAll();
    }*/
    @Bean @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public NFTContractService getNFTContractService() throws Exception{
    	Dotenv dotenv = Dotenv.load();
    	//da qui 
    	
    	HttpService service = new HttpService(dotenv.get("ETHEREUM_URL"));
    	
    	Web3j web3j = Web3j.build(service);
    	System.out.println("Ho creato web3j");
    	
    	Credentials cred = Credentials.create(dotenv.get("ETHEREUM_PRIVATE_KEY"));
    	System.out.println("Ho creato cred");
    	
    	ContractGasProvider cgp = new StaticGasProvider(BigInteger.valueOf(20000000000L),BigInteger.valueOf(6721975L));
    	System.out.println("Ho creato gas");
    	
    	NFTLabStore store = NFTETHContractService.loadContract(dotenv.get("ETHEREUM_CONTRACT_ADDRESS"),web3j, cred, cgp);
    	System.out.println("Ho creato store " + store.getContractAddress());
    	//a qui
    	IPFSService ipfs = new IPFSPinataService(dotenv.get("PINATA_BASE_URL"), dotenv.get("PINATA_API_KEY"), dotenv.get("PINATA_SECRET_KEY"), new RestTemplate());
    	System.out.println("Ho creato ipfs");
    	
    	return new NFTETHContractService(
    			store,
    			ipfs);
    	
    }
}