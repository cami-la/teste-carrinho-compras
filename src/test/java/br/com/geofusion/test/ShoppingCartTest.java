package br.com.geofusion.test;

import br.com.geofusion.cart.Product;
import br.com.geofusion.cart.ShoppingCart;
import br.com.geofusion.test.builders.ProductBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

class ShoppingCartTest {
  private ShoppingCart shoppingCart;

  @BeforeEach
  void setUp() {
    this.shoppingCart = new ShoppingCart();
  }

  @Test
  void test_addItem_Should_CalculateAndReturnTotalValueItem() {
    //given
    Product product = ProductBuilder.builder().build();
    BigDecimal unitPrice = BigDecimal.TEN;
    int quantity = 2;
    //when
    shoppingCart.addItem(product, unitPrice, quantity);
    int actualQuantityItems = shoppingCart.getItems().size();
    //then
    int expectedQuantityItems = 1;
    Assertions.assertEquals(expectedQuantityItems, actualQuantityItems);
  }

  @Test
  void test_removeItemByProduct_Should_RemoveItemAndReturnTrue_When_ContainProduct() {
    //given
    Product product = ProductBuilder.builder().build();
    shoppingCart.addItem(product, BigDecimal.TEN, 2);
    //when
    boolean actual = this.shoppingCart.removeItem(product);
    //then
    Assertions.assertTrue(actual);
  }

  @Test
  void test_removeItemByProduct_Should_DoesNotRemoveItemAndReturnFalse_When_DoesntContainProduct() {
    //given
    Product product = ProductBuilder.builder().build();
    shoppingCart.addItem(product, BigDecimal.TEN, 2);
    Product product2 = ProductBuilder.builder().build();
    //when
    boolean actual = this.shoppingCart.removeItem(product2);
    //then
    Assertions.assertFalse(actual);
  }

  @Test
  void test_removeItemByItemIndex_Should_RemoveItemAndReturnTrue_When_ItemIndexValid() {
    //given
    Product product1 = ProductBuilder.builder().build();
    Product product2 = ProductBuilder.builder().build();
    shoppingCart.addItem(product1, BigDecimal.TEN, 2);
    shoppingCart.addItem(product2, BigDecimal.ONE, 5);
    //when
    boolean actual = this.shoppingCart.removeItem(1);
    int actualItemListSize = shoppingCart.getItems().size();
    //then
    int expectedItemListSize = 1;
    Assertions.assertTrue(actual);
    Assertions.assertEquals(expectedItemListSize, actualItemListSize);
  }

  @Test
  void test_removeItemByItemIndex_Should_DoesNotRemoveItemAndReturnFalse_When_ItemIndexInValid() {
    //given
    Product product1 = ProductBuilder.builder().build();
    Product product2 = ProductBuilder.builder().build();
    shoppingCart.addItem(product1, BigDecimal.TEN, 2);
    shoppingCart.addItem(product2, BigDecimal.ONE, 5);
    //when
    boolean actual = this.shoppingCart.removeItem(3);
    int actualItemListSize = shoppingCart.getItems().size();
    //then
    int expectedItemListSize = 2;
    Assertions.assertFalse(actual);
    Assertions.assertEquals(expectedItemListSize, actualItemListSize);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 0, -1})
  void test_removeItemByItemIndex_Should_DoesNotRemoveItemAndReturnFalse_When_ItemIndexInValidAndEmptylist(int itemIndex) {
    //given
    //when
    boolean actual = this.shoppingCart.removeItem(1);
    //then
    Assertions.assertFalse(actual);
  }

  @Test
  void test_getAmount_Should_CalculateAndReturnTotalAmountShoppingCart_When_ContainsItems() {
    //given
    Product product1 = ProductBuilder.builder().build();
    Product product2 = ProductBuilder.builder().build();
    shoppingCart.addItem(product1, BigDecimal.TEN, 2);
    shoppingCart.addItem(product2, BigDecimal.ONE, 5);
    //when
    BigDecimal actualAmount = shoppingCart.getAmount();
    int actualItemListSize = shoppingCart.getItems().size();
    //then
    int expectedItemListSize = 2;
    BigDecimal expectedIAmount = BigDecimal.valueOf(25d);
    Assertions.assertEquals(expectedIAmount,actualAmount);
    Assertions.assertEquals(expectedItemListSize, actualItemListSize);
  }

  @Test
  void test_getAmount_Should_CalculateAndReturnTotalAmountShoppingCart_When_DoesntContainsItems() {
    //given
    //when
    BigDecimal actualAmount = shoppingCart.getAmount();
    int actualItemListSize = shoppingCart.getItems().size();
    //then
    int expectedItemListSize = 0;
    BigDecimal expectedIAmount = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_EVEN);
    Assertions.assertEquals(expectedIAmount,actualAmount);
    Assertions.assertEquals(expectedItemListSize, actualItemListSize);
  }
}