package com.southsystem.fileprocessor.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleItem implements Serializable {

    private Long itemId;
    private Long quantity;
    private BigDecimal price;

    public SaleItem(Long itemId, Long quantity, BigDecimal price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
