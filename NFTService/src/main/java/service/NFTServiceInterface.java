package service;

import java.util.List;

import opera.Category;
import opera.Opera;

public interface NFTServiceInterface {
	public Opera saveOpera(Opera op) throws Exception;
	public Opera modifyOpera(Opera op);
	
	public Opera getOpera(String str);
	
	public List<Opera> getAllOpera();
	public List<Category> getAllCategories();
	
	public List<Opera> getAllOperaByUserId(int id);
	
	public boolean checkExistsOpera(String idOpera);
}
