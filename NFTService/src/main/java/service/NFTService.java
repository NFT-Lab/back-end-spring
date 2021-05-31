package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
