package com.bl.orderbook.dto;

import lombok.Data;

@Data
public class OrderDTO {
	
	private long bookId;
	private long userId;
	private String address;
	private int quantity;
	private double price;

}
