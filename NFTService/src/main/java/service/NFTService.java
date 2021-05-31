package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import opera.Opera;
import repository.NFTJpaRepository;

public class NFTService implements NFTServiceInterface {

	private NFTJpaRepository repo;
	@Autowired
	public NFTService(NFTJpaRepository repo) {
		this.repo = repo;
	}
	@Override
	public Opera saveOpera(Opera op) throws Exception {
		//let's search for the name and surname of the owner id
		RestTemplate restTemplate = new RestTemplate();
		op.setOwner(restTemplate.getForObject("http://user-service/nftUser" + op.getUserId(), String.class));
		
		return repo.save(op);
	}

	@Override
	public List<Opera> getAllOpera() {
		return repo.findAll();
	}
	@Override
	public Opera getOpera(String str) {
		return repo.getById(str);
	}

}
