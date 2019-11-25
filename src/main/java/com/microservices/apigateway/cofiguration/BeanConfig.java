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
                    .filters(f -> f.rewritePath("/api/students/(?.*)", "/${remains}")
                            .hystrix(c -> c.setName("hystrix")
                                    .setFallbackUri("forward:/fallback/students")))
                    .uri("lb://SERVICE-STUDENT/")
                    .id("service-student"))
            .route(r -> r.path("/api/family/**")
                    .filters(f -> f.rewritePath("/api/family/(?.*)", "/${remains}")
                            .hystrix(c -> c.setName("hystrix")
                                    .setFallbackUri("forward:/fallback/family")))
                    .uri("lb://SERVICE-FAMILY/")
                    .id("service-family"))
            .route(r -> r.path("/api/courses/**")
                    .filters(f -> f.rewritePath("/api/courses/(?.*)", "/${remains}")
                            .hystrix(c -> c.setName("hystrix")
                                    .setFallbackUri("forward:/fallback/courses")))
                    .uri("lb://SERVICE-COURSE/")
                    .id("service-course"))
            .route(r -> r.path("/api/teachers/**")
                    .filters(f -> f.rewritePath("/api/teachers/(?.*)", "/${remains}")
                            .hystrix(c -> c.setName("hystrix")
                                    .setFallbackUri("forward:/fallback/teachers")))
                    .uri("lb://SERVICE-TEACHER/")
                    .id("service-teacher"))
            .build();
  }

}
