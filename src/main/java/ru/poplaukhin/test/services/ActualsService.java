package ru.poplaukhin.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poplaukhin.test.models.*;
import ru.poplaukhin.test.repositories.ActualsRepository;
import ru.poplaukhin.test.repositories.CustomerRepository;
import ru.poplaukhin.test.repositories.PriceRepository;
import ru.poplaukhin.test.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualsService {
    private final Logger log = LoggerFactory.getLogger(Price.class);
    private final ActualsRepository actualsRepository;
    private final CustomerRepository customerRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ActualsService(ActualsRepository actualsRepository, CustomerRepository customerRepository, PriceRepository priceRepository, ProductRepository productRepository) {
        this.actualsRepository = actualsRepository;
        this.customerRepository = customerRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    public List<Actuals> calcSalesWithPromo() {
        log.info("Calculate sales with promo in service actuals");
        List<Actuals> results = new ArrayList<>();
        List<Actuals> actualsList = actualsRepository.findAll(); // all fact

        for (Actuals actuals : actualsList) {
            Product product = productRepository.findById(actuals.getProduct().getMaterialNo()).orElse(null);
            Customer customer = customerRepository.findById(actuals.getCustomer().getShipToCode()).orElse(null);
            Price price = priceRepository.findByChainNameAndRegularPricePerUnit(actuals.getVolume(), product.getMaterialNo());

            if (product != null && customer != null && price != null) {
                Actuals result = new Actuals();

                result.setVolume(customer.getChainName());
                result.setPromoSign(product.getProductCategoryName());

                // Факт продаж в штуках по базовой цене
                double factSalesBasePrice = actuals.getUnits() * Math.round(price.getRegularPricePerUnit());
                result.setActualSalesValue(factSalesBasePrice);

                // Факт продаж в штуках по промо цене
                double factSalesPromoPrice = actuals.getUnits() * Math.round(actuals.getActualSalesValue());
                result.setPromoSign(String.valueOf(factSalesPromoPrice));

                double promoSalesPercentage = factSalesPromoPrice / factSalesBasePrice;
                log.info("Доля продаж по промо " + promoSalesPercentage);

                double promoSalesPercentageInPercent = promoSalesPercentage * 100;
                log.info("Процент продаж по промо " + promoSalesPercentageInPercent);

                results.add(result);
            }
        }

        return results;
    }

    public List<Actuals> getDailySales(List<String> volumeNames, List<String> productNames) {
        // Создаем список для хранения результатов
        List<Actuals> dailySalesList = new ArrayList<>();

        // Получаем данные о продажах из базы данных с учетом фильтров
        if (volumeNames.isEmpty() && productNames.isEmpty()) {
            // Если списки сетей и продуктов пусты, получаем все продажи
            dailySalesList = actualsRepository.findAll();
        } else if (!volumeNames.isEmpty() && productNames.isEmpty()) {
            // Если заданы только сети, фильтруем по сетям
            dailySalesList = actualsRepository.findByCustomerChainNameIn(volumeNames);
        } else if (volumeNames.isEmpty() && !productNames.isEmpty()) {
            // Если заданы только продукты, фильтруем по продуктам
            dailySalesList = actualsRepository.findByProductProductCategoryNameIn(productNames);
        } else {
            // Если заданы и сети, и продукты, фильтруем по обоим параметрам
            dailySalesList = actualsRepository.findByCustomerChainNameInAndProductProductCategoryNameIn(volumeNames, productNames);
        }

        return dailySalesList;
    }
}
