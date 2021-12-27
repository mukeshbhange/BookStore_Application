package com.bl.orderbook.services;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Interface
 *
 */
import java.util.List;

import com.bl.orderbook.dto.OrderDTO;
import com.bl.orderbook.exception.LoginException;
import com.bl.orderbook.exception.OrderNotFoundException;
import com.bl.orderbook.model.Order;

public interface IOrderServices {

	Order placeOrder(String token, OrderDTO orderDTO) throws LoginException, OrderNotFoundException;

	Order cancelOrder(String token, long orderId) throws OrderNotFoundException, LoginException;

	List<Order> allOrders(String token) throws OrderNotFoundException, LoginException;

	List<Order> allOrdersById(String token, long userId) throws OrderNotFoundException, LoginException;

}
