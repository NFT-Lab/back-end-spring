package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.UserService;
import service.UserServiceInterface;
import user.User;
@EnableJpaRepositories("repository")
@EntityScan(basePackages = "user")
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "service")
class UserServiceApplicationTests {
	/*private User user;
	private UserServiceInterface service;
	
	@Before
	public void setupMock() {
		user = mock(User.class);
		service = mock(UserServiceInterface.class);
	}
	@Test
    public void testMockCreation(){
        user.setEmail("pippo@gmail.com");
        user.setPassword("pippo");
        service.checkEmail(user);
        
    }*/
}
