package com.store.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

@Repository
public class CartDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public CartDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    // Used in the POST method of our cartcontroller
    public void createCartUser(String customerUsername, Boolean purchased) {
        String query = "INSERT into cartusers(customerUsername, purchased) values(?,?)";
        this.jdbcTemplate.update(query, customerUsername, purchased);
    }

    public void createCartItem(int cartId, int itemId) {
        String query ="INSERT into cartitems(cartId, productId) values(?,?)";
        this.jdbcTemplate.update(query, cartId, itemId);
    }

    public Cart getActiveCartId(String customerUsername, Boolean purchased) {
        String query = "SELECT * FROM cartusers WHERE customerUsername=? AND purchased=?";
        Cart cart;
        try {
            cart = this.jdbcTemplate.queryForObject(query, new Object[]{customerUsername, purchased},
                    (rs, rowNum) -> new Cart(rs.getInt("cartId"), new ArrayList<Item>(), rs.getBoolean("purchased")));
        }
        catch (Exception e) {
            cart = null;
        }

        return cart;
    }
    // End used in POST

    public Collection<Cart> getCarts(String username) {
        String query = "SELECT * FROM cartusers WHERE customerUsername =?";
        Collection<Cart> carts = new ArrayList<Cart>();
        this.jdbcTemplate.query(query, new Object[]{username},
        (rs, rowNum) -> new Cart(rs.getInt("cartId"), getProductByCartId(rs.getInt("cartId")),
        rs.getBoolean("purchased"))).forEach(cart -> carts.add(cart));

        return carts;
    }

    public Collection<Item> getProductByCartId(int cartId){
        String query = "SELECT products.itemId, products.name, products.msrp, products.salePrice " +
                "FROM cartitems LEFT JOIN products ON cartitems.productID = products.itemId WHERE cartitems.cartId =?";
        Collection<Item> items = new ArrayList<Item>();
        this.jdbcTemplate.query(query, new Object[]{cartId},
                (rs, rowNum) -> new Item(rs.getInt("products.itemId"),rs.getString("products.name"),
                rs.getBigDecimal("products.msrp").doubleValue(),
                rs.getBigDecimal("products.salePrice").doubleValue())).forEach(item -> items.add(item));

        return items;
    }

    public Boolean deleteItem(int cartId, int productId){
        String query = "DELETE from cartitems where cartId=? AND productId =?";
        boolean deleteSuccess = this.jdbcTemplate.update(query, cartId, productId) > 0;
        return deleteSuccess;
    }

    public void purchaseCart(int cartId) {
        String query = "UPDATE cartusers set purchased=? where cartId=?";
        this.jdbcTemplate.update(query,true, cartId);
    }

    public Collection<String> getWhoPurchased(int productId){
        String query ="SELECT cartusers.customerUsername FROM cartusers INNER JOIN cartitems ON cartusers.cartId=cartitems.cartId where cartitems.productId=? AND purchased=?";
        Collection<String> usernames = new ArrayList<String>();
        this.jdbcTemplate.query(query, new Object[]{productId, true},
                (rs, rowNum) -> new String(rs.getString("cartusers.customerUsername"))).forEach(username -> usernames.add(username));
        return usernames;
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