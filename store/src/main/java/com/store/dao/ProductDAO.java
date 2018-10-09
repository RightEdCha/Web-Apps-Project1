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
        String query = "INSERT into products(itemId, name, msrp, salePrice, upc, shortDescription, brandName," +
                "size, color, gender) values(?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(query, product.getItemId(), product.getName(), product.getMsrp(), product.getSalePrice(),
                product.getUpc(), product.getShortDescription(), product.getBrandName());

        return product;
    }

    public Product getProduct(int itemId){
        Product product = new Product(itemId, "", 0,0,0,"","","","","");
        //TODO:
        String query = "SELECT * FROM products WHERE itemId = ?";
        ProductDAO trackDAO = new ProductDAO(jdbcTemplate);
        //Get product and set tracks using getTracksByproductId(id) in TracksDAO
        this.jdbcTemplate.queryForObject(query, new Object[] {itemId},
                (rs, rowNum) -> new Product(rs.getInt("itemId"),
                rs.getString("name"),
                rs.getBigDecimal("msrp").doubleValue(),
                rs.getBigDecimal("salePrice").doubleValue(),
                rs.getInt("upc"),
                rs.getString("shortDescription"),
                rs.getString("brandName"),
                rs.getString("size"),
                rs.getString("color"),
                rs.getString("gender")));
        return product;
    }

    public Collection<Product> getAllProducts(){
        Collection<Product> products = new ArrayList<Product>();
        String query = "SELECT * FROM products";
        this.jdbcTemplate.query(
                query, new Object[] { },
                (rs, rowNum) -> new Product(rs.getInt("itemId"),
                rs.getString("name"),
                rs.getBigDecimal("msrp").doubleValue(),
                rs.getBigDecimal("salePrice").doubleValue(),
                rs.getInt("upc"),
                rs.getString("shortDescription"),
                rs.getString("brandName"),
                rs.getString("size"),
                rs.getString("color"),
                rs.getString("gender"))).forEach(product -> products.add(product));
        return products;
    }

    public Product updateProduct(Product product){
        //TODO:
        String query = "UPDATE products set name = ?, msrp = ?, salePrice = ?, upc = ?, shortDescription = ?, " +
                "brandName = ?, size = ?, color = ?, gender = ? where itemId= ?";
        this.jdbcTemplate.update(query, product.getName(), product.getMsrp(), product.getSalePrice(), product.getUpc(),
            product.getShortDescription(), product.getBrandName(), product.getSize(), product.getColor(), product.getGender(), product.getItemId());
        return product;
    }

    public boolean deleteProduct(Product product){
        //TODO:
        String query = "DELETE from products where itemId =?";
        boolean deleteSuccess = this.jdbcTemplate.update(query, product.getItemId()) > 0;
        return deleteSuccess;
}
