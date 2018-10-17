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

    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getSearchMsg(@PathParam("keyword") String keyword) {
        /*TODO: Need to implement this through the cart service and DAO - query should be a bunch of or statements with
        or statements*/
        Collection<Product> output = productService.getProductByKeyword(keyword);
        return output;
    }

}
