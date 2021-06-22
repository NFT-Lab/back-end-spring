package service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import opera.Category;
import opera.Opera;
import transaction.TransactionPayload;

public interface NFTServiceInterface {
	public Opera saveOpera(Opera op, MultipartFile file) throws Exception;
	public Opera modifyOpera(Opera op);
	
	public Opera getOpera(String str);
	
	public List<Opera> getAllOpera();
	public List<Category> getAllCategories();
	
	public List<Opera> getAllOperaByUserId(int id);
	
	public boolean checkExistsOpera(String idOpera);
	
	public void modifyOwner(TransactionPayload data);
}
