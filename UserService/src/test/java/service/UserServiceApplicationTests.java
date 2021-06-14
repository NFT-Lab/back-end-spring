package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import repository.JPAUserRepository;
import service.UserService;
import user.User;

@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {
	
	private UserService service;
	
	private JPAUserRepository repo = Mockito.mock(JPAUserRepository.class);
	
	private User user;

	@BeforeEach
	void initUserService() {
		service = new UserService(repo);
		user = new User("Testing_name", "Testing_surname", "Testing@gmail.com", LocalDate.of(1998, 6, 1), "Testing", "0xEBD5d19c2Aa332EDe6e2Eb92566a95D2B2f829F4");
	}
	
	@Test
	void addUserTest() throws Exception{
		
		when(repo.save(any(User.class))).thenReturn(user);
		
		when(repo.findUsersByEmail(any(String.class))).thenReturn(user);
		
		User savedUser = service.addUser(user);
		
		System.out.println(user.getName());
		assertThat(savedUser.getEmail()).isNotNull();
	}
	@Test
	void updateUserPasswordTest() throws Exception{
		String newPassword = "PasswordChanged";
		User expectedUser = new User("Testing_name", "Testing_surname", "Testing@gmail.com", LocalDate.of(1998, 6, 1), Integer.toString(newPassword.hashCode()), "0xEBD5d19c2Aa332EDe6e2Eb92566a95D2B2f829F4");
		
		when(repo.findUsersByEmail(any(String.class))).thenReturn(user);
		
		when(repo.save(any(User.class))).thenReturn(user);
		
		User actualUser = service.updateUserPassword(user, newPassword);
		
		assertEquals(expectedUser.getPassword(), actualUser.getPassword());
	}
 
	void updateUserDataTest() throws Exception {
		when(repo.save(any(User.class))).thenReturn(user);
		
		when(repo.findUsersByEmail(any(String.class))).thenReturn(user);
		
		assertThat(service.updateUserData(user)).isNotNull();
	}
}