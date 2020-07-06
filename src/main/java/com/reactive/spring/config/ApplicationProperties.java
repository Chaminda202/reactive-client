package com.reactive.spring.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Getter
public class ApplicationProperties {
    private final EmployeeEndpoint employeeEndpoint = new EmployeeEndpoint();

    @Data
    public static class EmployeeEndpoint {
        private String springMvc;
    }
}
