package me.chanjar.msat.product.info;

import me.chanjar.msat.product.basic.Product;

public class ProductInfoDto {

  private String id;
  private String name;
  private String description;
  private Integer price;

  public ProductInfoDto(Product product, Integer price) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
