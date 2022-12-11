package br.com.geofusion.cart;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {
    private Product product;
    private BigDecimal unitPrice;
    private int quantity;

    /**
     * Construtor da classe Item.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public Item(Product product, BigDecimal unitPrice, int quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return this.quantity;
    }


    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return this.getUnitPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}

