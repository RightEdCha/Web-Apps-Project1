package com.store.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class CustomerDAO {
    private JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
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
        CustomerDAO customerDAO = new CustomerDAO(jdbcTemplate);
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
        boolean deleteSuccess = this.jdbcTemplate.update(query customer.getId(), customer.getFName(), customer.getLName(),
                customer.getUsername(), customer.getEmail()) > 0;
        return deleteSuccess;
    }
}

