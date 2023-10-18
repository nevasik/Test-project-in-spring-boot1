package ru.poplaukhin.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.poplaukhin.test.models.Price;
import ru.poplaukhin.test.response.EntityResponse;
import ru.poplaukhin.test.services.PriceService;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService service;

    @Autowired
    public PriceController(PriceService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "New price")
    public ResponseEntity<HttpStatus> post(@RequestBody Price price) {
        try {
            service.save(price);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Get price")
    public ResponseEntity<EntityResponse<Price>> get(@PathVariable Long id) {
        EntityResponse<Price> response = service.getById(id);
        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @Operation(description = "Get all price")
    public ResponseEntity<List<Price>> getAll() {
        List<Price> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @PutMapping
    @Operation(description = "Update price")
    public ResponseEntity<Price> update(@RequestBody Price price) {

        Price update = service.update(price);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete price")
    public ResponseEntity<?> deletePrice(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}