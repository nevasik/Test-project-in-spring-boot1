package ru.poplaukhin.test.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poplaukhin.test.models.Customer;
import ru.poplaukhin.test.repositories.CustomerRepository;
import ru.poplaukhin.test.response.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final Logger log = LoggerFactory.getLogger(Customer.class);
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void save(Customer customer) {
        log.info("Successful finding " + customer.getShipToCode() + " " + customer.getShipToName() + " " + customer.getChainName());
        repository.save(customer);
    }

    public EntityResponse<Customer> getById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));

        return new EntityResponse<>(true, customer);
    }

    public List<Customer> getAll() {
        List<Customer> customers = repository.findAll();
        log.info("Successful finding everyone" + customers);

        return customers;
    }

    public Customer update(Customer newPrice) {
        log.info("Update Customer " + newPrice.toString());

        repository.save(newPrice);

        return newPrice;
    }

    public void delete(Long id) {
        Optional<Customer> customer = repository.findById(id);

        log.info("Delete Customer " + customer.orElse(null));

        repository.delete(customer.get());
    }
}
