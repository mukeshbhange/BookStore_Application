package com.bl.cart_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bl.cart_service.dto.CartDTO;
import com.bl.cart_service.exception.CartNotFoundException;
import com.bl.cart_service.exception.LoginException;
import com.bl.cart_service.model.Cart;
import com.bl.cart_service.repository.ICartRepository;

@Service
public class CartServices implements ICartServies{
	
	@Autowired
	private ICartRepository cartRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean isLogin(String token) {
		boolean isLogin = restTemplate.getForObject("http://localhost:8991/verify?token="+token,Boolean.class);
		return isLogin;
	}

	public Cart addToCart(String token, CartDTO cartDTO)throws LoginException {
		if(isLogin(token)) {
			throw new LoginException("Incorrect Token.");
		}
		Cart cart = new Cart(cartDTO);
		return cartRepo.save(cart);	
	}

	@Override
	public List<Cart> allCartsByUserId(String token, long userId)throws LoginException,CartNotFoundException {
		if(isLogin(token)) {
			List<Cart> cartList = cartRepo.findAll();
			
			List<Cart> byuserIdCarts = cartList.stream().filter(order->order.getUserId() == userId)
					.collect(Collectors.toList());

			if(byuserIdCarts.isEmpty()) {
				throw new CartNotFoundException("No Data Present in Database,of This User Id");
			}else {
				return byuserIdCarts;
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

	@Override
	public List<Cart> allCarts(String token)throws LoginException,CartNotFoundException {
		if(isLogin(token)) {
			List<Cart> cartList = new ArrayList<>();
			cartRepo.findAll().forEach(cartList::add);
			if(cartList.isEmpty()) {
				throw new CartNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return cartList;
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

	@Override
	public Cart removeCart(String token, long cartId)throws LoginException,CartNotFoundException {
		if(isLogin(token)) {
			Cart cart = cartRepo.findById(cartId).get();
			if(cart != null) {
				cartRepo.delete(cart);
				return cart;
			}else {
				throw new CartNotFoundException("Cart Of id: "+cartId+" not found");
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

	@Override
	public Cart updateQuantity(String token, long cartId, int quantity) throws LoginException, CartNotFoundException {
		if(isLogin(token)) {
			Cart cart = cartRepo.findById(cartId).get();
			if(cart != null) {
				cart.setQuantity(quantity);
				cartRepo.save(cart);
				return cart;
			}else {
				throw new CartNotFoundException("Cart Of id: "+cartId+" not found");
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

}
