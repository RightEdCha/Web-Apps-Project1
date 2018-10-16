package com.store.model;

public class Product {
    private int itemId, upc;
    private double msrp, salePrice;
    private String name, shortDescription, brandName, size, color, gender;

    public Product(int itemId, String name, double msrp, double salePrice, int upc, String shortDescription, String brandName,
                   String size, String color, String gender) {
        this.itemId = itemId;
        this.name = name;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.upc = upc;
        this.shortDescription = shortDescription;
        this.brandName = brandName;
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {

        return String.format(
                "{\"itemId\"=\"%d\", \"name\"=\"%s\", \"msrp\"=\"%1.2f\", \"salePrice\"=\"%1.2f\", \"upc\"=\"%d\", \"shortDescription\"=\"%s\", \"brandName\"=\"%s\", \"size\"=\"%s\", \"color\"=\"%s\", \"gender\"=\"%s\"}",
                itemId, name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender);
    }
}

