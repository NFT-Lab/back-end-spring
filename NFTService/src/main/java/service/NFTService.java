package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import opera.Category;
import opera.Opera;
import opera.OperaCategory;
import repository.CategoryJpaRepository;
import repository.NFTJpaRepository;
import repository.OperaCategoryRepository;
@Service
public class NFTService implements NFTServiceInterface {

	private NFTJpaRepository repoNFT;
	private CategoryJpaRepository repoCategory;
	private OperaCategoryRepository repoOpeCat;
	
	@Autowired
	public NFTService(NFTJpaRepository repoNFT, CategoryJpaRepository repoCat, OperaCategoryRepository repoOpeCat) {
		this.repoNFT = repoNFT;
		this.repoCategory = repoCat;
		this.repoOpeCat = repoOpeCat;
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
		List<Opera> listOpera = repoNFT.findAll();
		int i = 0;
		while( i < listOpera.size() ) {
			List<OperaCategory> listOpCat = repoOpeCat.findAllByIdOpera(listOpera.get(i).getId());
			int j = 0;
			List<Category> tempListCat = new ArrayList<Category>();
			while(j < listOpCat.size()) {
				tempListCat.add(repoCategory.findById(listOpCat.get(j).getIdCategory()).get());
				j++;
			}
			listOpera.get(i).setCategory(tempListCat);
			i++;
		}
		return listOpera;
	}
	@Override
	public Opera getOpera(String str) {
		Opera opera = repoNFT.getById(str);
		List<OperaCategory> listOpCat = repoOpeCat.findAllByIdOpera(opera.getId());
		int j = 0;
		List<Category> tempListCat = new ArrayList<Category>();
		while(j < listOpCat.size()) {
			tempListCat.add(repoCategory.findById(listOpCat.get(j).getIdCategory()).get());
			j++;
		}
		opera.setCategory(tempListCat);
		return opera;
	}
	@Override
	public List<Category> getAllCategories() {
		return repoCategory.findAll();
	}
	@Override
	public List<Opera> getAllOperaByUserId(int id) {
		List<Opera> listOpera = repoNFT.findAllByUserId(id);
		System.out.println(listOpera.get(0));
		int i = 0;
		while(i < listOpera.size()) {
			listOpera.set(i, this.getOpera(listOpera.get(i).getId()));
			i++;
		}
		return listOpera;
	}

}
