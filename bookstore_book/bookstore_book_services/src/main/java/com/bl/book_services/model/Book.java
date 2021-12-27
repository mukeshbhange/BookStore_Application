package com.bl.book_services.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.book_services.dto.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : BookModel
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String bookName;
	private String auther;
	private String description;
	private String logo;
	private double price;
	private int quantity;
	
	public Book(BookDTO bookDTO) {
		this.bookName = bookDTO.getBookName();
		this.auther = bookDTO.getAuther();
		this.description = bookDTO.getDescription();
		this.logo = bookDTO.getLogo();
		this.price = bookDTO.getPrice();
		this.quantity = bookDTO.getQuantity();
	}

}
