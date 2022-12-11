package br.com.geofusion.test.builders;

import br.com.geofusion.cart.Product;

import java.util.Random;

public class ProductBuilder {
  private Product product;

  public static ProductBuilder builder() {
    ProductBuilder builder = new ProductBuilder();
    Long code = new Random().nextLong();
    String description = "Test Product";
    builder.product = new Product(code, description);
    return builder;
  }

  public Product build() {
    return this.product;
  }
}
