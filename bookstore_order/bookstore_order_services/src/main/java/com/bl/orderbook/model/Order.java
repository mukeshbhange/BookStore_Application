package com.bl.orderbook.model;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Order Model
 *
 */
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.orderbook.dto.OrderDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long bookId;
	private long userId;
	private String address;
	private int quantity;
	private boolean cancel;
	private double price;
	private LocalDateTime orderDate;
	
	public Order(OrderDTO orderDTO) {
		this.bookId = orderDTO.getBookId();
		this.userId = orderDTO.getUserId();
		this.address = orderDTO.getAddress();
		this.quantity = orderDTO.getQuantity();
		this.price = orderDTO.getPrice();
		this.cancel = false;
		this.orderDate = LocalDateTime.now();
	}

}
