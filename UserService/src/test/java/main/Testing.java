package main;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.JPAUserRepository;
import user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
class Testing {

     @Autowired private JPAUserRepository userRepository;

     @Test
     public void testSaveEmployee() {

         User user = new User();
         user.setId(0);
         user.setEmail("test@gmail.com");
         user.setName("Andrea");
         user.setPassword("pippo");
         userRepository.save(user);
         User user1 = userRepository.findUsersByEmail("test@gmail.com");
         assertNotNull(user);
         assertEquals(user1.getName(), user.getName());
         //assertEquals(employee2.getLastName(), employee.getLastName());
     }
}