package com.bl.orderbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bl.orderbook.dto.OrderDTO;
import com.bl.orderbook.exception.LoginException;
import com.bl.orderbook.exception.OrderNotFoundException;
import com.bl.orderbook.model.Order;
import com.bl.orderbook.response.Response;
import com.bl.orderbook.services.IOrderServices;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderServices orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Response> placeOrder(@RequestHeader String token,@RequestBody OrderDTO orderDTO) throws LoginException, OrderNotFoundException{
		Order order = orderService.placeOrder(token,orderDTO);
		Response response = new Response("Order Placed Successfully.",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/cancelOrder")
	public ResponseEntity<Response> cancelOrder(@RequestHeader String token,@RequestParam long orderId)throws LoginException,OrderNotFoundException{
		Order order = orderService.cancelOrder(token,orderId);
		Response response = new Response("Order deleted Successfully.",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/allorders")
	public ResponseEntity<Response> allOrders(@RequestHeader String token)throws LoginException,OrderNotFoundException{
		List<Order> order = orderService.allOrders(token);
		Response response = new Response("All orders got Successfully.",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/allordersbyid")
	public ResponseEntity<Response> allOrdersById(@RequestHeader String token,@RequestParam long userId)throws LoginException,OrderNotFoundException{
		List<Order> order = orderService.allOrdersById(token,userId);
		Response response = new Response("All orders got Successfully.",(long)200,order);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
}
