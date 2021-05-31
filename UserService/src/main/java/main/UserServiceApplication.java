package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("repository")
@EntityScan(basePackages = "user")
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "service")
public class UserServiceApplication {
	/*@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
