package com.bl.book_services.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : BookServices
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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
	
	private final Path fileLocation = Paths.get("C:\\Users\\dell\\Desktop\\BookStoreProject\\bookstore_book\\bookstore_book_services\\src\\main\\resources\\static\\image");
	
	
	/*checks User is Logged in*/
	public boolean isLogin(String token) {
		boolean isLogin = restTemplate.getForObject("http://USER-SERVICES/admin/verify?token="+token,Boolean.class);
		return isLogin;
	}
	
	/*Add Book to BookRepository*/
	@Override
	public Book addBook(String token, BookDTO bookDTO)  throws LoginException {
		if(isLogin(token)) {
			Book book = new Book(bookDTO);
			return bookRepo.save(book);
		}else {
			throw new LoginException("Access Denied...!");
		}
	}
	
	/*get Book from BookRepo*/
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
	
	
	/*Deleting book from BookRepo*/
	@Override
	public Book delete(String token, long id)throws BookNotFoundException, LoginException  {
		if(this.getBook(token, id) != null) {
			bookRepo.delete(this.getBook(token, id));
			return getBook(token, id);
		}else {
			throw new BookNotFoundException("User not present of this Id");
		}
	}
	
	
	/*Get All Books*/
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
	
	/* Edit book by BookId */
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
	
	/* Change Book Quantity*/
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
	
	/*Edit book by bookId*/
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

	/*@Override
	public Book setprofile(MultipartFile path, String token, long bookId)
			throws LoginException, BookNotFoundException {
		if(isLogin(token)) {
			Book book = this.getBook(token, bookId);
			try {
				Files.copy(path.getInputStream(),Paths.get("C:\\Users\\dell\\Desktop\\BookStoreProject\\bookstore_book\\bookstore_book_services\\src\\main\\resources\\static\\image\\"
						+ book.getId() + ".png"), StandardCopyOption.REPLACE_EXISTING);
				book.setLogo(path.getBytes());
				
				;
				if(bookRepo.save(book) != null ) {
					return book;
				}else {
					throw new BookNotFoundException("Error while saving to database");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
		return null;
	}*/
	
	public Book setprofile(MultipartFile path, String token, long bookId)
			throws LoginException, BookNotFoundException {
		if(isLogin(token)) {
			Book book = this.getBook(token, bookId);
			UUID uuid = UUID.randomUUID();

			String uniqueId = uuid.toString();
			try {
				Files.copy(path.getInputStream(), fileLocation.resolve(uniqueId), StandardCopyOption.REPLACE_EXISTING);
				book.setLogo(uniqueId);
				bookRepo.save(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
		return null;
	}

	@Override
	public Resource getBookLogo(String token, long bookId) throws BookNotFoundException, LoginException {
		if(isLogin(token)) {
			try {
				Book book = this.getBook(token, bookId);
				Path imageFile = fileLocation.resolve(book.getLogo());
				Resource resource = new UrlResource(imageFile.toUri());

				if (resource.exists() || (resource.isReadable())) {
					System.out.println(resource);
					return resource;
				} else {
					throw new Exception("Couldn't read file: " + imageFile);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}else {
			throw new LoginException("Access Denied...!");
		}
		
	}
	
}
