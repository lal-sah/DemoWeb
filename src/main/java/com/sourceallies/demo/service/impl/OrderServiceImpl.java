/**
 * 
 */
package com.sourceallies.demo.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.sourceallies.demo.dao.OrderDao;
import com.sourceallies.demo.entity.Order;
import com.sourceallies.demo.service.OrderService;

/**
 * @author lsal
 * 
 */
@Named("orderService")
// @ContextConfiguration
public class OrderServiceImpl implements OrderService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984927731538272549L;
	
	@Inject
	@Named("orderDao")
	private OrderDao orderDao;

	@Override
	@Transactional
	public int addOrder(Order order) {
		return orderDao.addOrder(order);
	}

	@Override
	@Transactional
	public void modifyOrder(Order order) {
		orderDao.modifyOrder(order);
	}

	@Override
	@Transactional
	public void deleteOrder(int orderId) {
		orderDao.deleteOrder(orderId);
	}

	@Transactional
	@Override
	public Order findOrderById(int orderId) {
		return orderDao.findOrderById(orderId);
	}

	@Transactional
	@Override
	public List<Order> findAllOrders() {
		return orderDao.findAllOrders();
	}

	// public void setOrderDao(OrderDao orderDao) {
	// this.orderDao = orderDao;
	// }
	//
	// public OrderDao getOrderDao() {
	// return orderDao;
	// }
}
