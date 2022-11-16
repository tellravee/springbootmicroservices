package com.eazybytes.gatewayserver;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient

public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator myroutes(RouteLocatorBuilder builder) {
		return builder.routes()
		        .route(p -> p
		            .path("/tellravee/accounts/**")
		            .filters(f -> f.rewritePath("/tellravee/accounts/(?<segment>.*)","/${segment}")
		            				.addResponseHeader("X-Response-Time",new Date().toString())
		            				.addResponseHeader("Accept", "application/json")
		            				.addRequestHeader("X-Response-Time",new Date().toString())
		            				.setRequestHeader("Accept", "application/json")
		            				.setRequestHeader("Content-Type", "application/json")
		            				)
		            .uri("lb://ACCOUNTS")).
		        route(p -> p
			            .path("/tellravee/loans/**")
			            .filters(f -> f.rewritePath("/tellravee/loans/(?<segment>.*)","/${segment}")
			            		.addResponseHeader("X-Response-Time",new Date().toString()))
			            .uri("lb://LOANS")).
		        route(p -> p
			            .path("/tellravee/cards/**")
			            .filters(f -> f.rewritePath("/tellravee/cards/(?<segment>.*)","/${segment}")
			            		.addResponseHeader("X-Response-Time",new Date().toString()))
			            .uri("lb://CARDS")).build();
		}
}


