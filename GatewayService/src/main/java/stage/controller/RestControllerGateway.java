package stage.controller;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import reactor.netty.http.client.HttpClient.UriConfiguration;

@RestController

public class RestControllerGateway {
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder
	    		.routes()
	    		.route("registration_route", r -> r.path("/RegistrationService/**").uri("http://localhost:5071"))
	    		.build();
	}
}
