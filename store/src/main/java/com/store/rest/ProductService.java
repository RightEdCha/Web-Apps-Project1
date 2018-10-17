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

    public Collection<Product> getProductByKeyword(String keyword){
        return productDAO.getProductByKeyword(keyword);
    }
}