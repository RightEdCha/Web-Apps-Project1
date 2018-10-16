package com.store.model;

import java.util.Collection;

public class Cart {
    int cartId;
    boolean purchased;
    private Collection<Item> items;

    public Cart(int cartId, Collection<Item> items, boolean purchased) {
        this.cartId = cartId;
        this.items = items;
        this.purchased = purchased;
    }

    public int getCartId(){
        return cartId;
    }

    public void setCartId(int cartId){
        this.cartId = cartId;
    }

    public Collection<Item> getProducts() {
        return items;
    }

    public void setProducts(Collection<Item> products) {
        this.items = products;
    }

    public boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

}
