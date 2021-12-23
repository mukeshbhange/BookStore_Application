package com.bl.book_services.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bl.book_services.dto.BookDTO;
import com.bl.book_services.exception.BookNotFoundException;
import com.bl.book_services.exception.LoginException;
import com.bl.book_services.model.Book;
import com.bl.book_services.repository.IBookRepository;

@Service
public class BookServicesImpl implements IBookServices{
	@Autowired
	private IBookRepository bookRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean isLogin(String token) {
		boolean isLogin = restTemplate.getForObject("http://USER-SERVICES/admin/verify?token="+token,Boolean.class);
		return isLogin;
	}

	@Override
	public Book addBook(String token, BookDTO bookDTO)  throws LoginException {
		if(isLogin(token)) {
			Book book = new Book(bookDTO);
			return bookRepo.save(book);
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public Book getBook(String token, long id) throws BookNotFoundException, LoginException {
		if(!isLogin(token)) {
			throw new LoginException("Access Denied...!");
		}else {
			Optional<Book> isPresent = bookRepo.findById(id);
			if(isPresent.isPresent()) {
				return isPresent.get();
			}else {
				throw new BookNotFoundException("Book not present of this Id");
			}
			
		}
	}

	@Override
	public Book delete(String token, long id)throws BookNotFoundException, LoginException  {
		if(this.getBook(token, id) != null) {
			bookRepo.delete(this.getBook(token, id));
			return getBook(token, id);
		}else {
			throw new BookNotFoundException("User not present of this Id");
		}
	}

	@Override
	public List<Book> getAllBooks(String token) throws BookNotFoundException, LoginException {
		if(isLogin(token)) {
			List<Book> userData = new ArrayList<>();
			bookRepo.findAll().forEach(userData::add);
			if(userData.isEmpty()) {
				throw new BookNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return userData;
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public Book editBook(String token, long id, BookDTO bookDTO) throws BookNotFoundException, LoginException {
		if(isLogin(token)) {
			throw new LoginException("Access Denied...!");	
		}else {
			if(this.getBook(token, id) != null) {
				Book isbookPresent = this.getBook(token, id);
				isbookPresent.setBookName(bookDTO.getBookName());
				isbookPresent.setAuther(bookDTO.getAuther());
				isbookPresent.setDescription(bookDTO.getDescription());
				isbookPresent.setLogo(bookDTO.getLogo());
				isbookPresent.setPrice(bookDTO.getPrice());
				isbookPresent.setQuantity(bookDTO.getQuantity());
				
				return bookRepo.save(isbookPresent);
			}else {
				throw new BookNotFoundException("No Data Present in Database,First Add Data");
			}
		}
	}

	@Override
	public Book editBookQuantity(String token, long id, int quantity) throws LoginException, BookNotFoundException {
		if(isLogin(token)) {
			Book book = this.getBook(token, id);
			book.setQuantity(quantity);
			bookRepo.save(book);
			return book;	
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public Book editBookPrice(String token, long id, double price) throws LoginException, BookNotFoundException{
		if(isLogin(token)) {
			Book book = this.getBook(token, id);
			book.setPrice(price);;
			bookRepo.save(book);
			return book;	
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

}