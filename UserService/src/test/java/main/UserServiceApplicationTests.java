package main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.UserServiceApplication;
import service.UserService;
import user.User;

@WebMvcTest
@EntityScan(basePackages = "user")
class UserServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void contextLoads() throws Exception {
	/*	 List<Student> students = new ArrayList<>();
	        Student student = new Student();
	        student.setId(1);
	        student.setName("Arun");
	        students.add(student);
	        Mockito.when(studentService.getStudents()).thenReturn(students);
	        mockMvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
	                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Arun")));
	*/
		User user = new User();
		user.setName("Marco");
		user.setEmail("marco@gmail.com");
		user.setPassword("pip91");
		mockMvc.perform(post("/register")).andExpect(status().isOk()).andExpect(jsonPath("name", Matchers.equalTo("Marco")));
	}
	
}
