package com.bl.book_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	
	private String bookName;
	private String auther;
	private String description;
	private String logo;
	private double price;
	private int quantity;
	

}
