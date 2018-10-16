package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CartService {

    private CartDAO cartDAO = new CartDAO();

    public void addToCart(int itemId, String username) {
        Cart cart = cartDAO.getActiveCartId(username, false);
        if (cart == null) {
            cartDAO.createCartUser(username, false);
            addToCart(itemId, username);
        }
        else
            cartDAO.createCartItem(cart.getCartId(), itemId);
    }

    public Collection<Cart> getCarts(String username) {
        Collection<Cart> carts = new ArrayList<Cart>();
        carts = cartDAO.getCarts(username);
        return carts;
    }

    public String deleteItem(int cartId, int productId){
        String retString = "";
        boolean success = cartDAO.deleteItem(cartId, productId);;

        if (success == true)
            return retString;

        return "failure";
    }

    public void purchaseCart(int cartId){
        cartDAO.purchaseCart(cartId);
    }

    public Collection<String> getWhoPurchased(int productId) {
        return cartDAO.getWhoPurchased(productId);
    }
}