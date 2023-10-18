package ru.poplaukhin.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.poplaukhin.test.models.Product;
import ru.poplaukhin.test.response.EntityResponse;
import ru.poplaukhin.test.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Save product")
    public ResponseEntity<HttpStatus> post(@RequestBody Product product) {
        try {
            service.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Get product")
    public ResponseEntity<EntityResponse<Product>> get(@PathVariable Long id) {
        EntityResponse<Product> response = service.getById(id);
        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @Operation(description = "Get all products")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @PutMapping
    @Operation(description = "Update product")
    public ResponseEntity<Product> update(@RequestBody Product product) {

        Product update = service.update(product);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete product")
    public ResponseEntity<?> deletePrice(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
