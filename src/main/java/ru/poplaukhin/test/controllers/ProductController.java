package ru.poplaukhin.test.controllers;

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
    public ResponseEntity<HttpStatus> post(@RequestBody Product product) {
        try {
            service.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityResponse<Product>> get(@PathVariable Long id) {
        EntityResponse<Product> response = service.getById(id);
        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {

        Product update = service.update(product);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrice(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
