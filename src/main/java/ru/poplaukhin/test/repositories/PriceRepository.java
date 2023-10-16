package ru.poplaukhin.test.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.test.models.Price;
import ru.poplaukhin.test.models.Product;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    // найти цену по названию сети и продукту
//    Price findByChainNameAndPrice(String chainName, Product product);
    Price findByChainNameAndRegularPricePerUnit(String chainName, double regularPricePerUnit);
}
