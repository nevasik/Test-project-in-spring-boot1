package ru.poplaukhin.test;

import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.poplaukhin.test.models.Price;
import ru.poplaukhin.test.repositories.PriceRepository;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PriceRestControllerTest {
    private PriceRepository repository;

    @Autowired
    public PriceRestControllerTest(PriceRepository repository) {
        this.repository = repository;
    }

    @Test
    @Operation(description = "Create new Price and save PriceRepository")
    public void testCreatePrice() {
        Price price = new Price();

        price.setId(1L);
        price.setChainName("Chain 1");
        price.setRegularPricePerUnit(92.19);

        Price savePrice = repository.save(price);

        assertNotNull(savePrice.getId());
    }

    @Test
    @Operation(description = "Read Price")
    public void testReadPrice() {
        Price price = new Price();

        price.setId(2L);
        price.setChainName("Chain 2");
        price.setRegularPricePerUnit(19.58);

        Price savePrice = repository.save(price);

        // read entity by id
        Price readPrice = repository.findById(savePrice.getId()).orElse(null);

        assertNotNull(readPrice);
    }

    @Test
    @Operation(description = "Update Price")
    public void testUpdatePrice() {
        Price price = new Price();

        price.setId(3L);
        price.setChainName("Chain 3");
        price.setRegularPricePerUnit(19.58);

        Price savePrice = repository.save(price);

        savePrice.setChainName("Chain 4");
        savePrice.setRegularPricePerUnit(24.17);

        Price updatePrice = repository.save(savePrice);

        // read entity from repo by id
        Price retrievedPrice = repository.findById(updatePrice.getId()).orElse(null);

        assertEquals("Chain 4", Objects.requireNonNull(retrievedPrice).getChainName());
        assertEquals(24.17, retrievedPrice.getRegularPricePerUnit());
    }

    @Test
    @Operation(description = "Delete Price")
    public void testDeletePrice() {
        Price price = new Price();

        price.setId(4L);
        price.setChainName("Chain 4");
        price.setRegularPricePerUnit(92.18);

        Price savePrice = repository.save(price);

        repository.delete(savePrice);

        Price retrievedPrice = repository.findById(savePrice.getId()).orElse(null);

        assertNull(retrievedPrice);
    }
}
