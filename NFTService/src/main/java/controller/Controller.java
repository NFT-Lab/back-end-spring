package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import opera.Opera;
import service.NFTService;
import transaction.TransactionPayload;

@RestController
//@CrossOrigin(origins = "http://localhost:8080", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/NFTService")
public class Controller implements ControllerInterface {
	
	private NFTService service;
	
	@Autowired
	public Controller(NFTService service) {
		this.service = service;
	}

	@PostMapping(value = "/nft/user/{userId}", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@Override
	public ResponseEntity<?> insertOpera(@RequestParam("opera") String opera, @PathVariable("userId")int id, @RequestPart("file")MultipartFile file) {
		System.out.println(opera);
		Opera operaFromJson = new Opera();

		try {
		    ObjectMapper objMap = new ObjectMapper();
		    operaFromJson = objMap.readValue(opera, Opera.class);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		try {
			operaFromJson.setUserId(id);
			operaFromJson = service.saveOpera(operaFromJson,file);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è riscontrato un errore nel salvataggio dell'opera");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(operaFromJson);
	}
	
	@PutMapping("/nft/user/{userId}")
	@Override
	public ResponseEntity<?> modifyOpera(@RequestBody Opera opera, @PathVariable("userId")int id) {
		if(!(service.checkExistsOpera(opera.getId()))) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Opera non trovata");
		}
		try {
			opera = service.modifyOpera(opera);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è vericato un errore durante la modifica dell'opera");
		}
		return ResponseEntity.ok(opera);
	}
	@GetMapping("/nft")
	@Override
	public ResponseEntity<?> getAllOpera() {
		return ResponseEntity.ok(service.getAllOpera());
	}
	@GetMapping("/categories")
	@Override
	public ResponseEntity<?> getAllCategory() {
		return ResponseEntity.ok(service.getAllCategories());
	}
	@GetMapping("/nft/user/{userId}")
	@Override
	public ResponseEntity<?> getAllOperaByUserId(@PathVariable("userId")int id) {
		return ResponseEntity.ok(service.getAllOperaByUserId(id));
	}
	//url Back-end local..8765/NFTServi
	@GetMapping("/gallery/{filename:.+}")
	public ResponseEntity<?> getFileOpera(@PathVariable String filename){
		Resource file = service.load(filename);
		System.out.println("Ho recuperato il file tra poco lo invio");
		return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
	}
	
	//methods for transaction
	@GetMapping("/nft/transaction/{idHash}")
	@Override
	public ResponseEntity<?> getUserIdByOperaId(@PathVariable("idHash") String idHash){
		TransactionPayload data = new TransactionPayload();
		Opera temp = service.getOpera(idHash);
		data.setIdOwner(temp.getUserId());
		data.setPrice(temp.getPrice()+temp.getCurrency());
		data.setTokenId(temp.getTokenId());
		System.out.println(temp.getTokenId());
		return ResponseEntity.ok().body(data);
	}
	@PutMapping("/nft/transaction")
	@Override
	public ResponseEntity<?> modifyOwnerByTransactionPayload(@RequestBody TransactionPayload data) {
		try {
			service.modifyOwner(data);
			return ResponseEntity.ok("Proprietario modificato con successo");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore nella modifica del proprietario");
		}
	}
}
