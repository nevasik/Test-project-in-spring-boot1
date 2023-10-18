package ru.poplaukhin.test.services;

import jakarta.persistence.EntityNotFoundException;

import ru.poplaukhin.test.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.poplaukhin.test.repositories.ProductRepository;
import ru.poplaukhin.test.response.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(Product.class);
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        log.info("Successful finding " + product.getMaterialNo() + " " + product.getProductCategoryCode() + " " + product.getProductCategoryName());
        repository.save(product);
    }

    public EntityResponse<Product> getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        return new EntityResponse<>(true, product);
    }

    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        log.info("Successful finding everyone" + products);

        return products;
    }

    public Product update(Product newProduct) {
        repository.save(newProduct);

        log.info("Update Product " + newProduct);

        return newProduct;
    }

    public void delete(Long id) {
        Optional<Product> product = repository.findById(id);

        log.info("Delete Product " + product.orElse(null));

        repository.delete(product.get());
    }
}
