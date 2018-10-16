package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public Collection<Product> getAllProducts() {
        Collection<Product> products = productDAO.getAllProducts();

        return products;
    }

    public Product getProduct(int id){

        return productDAO.getProduct(id);
    }
/*
    public Product createProduct(Product product) {

        return productDAO.createProduct(product);
    }

    public Product updateProduct(Product product) {

        return productDAO.updateProduct(product);
    }

    public String deleteProduct(int id) {
        String retString = "";
        boolean success = productDAO.deleteProduct(id);

        if (success == true)
            return retString;

        return "failure";
    }
    */
}