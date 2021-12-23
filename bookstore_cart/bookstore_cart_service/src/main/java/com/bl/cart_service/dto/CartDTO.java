package com.bl.cart_service.dto;

import lombok.Data;

@Data
public class CartDTO {
	
	private long userId;
	private long bookId;
	private int quantity;

}
