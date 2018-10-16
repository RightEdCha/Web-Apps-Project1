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
@Path("/carts")
public class CartController extends HttpServlet {

    //@Autowired
    private CartService cartService = new CartService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        }catch(ServletException e){
        }
    }

    @POST //create
    public void postMsg(@QueryParam("productId") int itemId, @QueryParam("username") String username ) {
        cartService.addToCart(itemId, username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Collection<Cart> getCarts(@QueryParam("username") String username) {
        Collection<Cart> carts = cartService.getCarts(username);
        return carts;
    }
    @DELETE
    public Response deleteMsg(@QueryParam("cartId") int cartId, @QueryParam("productId") int productId){

        String output = cartService.deleteItem(cartId, productId);
        return Response.status(200).entity(output).build();
    }
    @PUT
    @Path("/purchase/{cartId}")
    public void purchaseMsg(@PathParam("cartId") int cartId) {
        cartService.purchaseCart(cartId);
    }

    @GET
    @Path("/{productId}")       //When i tried this as a query param, the program complained and none of the other functions worked.
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Collection<String> getWhoPurchased(@PathParam("productId") int productId){
        return cartService.getWhoPurchased(productId);
    }

}