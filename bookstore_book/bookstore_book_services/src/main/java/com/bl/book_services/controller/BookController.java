package com.bl.book_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bl.book_services.dto.BookDTO;
import com.bl.book_services.exception.BookNotFoundException;
import com.bl.book_services.exception.LoginException;
import com.bl.book_services.model.Book;
import com.bl.book_services.response.Response;
import com.bl.book_services.services.IBookServices;
/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : RestAPI controller for Book MicroServices
 *
 */
@RequestMapping("/bookservices")
@RestController
public class BookController {
	
	@Autowired
	private IBookServices bookServices;
	
	@PostMapping("/add")
	public ResponseEntity<Response> addBook(@RequestHeader String token,@RequestBody BookDTO bookDTO) throws LoginException{
		Book book = bookServices.addBook(token,bookDTO);
		Response response = new Response("Book Added Successfully",(long)200,book);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbook")
	public ResponseEntity<Book> getBook(@RequestParam String token,@RequestParam long id) throws LoginException, BookNotFoundException{		
		if(!token.isEmpty()) {
			Book book =bookServices.getBook(token,id);
			//Response response = new Response("Book Profile got SuccessFully",(long)200,book);
			return new ResponseEntity<Book>(book,HttpStatus.OK);	
		}else {
			throw new LoginException("Token / id is incoorect");
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestParam String token,@RequestParam long id) throws LoginException, BookNotFoundException{
		if(!token.isEmpty()) {
			Book book =bookServices.delete(token,id);
			Response response = new Response("Book Profile deleted SuccessFully",(long)200,book);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@GetMapping("/allbooks")
	public ResponseEntity<Response> getAllBooks(@RequestParam String token) throws LoginException, BookNotFoundException{
		if(!token.isEmpty()) {
			List<Book> allBooks =bookServices.getAllBooks(token);
			Response response = new Response("All Books got SuccessFully",(long)200,allBooks);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Response> editBook(@RequestParam String token, @RequestParam long id,  @RequestBody BookDTO bookDTO) throws BookNotFoundException, LoginException {
		if(!token.isEmpty()) {
			Book updatedUser = bookServices.editBook(token, id, bookDTO);
			Response response = new Response("Updated Book successfully",(long)200, updatedUser);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@GetMapping("/changequantity")
	public ResponseEntity<Book> editBookQuantity(@RequestParam String token, @RequestParam long id,  @RequestParam int quantity) throws BookNotFoundException, LoginException {
		if(!token.isEmpty()) {
			Book book = bookServices.editBookQuantity(token, id, quantity);
			//Response response = new Response("Updated Quantity successfully",(long)200, book);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@GetMapping("/changeprice")
	public ResponseEntity<Response> editBookPrice(@RequestParam String token, @RequestParam long id,  @RequestParam double price) throws BookNotFoundException, LoginException {
		if(!token.isEmpty()) {
			Book updatedUser = bookServices.editBookPrice(token, id, price);
			Response response = new Response("Updated price successfully",(long)200, updatedUser);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}else {
			throw new LoginException("Token / id is incoorect");
		}
	}
	
	@PostMapping(value = "/uploadlogo")
	ResponseEntity<String> setprofile(@RequestParam("file") MultipartFile file,@RequestHeader String token,@RequestParam long bookId) throws LoginException, BookNotFoundException
	{
		System.out.println("-----"+file.getOriginalFilename());
		if(file.isEmpty()) {
			throw new BookNotFoundException("File path not selected");
		}
		bookServices.setprofile(file, token,bookId);
		return new ResponseEntity<>("Book Logo uploaded successfully.", HttpStatus.OK);
	}
	
	@GetMapping(value = "/getbooklogo")
	Resource getBookLogo(@RequestHeader String token,@RequestParam long bookId) throws LoginException, BookNotFoundException
	{
		Resource path = bookServices.getBookLogo(token,bookId);
		return path;
	}

}
