package com.reactive.spring.client;

import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeClient {
    Flux<EmployeeDTO> findAll();
    Mono<EmployeeDTO> findById(String empId);
    Flux<EmployeeEventDTO> nestedCallPathVariable(String empName);
    Flux<EmployeeEventDTO> nestedCallRequestParam(String empName);
}
