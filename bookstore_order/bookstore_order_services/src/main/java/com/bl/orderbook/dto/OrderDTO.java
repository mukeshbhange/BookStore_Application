package com.bl.orderbook.dto;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : OrderDTO
 *
 */
import lombok.Data;

@Data
public class OrderDTO {
	
	private long bookId;
	private long userId;
	private String address;
	private int quantity;
	private double price;

}
