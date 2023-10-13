package ru.poplaukhin.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.poplaukhin.test.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
