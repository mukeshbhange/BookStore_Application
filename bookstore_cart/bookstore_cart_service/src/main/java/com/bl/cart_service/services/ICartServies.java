package com.bl.cart_service.services;

import java.util.List;

import com.bl.cart_service.dto.CartDTO;
import com.bl.cart_service.exception.CartNotFoundException;
import com.bl.cart_service.exception.LoginException;
import com.bl.cart_service.model.Cart;

public interface ICartServies {

	Cart addToCart(String token, CartDTO cartDTO) throws LoginException;

	List<Cart> allCartsByUserId(String token, long userId)throws LoginException,CartNotFoundException;

	List<Cart> allCarts(String token)throws LoginException,CartNotFoundException;

	Cart removeCart(String token, long cartId)throws LoginException,CartNotFoundException;

	Cart updateQuantity(String token, long cartId, int quantity)throws LoginException,CartNotFoundException;

}
