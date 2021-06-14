package stage.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class RestControllerGateway {
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		System.out.println("creo le build");
	    return builder
	    		.routes()
	    		.route("user_route", r -> r.path("/UserService/**").uri("http://localhost:5072"))
	    		.route("nft_route", r -> r.path("/NFTService/**").uri("http://localhost:5073"))
	    		.route("transaction_route", r -> r.path("/TransactionService/**").uri("http://localhost:5074"))
	    		.build();
	}
}
