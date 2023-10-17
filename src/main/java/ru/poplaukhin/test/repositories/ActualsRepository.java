package ru.poplaukhin.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poplaukhin.test.models.Actuals;
import ru.poplaukhin.test.models.Customer;
import ru.poplaukhin.test.models.Product;

import java.util.List;

@Repository
public interface ActualsRepository extends JpaRepository<Actuals, Long> {
    List<Actuals> findActualsByProductAndCustomer(Product product, Customer customer);
}
