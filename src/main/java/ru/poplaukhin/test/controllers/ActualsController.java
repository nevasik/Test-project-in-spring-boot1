package ru.poplaukhin.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/sales-with-promo")
    public ResponseEntity<List<Actuals>> getSalesWithPromo() {
        List<Actuals> actuals = service.calcSalesWithPromo();

        return ResponseEntity.ok(actuals);
    }

//    @GetMapping("/daily-sales")
//    public ResponseEntity<List<Actuals>> getDailySales(@RequestParam List<String> volumeNames,
//                                                          @RequestParam List<String> productNames) {
//
//        List<Actuals> result = service.getDailySales(volumeNames, productNames);
//        return ResponseEntity.ok(result);
//    }
}
