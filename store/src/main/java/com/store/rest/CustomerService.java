package com.store.rest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService {

    JdbcTemplate jdbcTemplate;
    //@Autowired
    private CustomerDAO customerDAO = new CustomerDAO();

    public String getMsg(String msg)  {
        return "test : " + msg;
    }

    public String getAllCustomers() {
        String retString = "";
        Collection<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer: customers) {
            retString += customer.toString();
        }

        return retString;
    }
}

