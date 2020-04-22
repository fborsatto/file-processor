package com.southsystem.fileprocessor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Sale implements Serializable {

    private Long saleId;

    private List<SaleItem> itemsList;

    private String sellerName;

    public Sale(Long saleId, List<SaleItem> itemsList, String sellerName) {
        this.saleId = saleId;
        this.itemsList = itemsList;
        this.sellerName = sellerName;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public List<SaleItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<SaleItem> itemsList) {
        this.itemsList = itemsList;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleItem item : itemsList) {
            total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

}
