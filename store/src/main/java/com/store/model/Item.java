package com.store.model;

public class Item{
    private int productId;
    private String productName;
    private double msrp;
    private double salePrice;

    public Item (int productId, String productName, double msrp, double salePrice) {
        this.productId = productId;
        this.productName = productName;
        this.msrp = msrp;
        this.salePrice = salePrice;
        }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
