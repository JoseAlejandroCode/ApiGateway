package com.microservices.apigateway.cofiguration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
            .route(r -> r.path("/api/students/**")
                    .uri("lb://SERVICE-STUDENT/")
                    .id("service-student"))
            .route(r -> r.path("/api/family/**")
                    .uri("lb://SERVICE-FAMILY/")
                    .id("service-family"))
            .route(r -> r.path("/api/courses/**")
                    .uri("lb://SERVICE-COURSE/")
                    .id("service-course"))
            .route(r -> r.path("/api/teachers/**")
                    .uri("lb://SERVICE-TEACHER/")
                    .id("service-teacher"))
            .route(r -> r.path("/service-*/**")
                    .uri("lb://CONFIG-SERVER/")
                    .id("config-server"))
            .build();
  }

}
