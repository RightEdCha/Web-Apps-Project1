package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public Collection<Customer> getAllCustomers() {
        Collection<Customer> customers = customerDAO.getAllCustomers();

        return customers;
    }

    public Customer getCustomer(String username){

        return customerDAO.getCustomer(username);
    }

    public Customer createCustomer(Customer customer) {

        return customerDAO.createCustomer(customer);
    }

    public Customer updateCustomer(Customer customer) {

        return customerDAO.updateCustomer(customer);
    }

    public String deleteCustomer(String username) {
        String retString = "";
        boolean success = customerDAO.deleteCustomer(username);

        if (success == true)
            return retString;

        return "failure";
    }
}

