package com.store.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class ProductDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername ="springuser";
    private static final String dbPassword ="ThePassword";

    public ProductDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Product createProduct(Product product){
        //TODO:
        String query = "INSERT into products(itemId, name, msrp, salePrice, upc, shortDescription, brandName," +
                "size, color, gender values(?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(query, product.getItemId(), product.getName(), product.getMsrp(), product.getSalePrice(),
                product.getUpc(), product.getShortDescription(), product.getBrandName());

        return product;
    }

    public Product getProduct(int itemId){
        String query = "SELECT * FROM products WHERE itemId = ?";

        Product product = this.jdbcTemplate.queryForObject(query, new Object[] {itemId},
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
        String query = "UPDATE products set name = ?, msrp = ?, salePrice = ?, upc = ?, shortDescription = ?, " +
                "brandName = ?, size = ?, color = ?, gender = ? where itemId= ?";
        this.jdbcTemplate.update(query, product.getName(), product.getMsrp(), product.getSalePrice(), product.getUpc(),
            product.getShortDescription(), product.getBrandName(), product.getSize(), product.getColor(),
            product.getGender(), product.getItemId());
        return product;
    }

    public boolean deleteProduct(Product product) {
        String query = "DELETE from products where itemId =?";
        boolean deleteSuccess = this.jdbcTemplate.update(query, product.getItemId()) > 0;
        return deleteSuccess;
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setUrl(url);

        return dataSource;
    }
}
