package com.niit.apigateway.routes;

import com.niit.apigateway.filter.AdminUrlsFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(i -> i.path("/api/v1/login"
                        , "/api/v1/image"
                        , "/api/v1/admin/login"
                        , "/api/v1/admin/users"
                        , "/api/v1/admin/users/block"
                        , "/api/v1/admin/users/unblock"
                        , "/api/v1/admin/users/addrole"
                        , "/api/v1/admin/users/removerole"
                        , "/api/v1/admin/roles").uri("lb://user-authentication"))
                .route(i -> i.path("/api/v1/register"
                        , "/api/v1/user/**").uri("lb://user-movie"))
                .route(i -> i.path("/api/v1/recommended/genre"
                        , "/api/v1/recommended/popularMovie"
                        , "/api/v1/recommended/searchMovie/**"
                        , "/api/v1/thirdParty/cast/**"
                        , "/api/v1/thirdParty/**"
                        , "/api/v1/thirdParty/upcomingMovies"
                        , "/api/v1/thirdParty/trailer/**"
                        , "/api/v1/thirdParty/Action"
                        , "/api/v1/thirdParty/Comedy"
                        , "/api/v1/thirdParty/Crime"
                        , "/api/v1/thirdParty/Family"
                        , "/api/v1/recommended/movie/**"
                        ,"/api/v1/recommended/**").uri("lb://recommended-service"))
                .route(i -> i.path("/api/v1/test-message")
                        .uri("lb://push-notification"))
                .route(i -> i.path("/api/v1/admin/*/actuator"
                        , "/api/v1/admin/*/actuator**"
                        , "/api/v1/admin/*/actuator/**")
                        .filters(j -> j.filter(new AdminUrlsFilter()))
                        .uri("no://op"))
                .build();
    }
}
