package me.chanjar.msat.productprice;

import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class FakeProductPriceRepository implements ProductPriceRepository {

  @Override
  public List<ProductPrice> listAll() {
    return asList(
      new ProductPrice("animal-1", 1000),
      new ProductPrice("animal-2", 40),
      new ProductPrice("animal-3", 5000)
    );
  }

}
