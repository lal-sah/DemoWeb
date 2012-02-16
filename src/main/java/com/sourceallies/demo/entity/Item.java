/**
 * 
 */
package com.sourceallies.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author lsal
 * 
 */
@Entity
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7925388970439875454L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int viewId;

	@ManyToOne
	private Order order;

	private String product;

	private double price;

	private int quantity;

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the viewId
	 */
	public int getViewId() {
		return viewId;
	}

	/**
	 * @param viewId
	 *            the viewId to set
	 */
	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

}
