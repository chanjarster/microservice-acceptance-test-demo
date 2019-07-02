package me.chanjar.msat.product.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class RestPriceService implements PriceService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestPriceService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Value("${product-price.service.address:http://msat-product-price:8080}")
  private String priceServiceAddress;

  @Override
  public List<ProductPriceDto> listAll() {

    try {
      ResponseEntity<ProductPriceList> entity = restTemplate
        .getForEntity(priceServiceAddress + "/product-prices", ProductPriceList.class);

      if (entity.getStatusCode() != HttpStatus.OK) {
        LOGGER.error("product service response error: {}", entity.getStatusCode());
        return Collections.emptyList();
      }

      return entity.getBody().getPrices();

    } catch (Exception e) {
      LOGGER.error("product service error", e);
      return Collections.emptyList();
    }

  }
}
