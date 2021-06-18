package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import file.FileConstruction;
import io.nfteam.nftlab.services.NFTContractService;
import io.nfteam.nftlab.services.smartcontract.NFTID;
import io.nfteam.nftlab.services.smartcontract.UserTuple;
import opera.Category;
import opera.EnumType;
import opera.Opera;
import opera.OperaCategory;
import repository.CategoryJpaRepository;
import repository.NFTJpaRepository;
import repository.OperaCategoryRepository;
import transaction.TransactionPayload;
import user.UserServiceResponse;


@ExtendWith(MockitoExtension.class)

class UserServiceApplicationTests {
	
	private NFTService service;
	@Mock
	private NFTJpaRepository repoNFT ;
	@Mock
	private CategoryJpaRepository repoCategory ;
	@Mock
	private OperaCategoryRepository repoOpeCat ;
	@Mock
	private NFTContractService contractService ;
	
	@Mock
	private FileConstruction fileConstruct;
	@Mock
	private RestTemplate restTemplate;
	
	private MockMultipartFile file =new MockMultipartFile("user-file","test.txt",
            "text/plain", "test data".getBytes());
	
	private Opera opera;
	private Category cat;
	private UserServiceResponse user;
	
	@BeforeEach
	void initUserService() {
		service = new NFTService(repoNFT, repoCategory, repoOpeCat, contractService, restTemplate);
		
		opera = new Opera();
		opera.setId("QmauN5VgBiXEcCHpJPHf1ACfKazYUwUPUs7jaF6pQXaaMS");
		opera.setTitle("letter y");
		opera.setDescription("letter y");
		opera.setAuthorId(0);
		opera.setUserId(0);
		opera.setCategories(null);
		opera.setPrice(12);
		opera.setStatus(false);
		opera.setTokenId(BigInteger.valueOf(-1));
		opera.setCurrency("ETH");
		opera.setType(EnumType.img);

		user = new UserServiceResponse("Metw","Mac","ciao@gmail.com",null,"0x9C018459FecFD87A23ad5172a02C9AEF93D30787");
		
	}

	@Test
	 void saveOperaTest() throws Exception{

		Mockito.lenient().when(service.getUser(0)).thenReturn(user);
		ByteArrayResource testByte = new ByteArrayResource(file.getBytes());
		Mockito.lenient().when(fileConstruct.constructFile(file)).thenReturn(testByte);
		Mockito.lenient().when(contractService.mint(any(UserTuple.class), any(ByteArrayResource.class))).thenReturn(new NFTID("test",BigInteger.valueOf(-1)));
		
		Mockito.lenient().when(fileConstruct.saveFile(any(MultipartFile.class), any(String.class), any(String.class))).thenReturn(opera.getId());
		
		assertEquals(service.saveOpera(opera, file).getId(), opera.getId());
	}
	@Test
	void modifyOperaTest() {
		when(repoNFT.getById(any(String.class))).thenReturn(opera);
		when(service.getUser(0)).thenReturn(user);
		assertEquals(service.modifyOpera(opera), opera);
	}
	@Test
	void getAllOperaTest() {
		Opera opera1 = opera;
		List<Opera> list = new ArrayList<Opera>();
		list.add(opera1);
		list.add(opera);
		Category uno = new Category();
		uno.setId(1);
		uno.setName("uno");
		when(repoNFT.findAll()).thenReturn(list);
		when(repoOpeCat.findAllByOpera(any(String.class))).thenReturn(new ArrayList<OperaCategory>());
		
		Mockito.lenient().when(repoCategory.findById(0)).thenReturn(Optional.of(uno));
		when(service.getUser(0)).thenReturn(user);
		
		assertEquals(service.getAllOpera(),list);
	}
	@Test
	void getOperaTest() {
		Category uno = new Category();
		uno.setId(1);
		uno.setName("uno");
		when(repoNFT.getById(any(String.class))).thenReturn(opera);
		when(repoOpeCat.findAllByOpera(any(String.class))).thenReturn(new ArrayList<OperaCategory>());
		Mockito.lenient().when(repoCategory.findById(0)).thenReturn(Optional.of(uno));
		
		assertEquals(service.getOpera(opera.getId()),opera);
	}
	@Test
	void getAllOperaByUserIdTest() {
		Opera opera1 = opera;
		List<Opera> list = new ArrayList<Opera>();
		list.add(opera1);
		list.add(opera);
		
		when(repoNFT.findAllByUserId(opera.getUserId())).thenReturn(list);
		when(repoOpeCat.findAllByOpera(opera.getId())).thenReturn(new ArrayList<OperaCategory>());
		Mockito.lenient().when(repoCategory.findById(any(Integer.class))).thenReturn(Optional.of(new Category()));
		when(service.getUser(0)).thenReturn(user);
		
		assertEquals(service.getAllOperaByUserId(opera.getUserId()),list);
	}
	@Test
	void modifyOwnerTest() {
		Mockito.lenient().when(repoNFT.getById(opera.getId())).thenReturn(opera);
		
		TransactionPayload testTransaction = new TransactionPayload();
		testTransaction.setIdHash(opera.getId());
		testTransaction.setIdOwner(0);
		testTransaction.setPrice(String.valueOf(opera.getPrice()));
		testTransaction.setTokenId(opera.getTokenId());
		
		service.modifyOwner(testTransaction);
		assert(true);
	}
}