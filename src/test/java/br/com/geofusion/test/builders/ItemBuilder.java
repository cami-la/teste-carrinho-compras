package br.com.geofusion.test.builders;

import br.com.geofusion.cart.Item;
import br.com.geofusion.cart.Product;

import java.math.BigDecimal;

public class ItemBuilder {
  private Item item;

  public static ItemBuilder builder() {
    ItemBuilder builder = new ItemBuilder();
    Product product = ProductBuilder.builder().build();
    BigDecimal unitPrice = BigDecimal.TEN;
    int quantity = 2;
    builder.item = new Item(product, unitPrice, quantity);
    return builder;
  }

  public Item build() {
    return this.item;
  }
}
