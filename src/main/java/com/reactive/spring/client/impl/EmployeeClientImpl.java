package com.reactive.spring.client.impl;

import com.reactive.spring.client.EmployeeClient;
import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {
    private final WebClient webClient;
    @Override
    public Flux<EmployeeDTO> findAll() {
        return this.webClient.get()
                .retrieve()
                .bodyToFlux(EmployeeDTO.class);
    }

    @Override
    public Mono<EmployeeDTO> findById(String empId) {
        return this.webClient.get()
                .uri("/{id}", empId)
                .retrieve()
                .bodyToMono(EmployeeDTO.class);
    }

    /***
     *  Define two api call
     * @param empName : Employee Name
     * @return
     */
    @Override
    public Flux<EmployeeEventDTO> nestedCallPathVariable(String empName) {
        return this.webClient.get()
                .retrieve()
                .bodyToFlux(EmployeeDTO.class)
                .filter(employeeDTO -> employeeDTO.getName().equals(empName))
                .flatMap(employeeDTO -> {
                    return this.webClient.get()
                            .uri("/{id}/event", employeeDTO.getId()) // path variable
                            .retrieve()
                            .bodyToFlux(EmployeeEventDTO.class);
                });
    }

    @Override
    public Flux<EmployeeEventDTO> nestedCallRequestParam(String empName) {
        return this.webClient.get()
                .retrieve()
                .bodyToFlux(EmployeeDTO.class)
                .filter(employeeDTO -> employeeDTO.getName().equals(empName))
                .flatMap(employeeDTO -> {
                    return this.webClient.get()
                            .uri(uriBuilder -> uriBuilder.path("/event").queryParam("name", employeeDTO.getName()).build()) // fake api call to illustrate,
                            .retrieve()
                            .bodyToFlux(EmployeeEventDTO.class);
                });
    }
}
