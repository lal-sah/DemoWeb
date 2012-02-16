/**
 * 
 */
package com.sourceallies.demo.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.sourceallies.demo.dao.OrderDao;
import com.sourceallies.demo.entity.Order;

/**
 * @author lsal
 * 
 */
@Named("orderDao")
public class OrderDaoImpl/* extends HibernateDaoSupport */implements OrderDao,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1511522030329236248L;

	@Inject
	@Named("sessionFactory")
	private SessionFactory sessionFactory;

	@Inject
	@Named("transactionTemplate")
	private TransactionTemplate template;

	// @Inject
	// @Named("hibernateTemplate")
	// private HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sourceallies.demo.dao.OrderDao#addOrder(com.sourceallies.demo.web
	 * .bean.OrderBean)
	 */
	@Override
	public int addOrder(Order order) {
		Long id = (Long) getSession().save(order);
		Order order2 = (Order) getSession().get(Order.class, id);
		System.out.println("Order just saved: " + order2);
		System.out.println("Order id: " + id);
		return id.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sourceallies.demo.dao.OrderDao#modifyOrder(com.sourceallies.demo.
	 * web.bean.OrderBean)
	 */
	@Override
	public void modifyOrder(Order order) {
		getSession().saveOrUpdate(order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sourceallies.demo.dao.OrderDao#deleteOrder(com.sourceallies.demo.
	 * web.bean.OrderBean)
	 */
	@Override
	public void deleteOrder(int orderId) {
		Order order = findOrderById(orderId);
		getSession().delete(order);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sourceallies.demo.dao.OrderDao#findOrder(long)
	 */
	@Override
	public Order findOrderById(final int orderId) {
		return template.execute(new TransactionCallback<Order>() {
			@Override
			public Order doInTransaction(TransactionStatus arg0) {
				System.out.println("Order id: " + orderId);
				Order order = (Order) getSession().load(Order.class,
						(long) orderId);
				Hibernate.initialize(order);
				Hibernate.initialize(order.getItems());
				// getSession().flush();
				System.out.println("Order: " + order);
				return order;
			}
		});
		// System.out.println("Order id: " + orderId);
		// Order order = (Order) getSession().load(Order.class, (long) orderId);
		// System.out.println("Order: " + order);
		// return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sourceallies.demo.dao.OrderDao#findAllOrders()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrders() {
		return template.execute(new TransactionCallback<List<Order>>() {
			@Override
			public List<Order> doInTransaction(TransactionStatus status) {
				// return getHibernateTemplate().find("from Order");
				return getSession().createCriteria(Order.class).list();
			}

		});
	}

	/**
	 * @return
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
