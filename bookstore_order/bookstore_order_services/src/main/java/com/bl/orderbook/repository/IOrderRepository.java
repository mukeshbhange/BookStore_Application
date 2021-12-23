package com.bl.orderbook.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.orderbook.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
	
	
	/*
	 * @Query(value="Select * from order where user_id = ?1 And cancel is False"
	 * ,nativeQuery=true) List<Order> findOrderByUserId(long id);
	 */
}
