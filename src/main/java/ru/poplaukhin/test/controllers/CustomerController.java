package ru.poplaukhin.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poplaukhin.test.models.Customer;
import ru.poplaukhin.test.response.EntityResponse;
import ru.poplaukhin.test.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "New customer")
    public ResponseEntity<HttpStatus> post(@RequestBody Customer customer) {
        try {
            service.save(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Get customer by id")
    public ResponseEntity<EntityResponse<Customer>> get(@PathVariable Long id) {
        EntityResponse<Customer> response = service.getById(id);
        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @Operation(description = "All Customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @PutMapping
    @Operation(description = "Update Customers")
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        Customer update = service.update(customer);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete Customers")
    public ResponseEntity<?> deletePrice(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}