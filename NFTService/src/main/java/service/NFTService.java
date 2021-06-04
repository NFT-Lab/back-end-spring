package service;

import io.nfteam.nftlab.services.*;
import io.nfteam.nftlab.services.smartcontract.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import opera.Category;
import opera.Opera;
import opera.OperaCategory;
import repository.CategoryJpaRepository;
import repository.NFTJpaRepository;
import repository.OperaCategoryRepository;
import user.UserServiceResponse;
//temp wallet = 0x5517e1498EDf8bB4B76005fD8a81697043A7E928
@Service
public class NFTService implements NFTServiceInterface {

	private NFTJpaRepository repoNFT;
	private CategoryJpaRepository repoCategory;
	private OperaCategoryRepository repoOpeCat;
	
	private NFTContractService contractService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	public NFTService(NFTJpaRepository repoNFT, CategoryJpaRepository repoCat, OperaCategoryRepository repoOpeCat, NFTContractService contractService) {
		this.repoNFT = repoNFT;
		this.repoCategory = repoCat;
		this.repoOpeCat = repoOpeCat;
		this.contractService = contractService;
	}
	@Override
	public Opera saveOpera(Opera op, MultipartFile file) throws Exception {
		//let's search for the name and surname of the owner id
		UserServiceResponse user = restTemplate.getForObject("http://user-service/nftUser/" + op.getUserId(), UserServiceResponse.class);
		try {
		      Files.copy(file.getInputStream(), Paths.get("NotDecodeImg").resolve(file.getOriginalFilename()));
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
		NFTID temp = contractService.mint(new UserTuple(user.getWallet(),BigInteger.valueOf(op.getUserId())) , new FileSystemResource("./NotDecodeImg/" + file.getName()));
		
		System.out.println("ho creato temp");
		op.setId(temp.hash());
		System.out.println("ho creato ID");
		op.setTokenId(temp.tokenId());
		System.out.println("ho creato tokenId");
		return op;
	}
	@Transactional
	@Override
	public Opera modifyOpera(Opera op) {
		System.out.println(1);
		op = repoNFT.save(op);
		int i = 0;
		System.out.println(2);
		repoOpeCat.deleteAllByIdOpera(op.getId());
		System.out.println(2.01);
		if(op.getCategory() != null) {
			while(i < op.getCategory().size()) {

				System.out.println(2.1);
				repoOpeCat.save(new OperaCategory(op.getId(), op.getCategory().get(i).getId()));
				System.out.println(2.2);
				i++;
			}
		}
		System.out.println(3);
		UserServiceResponse user = restTemplate.getForObject("http://user-service/nftUser/" + op.getUserId(), UserServiceResponse.class);
		op.setOwner(user.getName() + user.getSurname());
		return op;
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
	@Override
	public boolean checkExistsOpera(String idOpera) {
		return repoNFT.existsById(idOpera);
	}
	

}
