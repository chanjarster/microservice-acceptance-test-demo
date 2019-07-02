package me.chanjar.msat.product.basic;

import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class FakeProductRepository implements ProductRepository {

  @Override
  public List<Product> listAll() {
    return asList(
      new Product("animal-1", "dog", "woof woof"),
      new Product("animal-2", "duck", "quack quack"),
      new Product("animal-3", "fox", "what does the fox say?")
    );
  }
}
