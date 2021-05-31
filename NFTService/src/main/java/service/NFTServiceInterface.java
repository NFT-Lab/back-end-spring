package service;

import java.util.List;

import opera.Opera;

public interface NFTServiceInterface {
	public Opera saveOpera(Opera op) throws Exception;
	public List<Opera> getAllOpera();
	public Opera getOpera(String str);
}
