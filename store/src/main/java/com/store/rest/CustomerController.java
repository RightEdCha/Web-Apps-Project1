package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet {

    //@Autowired
    private CustomerService customerService = new CustomerService();

    public void init(ServletConfig config) {
        try{
                super.init(config);
                SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        }catch(ServletException e){
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public  Collection<Customer> getAllCustomers() {
        Collection<Customer> customers = customerService.getAllCustomers();

        return customers;
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Customer getMsg(@PathParam("username") String username) {
        Customer output = customerService.getCustomer(username);

        return output;
    }

    @POST //create
    public Customer postMsg(@QueryParam("username") String username, @QueryParam("fname") String fname,
                           @QueryParam("lname") String lname, @QueryParam("email") String email) {

        Customer customer = new Customer(fname,lname,username,email);
        Customer output = customerService.createCustomer(customer);

        return output;
    }

    @PUT //Update
    public Customer putMsg(@QueryParam("username") String username, @QueryParam("fname") String fname,
                           @QueryParam("lname") String lname, @QueryParam("email") String email) {

        Customer customer = new Customer(fname,lname,username,email);

        return customerService.updateCustomer(customer);
    }

    @DELETE
    @Path("/{username}")
    public Response deleteMsg(@PathParam("username") String username){

        String output = customerService.deleteCustomer(username);
        return Response.status(200).entity(output).build();
    }

}
