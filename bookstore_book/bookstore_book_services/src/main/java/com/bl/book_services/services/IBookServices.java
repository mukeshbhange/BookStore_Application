package com.bl.book_services.services;

import java.util.List;

import com.bl.book_services.dto.BookDTO;
import com.bl.book_services.exception.BookNotFoundException;
import com.bl.book_services.exception.LoginException;
import com.bl.book_services.model.Book;

public interface IBookServices {

	Book addBook(String token, BookDTO bookDTO) throws LoginException;

	Book getBook(String token, long id)throws LoginException, BookNotFoundException;

	Book delete(String token, long id)throws LoginException, BookNotFoundException;

	List<Book> getAllBooks(String token)throws LoginException, BookNotFoundException;

	Book editBook(String token, long id, BookDTO bookDTO)throws LoginException, BookNotFoundException;

	Book editBookQuantity(String token, long id, int quantity)throws LoginException, BookNotFoundException;

	Book editBookPrice(String token, long id, double price)throws LoginException, BookNotFoundException;
}
