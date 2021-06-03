package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import opera.Category;
import opera.Opera;
import repository.CategoryJpaRepository;
import repository.NFTJpaRepository;

public class NFTService implements NFTServiceInterface {

	private NFTJpaRepository repoNFT;
	private CategoryJpaRepository repoCategory;
	
	@Autowired
	public NFTService(NFTJpaRepository repoNFT, CategoryJpaRepository repoCat) {
		this.repoNFT = repoNFT;
		this.repoCategory = repoCat;
	}
	@Override
	public Opera saveOpera(Opera op) throws Exception {
		//let's search for the name and surname of the owner id
		RestTemplate restTemplate = new RestTemplate();
		op.setOwner(restTemplate.getForObject("http://user-service/nftUser" + op.getUserId(), String.class));
		return repoNFT.save(op);
	}
	
	@Override
	public List<Opera> getAllOpera() {
		return repoNFT.findAll();
	}
	@Override
	public Opera getOpera(String str) {
		return repoNFT.getById(str);
	}
	@Override
	public List<Category> getAllCategories() {
		return repoCategory.findAll();
	}
	@Override
	public List<Opera> getAllOperaByUserId(int id) {
		return repoNFT.findAllByUserId(id);
	}

}
