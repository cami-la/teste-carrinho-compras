package br.com.geofusion.cart;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class ShoppingCart {

  private final List<Item> items = new ArrayList<>();

  /**
   * Permite a adição de um novo item no carrinho de compras.
   * <p>
   * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
   * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
   * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
   * o passado como parâmetro.
   * <p>
   * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
   *
   * @param product
   * @param unitPrice
   * @param quantity
   */
  public void addItem(Product product, BigDecimal unitPrice, int quantity) {
    Item item = new Item(product, unitPrice, quantity);
    items.add(item);
  }

  /**
   * Permite a remoção do item que representa este produto do carrinho de compras.
   *
   * @param product
   * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
   * caso o produto não exista no carrinho.
   */
  public boolean removeItem(Product product) {
    return items.removeIf(i -> i.getProduct().equals(product));
  }

  /**
   * Permite a remoção do item de acordo com a posição.
   * Essa posição deve ser determinada pela ordem de inclusão do produto na
   * coleção, em que zero representa o primeiro item.
   *
   * @param itemIndex
   * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
   * caso o produto não exista no carrinho.
   */
  public boolean removeItem(int itemIndex) {
    if (itemIndex >= items.size() || itemIndex < 0) {
      return false;
    } else {
      Item item = this.items.get(itemIndex);
      return this.items.remove(item);
    }
  }

  /**
   * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
   * de todos os itens que compõem o carrinho.
   *
   * @return BigDecimal
   */
  public BigDecimal getAmount() {
    return items.stream()
        .map(Item::getAmount)
        .collect(Collectors.toList())
        .stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .setScale(1, RoundingMode.HALF_EVEN);
  }

  /**
   * Retorna a lista de itens do carrinho de compras.
   *
   * @return items
   */
  public Collection<Item> getItems() {
    return this.items;
  }
}