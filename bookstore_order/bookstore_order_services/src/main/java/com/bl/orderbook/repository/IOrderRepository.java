package com.bl.orderbook.repository;


/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 * purpose : Order Repository
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.orderbook.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

}
