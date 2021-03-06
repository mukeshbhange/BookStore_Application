package com.bl.cart_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bl.cart_service.dto.CartDTO;
import com.bl.cart_service.exception.CartNotFoundException;
import com.bl.cart_service.exception.LoginException;
import com.bl.cart_service.model.Cart;
import com.bl.cart_service.response.Response;
import com.bl.cart_service.services.ICartServies;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ICartServies cartServices;
	
	 
	
	@PostMapping("/addtocart")
	public ResponseEntity<Response> addToCart(@RequestHeader String token, @RequestBody CartDTO cartDTO) throws LoginException{
		Cart cart = cartServices.addToCart(token,cartDTO);
		Response response = new Response("Order Added to Cart.",(long)200,cart);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/removecart")
	public ResponseEntity<Response> cancelOrder(@RequestHeader String token,@RequestParam long cartId)throws LoginException,CartNotFoundException{
		Cart cart = cartServices.removeCart(token,cartId);
		Response response = new Response("Cart Removed Successfully.",(long)200,cart);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/allcarts")
	public ResponseEntity<Response> allOrders(@RequestHeader String token)throws LoginException,CartNotFoundException{
		List<Cart> order = cartServices.allCarts(token);
		Response response = new Response("All orders got Successfully.",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/allordersbyid")
	public ResponseEntity<Response> allOrdersById(@RequestHeader String token,@RequestParam long userId)throws LoginException,CartNotFoundException{
		List<Cart> order = cartServices.allCartsByUserId(token,userId);
		Response response = new Response("All carts got Successfully of id "+userId+".",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/updatequantity")
	public ResponseEntity<Response> updateQuantity(@RequestHeader String token,@RequestParam long cartId,@RequestParam int quantity)throws LoginException,CartNotFoundException{
		Cart cart = cartServices.updateQuantity(token,cartId,quantity);
		Response response = new Response("Cart book Quantity updated Successfully.",(long)200,cart);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
