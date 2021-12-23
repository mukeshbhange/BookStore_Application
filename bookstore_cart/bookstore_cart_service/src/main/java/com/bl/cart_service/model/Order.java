package com.bl.cart_service.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
	
	private long id;
	
	private long bookId;
	private long userId;
	private String address;
	private int quantity;
	private boolean cancel;
	private double price;
	private LocalDateTime orderDate;

}
