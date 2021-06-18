package service;

import io.nfteam.nftlab.services.*;
import io.nfteam.nftlab.services.smartcontract.*;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import file.FileConstruction;
import opera.Category;
import opera.EnumType;
import opera.Opera;
import opera.OperaCategory;
import repository.CategoryJpaRepository;
import repository.NFTJpaRepository;
import repository.OperaCategoryRepository;
import transaction.TransactionPayload;
import user.UserServiceResponse;
//temp wallet = 0x5517e1498EDf8bB4B76005fD8a81697043A7E928
@Service
public class NFTService implements NFTServiceInterface {

	private NFTJpaRepository repoNFT;
	private CategoryJpaRepository repoCategory;
	private OperaCategoryRepository repoOpeCat;
	
	private NFTContractService contractService;
	private FileConstruction fileConstruct = new FileConstruction();
	
	private RestTemplate restTemplate;
	
	@Autowired
	public NFTService(NFTJpaRepository repoNFT, CategoryJpaRepository repoCat, OperaCategoryRepository repoOpeCat, NFTContractService contractService, RestTemplate restTemplate) {
		this.repoNFT = repoNFT;
		this.repoCategory = repoCat;
		this.repoOpeCat = repoOpeCat;
		this.contractService = contractService;
		this.restTemplate = restTemplate;
	}
	@Override
	public Opera saveOpera(Opera op, MultipartFile file) throws Exception {
		//let's search for the name and surname of the owner id
		UserServiceResponse user = this.getUser(op.getUserId());
		//let's add the owner
		op.setOwner(user.getNameSurname());
		
		op.setAuthor(user.getNameSurname());
		
		op.setAuthorId(op.getUserId());
		
		if(op.getType() == null)
			op.setType(EnumType.img);
		String path = "gallery/" + op.getType().toString() + "/";

		ByteArrayResource arrayFile = fileConstruct.constructFile(file);
		
		NFTID temp;
		try {
			temp = contractService.mint(new UserTuple(user.getWallet(),BigInteger.valueOf(op.getUserId())) , arrayFile);	
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		op.setId(temp.hash());
		op.setTokenId(temp.tokenId());

		//save the file + set path
		try {
			path = fileConstruct.saveFile(file, path, temp.hash());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		op.setPath(path); //sistema il path fa schifo per ora ritorna soltanto gallery/img che non vuol dire nulla
		//insert category
		repoNFT.save(op);

		this.insertCategory(op);
		
		return op;
	}
	@Transactional
	@Override
	public Opera modifyOpera(Opera op) {
		Opera temp = repoNFT.getById(op.getId());
		
		if(op.getTitle().isBlank())
			op.setTitle(temp.getTitle());
		if(op.getDescription().isBlank())
			op.setDescription(temp.getDescription());
		op.setPath(temp.getPath());
		op.setAuthorId(temp.getAuthorId());
		op.setAuthor(temp.getAuthor());
		op.setTokenId(temp.getTokenId());
		op.setUserId(temp.getUserId());
		
		
		repoNFT.save(op);
		
		repoOpeCat.deleteAllByOpera(op.getId());
		
		this.insertCategory(op);
		
		op.setOwner(this.getUser(op.getUserId()).getNameSurname());
		return op;
	}
	@Override
	public List<Opera> getAllOpera() {
		List<Opera> listOpera = repoNFT.findAll();
		int i = 0;
		while( i < listOpera.size() ) {
			List<OperaCategory> listOpCat = repoOpeCat.findAllByOpera(listOpera.get(i).getId());
			int j = 0;
			List<Category> tempListCat = new ArrayList<Category>();
			while(j < listOpCat.size()) {
				tempListCat.add(repoCategory.findById(listOpCat.get(j).getIdCategory()).get());
				j++;
			}
			
			UserServiceResponse userAuthor = this.getUser(listOpera.get(i).getAuthorId());
			listOpera.get(i).setAuthor(userAuthor.getNameSurname());
			
			UserServiceResponse userOwner = this.getUser(listOpera.get(i).getUserId());
			listOpera.get(i).setOwner(userOwner.getNameSurname());
			
			listOpera.get(i).setCategories(tempListCat);
			i++;
		}
		return listOpera;
	}
	@Override
	public Opera getOpera(String str) {
		Opera opera = repoNFT.getById(str);
		//inserire anche l'autore e l'owner
		List<OperaCategory> listOpCat = repoOpeCat.findAllByOpera(opera.getId());
		int j = 0;
		List<Category> tempListCat = new ArrayList<Category>();
		while(j < listOpCat.size()) {
			tempListCat.add(repoCategory.findById(listOpCat.get(j).getIdCategory()).get());
			j++;
		}
		opera.setCategories(tempListCat);
		return opera;
	}
	@Override
	public List<Category> getAllCategories() {
		return repoCategory.findAll();
	}
	@Override
	public List<Opera> getAllOperaByUserId(int id) {
		List<Opera> listOpera = repoNFT.findAllByUserId(id);
		System.out.println("Lunghezza lista: " + listOpera.size());
		int i = 0;
		while(i < listOpera.size()) {
			List<OperaCategory> listOpCat = repoOpeCat.findAllByOpera(listOpera.get(i).getId());
			int j = 0;
			List<Category> tempListCat = new ArrayList<Category>();
			while(j < listOpCat.size()) {
				tempListCat.add(repoCategory.findById(listOpCat.get(j).getIdCategory()).get());
				j++;
			}
			UserServiceResponse userAuthor = this.getUser(listOpera.get(i).getAuthorId());
			listOpera.get(i).setAuthor(userAuthor.getNameSurname());
			UserServiceResponse userOwner = this.getUser(listOpera.get(i).getUserId());
			listOpera.get(i).setOwner(userOwner.getNameSurname());
			
			listOpera.get(i).setCategories(tempListCat);
			i++;
		}
		return listOpera;
	}
	@Override
	public boolean checkExistsOpera(String idOpera) {
		return repoNFT.existsById(idOpera);
	}
	@Override
	public void modifyOwner(TransactionPayload data) {
		Opera temp = repoNFT.getById(data.getIdHash());
		temp.setUserId(data.getIdOwner());
		repoNFT.save(temp);
		}
	
	public Resource load(String filename) {
        try {
            Path file = Paths.get("gallery/img/")
                             .resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }
	//methods for the class
	private void insertCategory(Opera op) {
		int i = 0;
		System.out.println("Sono prima del'if delle categorie");
		if(op.getCategories() != null) {
			System.out.println("Sono prima del while");
			while(i < op.getCategories().size()) {
				System.out.println("Counter i: "+i);
				System.out.println("Opera id: "+op.getId());
				System.out.println("Category id: "+op.getCategories().get(i).getId());
				repoOpeCat.save(new OperaCategory(op.getId(), op.getCategories().get(i).getId()));
				i++;
			}
		}
	}
	public UserServiceResponse getUser(int id) {
		UserServiceResponse user = restTemplate.getForObject("http://user-service/UserService/nftUser/" + id, UserServiceResponse.class);
		return user;
	}
	
}
