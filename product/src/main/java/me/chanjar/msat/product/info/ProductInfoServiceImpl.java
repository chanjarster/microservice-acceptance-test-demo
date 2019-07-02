package me.chanjar.msat.product.info;

import me.chanjar.msat.product.price.PriceService;
import me.chanjar.msat.product.price.ProductPriceDto;
import me.chanjar.msat.product.basic.Product;
import me.chanjar.msat.product.basic.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private PriceService inventoryService;

  @Override
  public List<ProductInfoDto> listAll() {

    List<Product> products = productRepository.listAll();

    List<ProductPriceDto> productPriceDtos = inventoryService.listAll();

    Map<String, ProductPriceDto> productPriceDtoMap =
      productPriceDtos.stream().collect(toMap(ProductPriceDto::getId, identity()));

    return products
      .stream()
      .map(product -> {
        ProductPriceDto productPriceDto = productPriceDtoMap.get(product.getId());
        if (productPriceDto == null) {
          return new ProductInfoDto(product, null);
        }
        return new ProductInfoDto(product, productPriceDto.getPrice());
      })
      .collect(toList());

  }
}
