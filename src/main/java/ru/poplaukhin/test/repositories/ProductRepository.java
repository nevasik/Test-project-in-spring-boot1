package ru.poplaukhin.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.poplaukhin.test.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
