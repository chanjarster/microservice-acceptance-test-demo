package me.chanjar.msat.product.price;

import java.util.Collections;
import java.util.List;

public class ProductPriceList {

  private List<ProductPriceDto> prices = Collections.emptyList();

  public List<ProductPriceDto> getPrices() {
    return prices;
  }

  public void setPrices(List<ProductPriceDto> prices) {
    this.prices = prices;
  }
}
