package ru.poplaukhin.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poplaukhin.test.models.*;
import ru.poplaukhin.test.repositories.ActualsRepository;
import ru.poplaukhin.test.repositories.CustomerRepository;
import ru.poplaukhin.test.repositories.PriceRepository;
import ru.poplaukhin.test.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualsService {
    private final Logger log = LoggerFactory.getLogger(Actuals.class);
    private final ActualsRepository actualsRepository;
    private final CustomerRepository customerRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ActualsService(ActualsRepository actualsRepository, CustomerRepository customerRepository, PriceRepository priceRepository, ProductRepository productRepository) {
        this.actualsRepository = actualsRepository;
        this.customerRepository = customerRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    public List<Actuals> calcColumPromoSign() {
        log.info("Calculate column promo sign");

        List<Actuals> actuals = actualsRepository.findAll();
        List<Price> prices = priceRepository.findAll();

        for (Actuals actual : actuals) {
            for (Price price : prices) {
                if (actual.getProduct().equals(price.getMaterialNo())) {
                    if ((actual.getActualSalesValue() / actual.getUnits()) < price.getRegularPricePerUnit()) {
                        log.info(String.valueOf(actual));
                        actual.setPromoSign("Promo");
                        actualsRepository.save(actual);
                    } else {
                        log.info(String.valueOf(actual));
                        actual.setPromoSign("Regular");
                        actualsRepository.save(actual);
                    }
                }
            }
        }

        return actuals;
    }

    public List<Actuals> calcSalesWithPromo() {
        log.info("Calculate sales with promo in service actuals");

        List<Actuals> result = new ArrayList<>();

        // sales data
        List<Actuals> all = actualsRepository.findAll();

        for (Actuals actuals : all) {

        }

        return null;
    }

    public List<Actuals> getDailySales(List<String> volumeNames, List<String> productNames) {
        return null;
    }
}
