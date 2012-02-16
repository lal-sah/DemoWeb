/**
 * 
 */
package com.sourceallies.demo.service;

import java.util.List;

import com.sourceallies.demo.entity.Order;

/**
 * @author lsal
 * 
 */
public interface OrderService {
	public int addOrder(Order order);

	public void modifyOrder(Order order);

	public Order findOrderById(int orderId);

	public List<Order> findAllOrders();

	public void deleteOrder(int orderId);
}
