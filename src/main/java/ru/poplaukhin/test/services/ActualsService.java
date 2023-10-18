package ru.poplaukhin.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poplaukhin.test.dto.Unloading;
import ru.poplaukhin.test.models.*;
import ru.poplaukhin.test.repositories.ActualsRepository;
import ru.poplaukhin.test.repositories.CustomerRepository;
import ru.poplaukhin.test.repositories.PriceRepository;
import ru.poplaukhin.test.repositories.ProductRepository;

import java.util.*;

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

    // promo or regular record
    public List<Actuals> calcColumPromoSign() {
        log.info("Calculate column promo sign");

        List<Actuals> actuals = actualsRepository.findAll();
        List<Price> prices = priceRepository.findAll();

        for (Actuals actual : actuals) {
            for (Price price : prices) {
                if (actual.getProduct().equals(price.getProduct())) {
                    if ((actual.getActualSalesValue() / actual.getUnits()) > price.getRegularPricePerUnit()) {
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

    public List<Unloading> uploadingSalesPromo() {
        log.info("Calculate sales with promo in service actuals");

        List<Unloading> result = new ArrayList<>();

        List<Actuals> actuals = actualsRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        double promoValueSum = 0;
        double regularValueSum = 0;
        double promoUnitsSum = 0;
        double regularUnitsSum = 0;

        for (Actuals actual : actuals) {
            if (actual.getPromoSign().equals("Promo")) {
                promoValueSum += actual.getActualSalesValue();
                promoUnitsSum += actual.getUnits();
            } else {
                regularValueSum += actual.getActualSalesValue();
                regularUnitsSum += actual.getUnits();
            }
        }

        log.info(String.valueOf(promoValueSum));
        log.info(String.valueOf(regularValueSum));
        log.info(String.valueOf(promoUnitsSum));
        log.info(String.valueOf(regularUnitsSum));

        for (Actuals actual : actuals) { // the speed is lame, I'll fix it when I have time
            for (Customer customer : customers) {

                if (actual.getCustomer().getShipToCode().equals(customer.getShipToCode())) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(actual.getDate());
                    int month = calendar.get(Calendar.MONTH) + 1; // added 1 because months in the calendar start from 0
                    log.info(String.valueOf(month));

                    Unloading unloadingResult = new Unloading(customer.getChainName(), actual.getProduct().getProductCategoryCode(),
                            month, (((regularValueSum / regularUnitsSum) * 100.0) / 100.0), (((promoValueSum / promoUnitsSum) * 100.0) / 100.0), 0);

                    if (actual.getPromoSign().equals("Promo")) {
                        double procentRes = actual.getUnits() / actual.getActualSalesValue() * 100;
                        double roundedValue = Math.round(procentRes * 100.0) / 100.0; // to round to 2 decimal places

                        log.info(String.valueOf(roundedValue));

                        unloadingResult.setProcentResult(roundedValue);
                        unloadingResult.setRegularFact(0); // because promo price, then regular 0
                    } else {
                        unloadingResult.setPromoFact(0); // everything is the other way around here
                    }

                    result.add(unloadingResult);
                }
            }
        }

        return result;
    }

    public List<List<Actuals>> getDailySales(String chainName, String productCategoryName) {
        List<Customer> byChainName = customerRepository.findByChainName(chainName);
        List<Product> byProductCategoryName = productRepository.findByProductCategoryName(productCategoryName);

        List<List<Actuals>> result = new ArrayList<>();

        if (byChainName.size() <= byProductCategoryName.size()) {
            for (Customer customer : byChainName) {
                for (Product product : byProductCategoryName) {
                    result.add(actualsRepository.findActualsByProductAndCustomer(product, customer));
                }
            }
        } else {
            for (Product product : byProductCategoryName) {
                for (Customer customer : byChainName) {
                    result.add(actualsRepository.findActualsByProductAndCustomer(product, customer));
                }
            }
        }

        return result;
    }
}
