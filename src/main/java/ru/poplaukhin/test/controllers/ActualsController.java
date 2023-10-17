package ru.poplaukhin.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.poplaukhin.test.dto.Unloading;
import ru.poplaukhin.test.models.Actuals;
import ru.poplaukhin.test.services.ActualsService;

import java.util.List;

@RestController
@RequestMapping("/actuals")
public class ActualsController {
    private final ActualsService service;

    @Autowired
    public ActualsController(ActualsService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<List<Actuals>> calculateColumnPromoSign() {
        List<Actuals> actuals = service.calcColumPromoSign();

        return ResponseEntity.ok(actuals);
    }

    @GetMapping("/sales-with-promo")
    public ResponseEntity<List<Unloading>> uploadingSalesFactsPromo() {
        List<Unloading> actuals = service.uploadingSalesPromo();

        return ResponseEntity.ok(actuals);
    }

    @GetMapping("/uploading facts")
    public ResponseEntity<List<Unloading>> uploadingFacts() {
        List<Unloading> actuals = service.getDailySales();

        return ResponseEntity.ok(actuals);
    }
}
