package ru.poplaukhin.test.services;

import jakarta.persistence.EntityNotFoundException;
import ru.poplaukhin.test.models.Price;
import ru.poplaukhin.test.repositories.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.poplaukhin.test.response.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    private final Logger log = LoggerFactory.getLogger(Price.class);
    private final PriceRepository repository;

    @Autowired
    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public void save(Price price) {
        log.info("Successful finding " + price.getChainName() + " " + price.getProduct() + " " + price.getRegularPricePerUnit());
        repository.save(price);
    }

    public EntityResponse<Price> getById(Long id) {
        Price price = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price not found with id " + id));

        return new EntityResponse<>(true, price);
    }

    public List<Price> getAll() {
        List<Price> prices = repository.findAll();
        log.info("Successful finding everyone" + prices);

        return prices;
    }

    public Price update(Price newPrice) {
        repository.save(newPrice);

        log.info("Update Price " + newPrice);

        return newPrice;
    }

    public void delete(Long id) {
        Optional<Price> price = repository.findById(id);

        log.info("Delete Price " + price.orElse(null));

        repository.delete(price.get());
    }
}
