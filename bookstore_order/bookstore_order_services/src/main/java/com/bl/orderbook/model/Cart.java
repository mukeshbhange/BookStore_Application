package com.bl.orderbook.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
	
	private long id;
	private long userId;
	private long bookId;
	private int quantity;
}
