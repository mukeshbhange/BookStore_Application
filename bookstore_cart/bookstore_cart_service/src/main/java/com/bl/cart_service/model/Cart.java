package com.bl.cart_service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.cart_service.dto.CartDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long userId;
	private long bookId;
	private int quantity;
	
	public Cart(CartDTO cartDTO) {
		this.bookId = cartDTO.getBookId();
		this.userId = cartDTO.getUserId();
		this.quantity = cartDTO.getQuantity();
	}

}
