package com.bl.orderbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bl.orderbook.dto.OrderDTO;
import com.bl.orderbook.exception.LoginException;
import com.bl.orderbook.exception.OrderNotFoundException;
import com.bl.orderbook.model.Book;
import com.bl.orderbook.model.Order;
import com.bl.orderbook.repository.IOrderRepository;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Order Services
 *
 */
@Service
public class OrderServices implements IOrderServices {
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean isLogin(String token) {
		boolean isLogin = restTemplate.getForObject("http://USER-SERVICES/admin/verify?token="+token,Boolean.class);
		return isLogin;
	}

	@SuppressWarnings("unused")
	@Override
	public Order placeOrder(String token, OrderDTO orderDTO) throws LoginException, OrderNotFoundException {
		if(!isLogin(token)) {
			throw new LoginException("Incorrect Token.");
		}else {
			Order order = new Order(orderDTO);
			
			Book book = restTemplate.getForObject("http://BOOKSTORE-BOOK-SERVICES/bookservices/getbook?token="+token+
					"&id="+orderDTO.getBookId(),Book.class);
			System.out.println(book.toString());
			if(book != null) {
				if(book.getQuantity() > order.getQuantity()) {
					//minus the book quantity from book repo
					int quantity = book.getQuantity() - order.getQuantity();
					restTemplate.getForObject("http://BOOKSTORE-BOOK-SERVICES/bookservices/changequantity?token="+token+"&id="+
					orderDTO.getBookId()+"&quantity="+quantity,Book.class);
					order.setPrice(book.getPrice()*orderDTO.getQuantity());
					return orderRepo.save(order);
				}else {
					throw new OrderNotFoundException("Only "+book.getQuantity()+" avaialables.\n Please Order less.");
				}
				
			}else {
				throw new OrderNotFoundException("Book of this id is not present");
			}
			
		}		
	}

	@Override
	public Order cancelOrder(String token, long orderId) throws OrderNotFoundException, LoginException {
		if(isLogin(token)) {
			Optional<Order> order = orderRepo.findById(orderId);
			if(order.isPresent()) {
				order.get().setCancel(true);
				return orderRepo.save(order.get());
			}else {
				throw new OrderNotFoundException("Order of "+orderId+" not found");
			}
			
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

	@Override
	public List<Order> allOrders(String token) throws OrderNotFoundException, LoginException {
		if(isLogin(token)) {
			List<Order> orderList = new ArrayList<>();
			orderRepo.findAll().forEach(orderList::add);
			if(orderList.isEmpty()) {
				throw new OrderNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return orderList;
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

	@Override
	public List<Order> allOrdersById(String token, long userId) throws OrderNotFoundException, LoginException {
		if(isLogin(token)) {
			List<Order> orderList = orderRepo.findAll();
			
			List<Order> byuserOrders = orderList.stream().filter(order->order.getUserId() == userId)
					.filter(order->!order.isCancel()).collect(Collectors.toList());
			if(byuserOrders.isEmpty()) {
				throw new OrderNotFoundException("No Data Present in Database,of This User Id");
			}else {
				return byuserOrders;
			}
		}else{
			throw new LoginException("Incorrect Token.");
		}
	}

}
