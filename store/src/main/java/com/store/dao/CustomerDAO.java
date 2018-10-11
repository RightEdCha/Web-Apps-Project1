package com.store.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

@Repository
public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername ="springuser";
    private static final String dbPassword ="ThePassword";

    public CustomerDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Customer createCustomer(Customer customer){
        //TODO: Run a test on these functions.
        String query = "INSERT into customers(id, fname, lname, username, email) values(?,?,?,?,?)";
        this.jdbcTemplate.update(query, customer.getId(), customer.getFName(), customer.getLName(),
                customer.getUsername(), customer.getEmail());
        return customer;
    }

    public Customer getCustomer(int id){
        Customer customer = new Customer(id, "", "", "", "");
        //TODO: Run a test on these functions.
        CustomerDAO customerDAO = new CustomerDAO();
        //Get customer and set tracks using getProductsByCustomerId(id) in TracksDAO
        String query = "SELECT * FROM customers WHERE ID = ?";
        this.jdbcTemplate.queryForObject(query, new Object[] {id},
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("username"),
                rs.getString("email")));
        //Add a list of all products in customer's cart?

        return customer;
    }

    public Collection<Customer> getAllCustomers(){
        Collection<Customer> customers = new ArrayList<Customer>();

        String query = "SELECT * FROM customers";
        this.jdbcTemplate.query(
                query, new Object[] { },
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("username"),
                rs.getString("email"))).forEach(customer -> customers.add(customer));

        return customers;
    }

    public Customer updateCustomer(Customer customer){
        //TODO: Update this query to match with the customer object.
        String query = "UPDATE customers set fname = ?, lname = ?, username = ?, email =? where id = ?";
        this.jdbcTemplate.update(query, customer.getId(), customer.getFName(), customer.getLName(),
                customer.getUsername(), customer.getEmail());
        return customer;
    }

    public boolean deleteCustomer(Customer customer){
        //TODO: Update this query to match with the customer object.
        String query = "DELETE from customers where id =?";
        boolean deleteSuccess = this.jdbcTemplate.update(query, customer.getId(), customer.getFName(), customer.getLName(),
                customer.getUsername(), customer.getEmail()) > 0;
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

