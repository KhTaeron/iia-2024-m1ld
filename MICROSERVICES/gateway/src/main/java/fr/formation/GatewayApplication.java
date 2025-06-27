package fr.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r ->
                r   .method(HttpMethod.GET)
                    .uri("lb://query-service")
            )

            .route(r ->
                r   .path("/api/produit/**")
                    .uri("lb://produit-service")
            )

            .route(r ->
                r   .path("/api/commentaire/**")
                    .uri("lb://commentaire-service")
            )
            .build()
        ;
    }
}
