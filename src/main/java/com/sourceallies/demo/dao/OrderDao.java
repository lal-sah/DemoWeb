/**
 * 
 */
package com.sourceallies.demo.dao;

import java.util.List;

import com.sourceallies.demo.entity.Order;

/**
 * @author lsal
 * 
 */
public interface OrderDao {
	public int addOrder(Order order);

	public void modifyOrder(Order order);

	public void deleteOrder(int orderId);

	public List<Order> findAllOrders();

	public Order findOrderById(int orderId);
}
