package ru.poplaukhin.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.poplaukhin.test.models.Customer;
import ru.poplaukhin.test.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductCategoryName(String productCategoryCode);
}
