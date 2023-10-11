package ru.poplaukhin.test.controllers;

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
    public ResponseEntity<HttpStatus> post(@RequestBody Price price) {
        try {
            service.save(price);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityResponse<Price>> get(@PathVariable Long id) {
        EntityResponse<Price> response = service.getById(id);
        if (response.isEntityFound()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Price>> getAll() {
        List<Price> all = service.getAll();

        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> update(@PathVariable Long id,
                                        @RequestBody Price price) {

        Price update = service.update(id, price);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrice(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}