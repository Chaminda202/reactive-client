package com.reactive.spring.controller;

import com.reactive.spring.client.EmployeeClient;
import com.reactive.spring.model.EmployeeDTO;
import com.reactive.spring.model.EmployeeEventDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/client/employees")
@AllArgsConstructor
public class EmployeeClientController {
    private final EmployeeClient employeeClient;

    @GetMapping
    public Flux<EmployeeDTO> findAll(){
        return this.employeeClient.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<EmployeeDTO> findById(@PathVariable(value = "id") String empId) {
        return this.employeeClient.findById(empId);
    }

    @GetMapping(value = "/nested", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<EmployeeEventDTO> nestedCall(@RequestParam(value = "name") String empName){
        return this.employeeClient.nestedCallPathVariable(empName);
    }
}
