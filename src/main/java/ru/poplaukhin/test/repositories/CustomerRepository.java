package ru.poplaukhin.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.poplaukhin.test.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByChainName(String chainName);
}
