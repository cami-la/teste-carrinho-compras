package br.com.geofusion.test;

import br.com.geofusion.cart.Item;
import br.com.geofusion.cart.Product;
import br.com.geofusion.test.builders.ItemBuilder;
import br.com.geofusion.test.builders.ProductBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ItemTest {
  private Item item;

  @BeforeEach
  void setUp() {
    this.item = ItemBuilder.builder().build();
  }

  @Test
  void test_GetAmount_Should_CalculateAndReturnTotalValueItem() {
    //given
    //when
    BigDecimal actual = item.getAmount();
    //then
    BigDecimal expected = BigDecimal.valueOf(20);
    Assertions.assertEquals(expected, actual);
  }

}