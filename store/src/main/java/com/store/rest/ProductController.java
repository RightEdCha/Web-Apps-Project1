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
@Path("/items")
public class ProductController extends HttpServlet {

    private ProductService productService = new ProductService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        }catch(ServletException e){
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public  Collection<Product> getAllProducts() {
        Collection<Product> products = productService.getAllProducts();

        return products;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Product getMsg(@PathParam("id") int id) {
        Product output = productService.getProduct(id);

        return output;
    }

    /*
    @POST //create
    public Product postMsg(@QueryParam("id") String id, @QueryParam("fname") String fname,
                            @QueryParam("lname") String lname, @QueryParam("email") String email) {

        Product product = new Product(fname,lname,username,email);
        Product output = productService.createProduct(product);

        return output;
    }

    @PUT //Update
    public Product putMsg(@QueryParam("username") String username, @QueryParam("fname") String fname,
                           @QueryParam("lname") String lname, @QueryParam("email") String email) {

        Product product = new Product(fname,lname,username,email);

        return productService.updateProduct(product);
    }

    @DELETE
    @Path("/{username}")
    public Response deleteMsg(@PathParam("username") String username){

        String output = productService.deleteProduct(username);
        return Response.status(200).entity(output).build();
    }
    */
}
