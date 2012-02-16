/**
 * 
 */
package com.sourceallies.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lsal
 * 
 */
@Entity
@Table(name = "T_ORDER")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933284455450180531L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private List<Item> items = new ArrayList<Item>();

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
