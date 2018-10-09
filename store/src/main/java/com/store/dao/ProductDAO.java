package com.store.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Product createProduct(Product product){
        //TODO:
        String query = "INSERT into products(id,title) values(?,?)";
        this.jdbcTemplate.update(query, product.getId(), product.getTitle());
        return product;
    }

    public Product getProduct(int id){
        Product product = new Product(id, "", 0,0,0,"","","","","");
        //TODO:
        String query = "SELECT * FROM products WHERE ID = ?";
        ProductDAO trackDAO = new ProductDAO(jdbcTemplate);
        //Get product and set tracks using getTracksByproductId(id) in TracksDAO
        this.jdbcTemplate.queryForObject(query, new Object[] {id},
                (rs, rowNum) -> new Product(rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("msrp").doubleValue(),
                rs.getBigDecimal("saleprice").doubleValue(),
                rs.getInt("upc"),
                rs.getString"shortdescription"));
        product.setTracks(trackDAO.getTracksByproductId(id));
        return product;
    }

    public Collection<Product> getAllProducts(){
        Collection<product> products = new ArrayList<product>();
        String query = "SELECT * FROM products";
        this.jdbcTemplate.query(
                query, new Object[] { },
                (rs, rowNum) -> new product(rs.getInt("id"), rs.getString("title"))
        ).forEach(product -> products.add(product));

        return products;
    }

    public product updateProduct(product product){
        //TODO:
        String query = "UPDATE products set title = ? where id = ?";
        this.jdbcTemplate.update(query ,product.getTitle(),product.getId());
        return product;
    }

    public boolean deleteProduct(product product){
        boolean success = false;
        //TODO:
        String query = "DELETE from products where id =?";
        success = this.jdbcTemplate.update(query, product.getId()) > 0;
        return success;
}
