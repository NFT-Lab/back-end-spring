package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("repository")
@EntityScan(basePackages = "opera")
@ComponentScan(basePackages = {"controller","service","configuration"})
public class NftServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(NftServiceApplication.class, args);
	}

}
