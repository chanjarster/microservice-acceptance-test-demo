package me.chanjar.msat.productprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.singletonMap;

@RestController
public class ProductPriceRestController {

  @Autowired
  private ProductPriceRepository productPriceRepository;

  @GetMapping(value = "/product-prices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity listAll() {
    return new ResponseEntity(
      singletonMap("prices", productPriceRepository.listAll()),
      HttpStatus.OK
    );
  }

  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity health() {
    return new ResponseEntity(
      singletonMap("healthy", true),
      HttpStatus.OK
    );
  }
}
