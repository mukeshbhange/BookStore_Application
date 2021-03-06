package com.bl.book_services.dto;


import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : BookDTO
 *
 */
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
