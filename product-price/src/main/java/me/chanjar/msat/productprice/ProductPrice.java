package me.chanjar.msat.productprice;

public class ProductPrice {

  private String id;
  private Integer price;

  public ProductPrice(String id, Integer price) {
    this.id = id;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

}
