/**
 * 
 */
package com.sourceallies.demo.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.cdyne.ws.weatherws.GetCityWeatherByZIPResponse;
import com.cdyne.ws.weatherws.WeatherReturn;
import com.sourceallies.demo.entity.Item;
import com.sourceallies.demo.entity.Order;
import com.sourceallies.demo.service.OrderService;
import com.sourceallies.webservices.WeatherServiceClient;

/**
 * @author lsah
 * 
 */
// @Controller("orderController")
// @Component("orderController")
// @Qualifier(value = "orderController")
@Named("orderController")
@Scope("session")
public class OrderController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7124946677169067286L;

	private static final Logger LOGGER = Logger
			.getLogger(OrderController.class);

	public static final String HOME_PAGE = "index";
	public static final String ADD_ORDER_PAGE = "addOrder";
	public static final String VIEW_ORDER_PAGE = "viewOrder";
	public static final String DELETE_ORDER_PAGE = "deleteOrder";
	public static final String FIND_ORDER_PAGE = "findOrderById";
	public static final String LIST_ORDER_PAGE = "listOrders";

	private String zipCode = "50312";

	@Inject
	@Named("orderService")
	private OrderService orderService;

	@Inject
	@Named("weatherServiceClient")
	private WeatherServiceClient weatherServiceClient;

	// @Inject
	// @Named("webServiceTemplate")
	// private WebServiceTemplate webServiceTemplate;

	private WeatherReturn weatherReturn;

	private Order order;
	private int orderId;
	private int itemId;

	private String product;
	private double price;
	private int quantity;

	private List<Order> orders = null;

	private Date currentTime = new Date();

	// private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
	// "MM/dd/yyyy hh:mm:ss a z");

	public OrderController() {
		LOGGER.debug("Order controller: " + this);
		order = new Order();
	}

	@PostConstruct
	public void postConstruct() {
		// Weather weather = new Weather();
		// WeatherReturn weatherReturn12 = weather.getWeatherSoap12()
		// .getCityWeatherByZIP("50312");
		// LOGGER.info("Today's weather (SOAP12): "
		// + weatherReturn12.getTemperature());
		//
		// weatherReturn =
		// weather.getWeatherSoap().getCityWeatherByZIP("50312");
		// LOGGER.info("Today's weather (SOAP): " +
		// weatherReturn.getTemperature());

		LOGGER.info("Weather Service Client: " + weatherServiceClient);
		GetCityWeatherByZIPResponse response = weatherServiceClient
				.getWeatherByZipCode(zipCode);
		LOGGER.info("GetCityWeatherByZIPResponse: " + response);
		LOGGER.info("GetCityWeatherByZIPResponse Temperature: "
				+ response.getGetCityWeatherByZIPResult().getTemperature());
		weatherReturn = response.getGetCityWeatherByZIPResult();
	}

	public void updateWeather() {
		// Weather weather = new Weather();
		// weatherReturn =
		// weather.getWeatherSoap().getCityWeatherByZIP("50312");
		weatherReturn = weatherServiceClient.getWeatherByZipCode(zipCode)
				.getGetCityWeatherByZIPResult();
		LOGGER.info("Today's weather (SOAP): " + weatherReturn.getTemperature());
	}

	public void updateTime() {
		currentTime = new Date();
	}

	/**
	 * @return the currentTime
	 */
	public Date getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime
	 *            the currentTime to set
	 */
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * @return the weatherReturn
	 */
	public WeatherReturn getWeatherReturn() {
		return weatherReturn;
	}

	/**
	 * @param weatherReturn
	 *            the weatherReturn to set
	 */
	public void setWeatherReturn(WeatherReturn weatherReturn) {
		this.weatherReturn = weatherReturn;
	}

	/**
	 * Adds an order to the database.
	 * 
	 * @return
	 */
	public String addOrder() {
		// LOGGER.debug("Order service: " + orderService);
		int orderid = orderService.addOrder(order);
		FacesContext.getCurrentInstance().addMessage("ODRER_SUCCESS",
				new FacesMessage("Order " + orderid + " added successfully!"));

		// clear the order from the session
		order = new Order();
		return OrderController.ADD_ORDER_PAGE;
	}

	/**
	 * Adds a new controller to the session and returns to the add order page.
	 * 
	 * @return
	 */
	public String newAddOrderPage() {
		// clear the order from session
		order = new Order();
		setProduct(null);
		setPrice(0.0);
		setQuantity(0);
		return OrderController.ADD_ORDER_PAGE;
	}

	/**
	 * Adds an item to an order and returns to the same page.
	 * 
	 * @return
	 */
	public String addItem() {
		Item item = new Item();
		item.setViewId(new Random().nextInt(1000000000));
		item.setProduct(product);
		item.setPrice(price);
		item.setQuantity(quantity);
		order.getItems().add(item);
		return null;
	}

	/**
	 * Deletes an item from the order and returns to the same page.
	 * 
	 * @return
	 */
	public String deleteItem(int id) {
		LOGGER.debug("order id: " + id);
		for (Item item : order.getItems()) {
			if (item.getViewId() == id) {
				LOGGER.debug("");
				order.getItems().remove(item);
				break;
			}
		}
		return null;
	}

	/**
	 * Deletes an item from the order and returns to the same page.
	 * 
	 * @return
	 */
	public String deleteItem(long id) {
		for (Item item : order.getItems()) {
			if (item.getViewId() == id) {
				order.getItems().remove(item);
				break;
			}
		}
		return null;
	}

	/**
	 * Finds the order from the database given an order id.
	 * 
	 * @return to the View Order details page.
	 */
	public String findOrderById() {
		String result = OrderController.FIND_ORDER_PAGE;
		Order temp = orderService.findOrderById(orderId);
		// LOGGER.debug("Order found: " + temp);
		if (temp != null) {
			order = temp;
			LOGGER.debug("Order items count: " + order.getItems().size());
			result = OrderController.VIEW_ORDER_PAGE;
		}
		return result;
	}

	/**
	 * Finds the order from the database given an order id.
	 * 
	 * @return to the View Order details page.
	 */
	public String findOrderById(long orderId) {
		String result = OrderController.FIND_ORDER_PAGE;
		Order temp = orderService.findOrderById((int) orderId);

		LOGGER.debug("Order found: " + temp);
		if (temp != null) {
			order = temp;
			result = OrderController.VIEW_ORDER_PAGE;
		}
		return result;
	}

	/**
	 * Updates the current order.
	 * 
	 * @return
	 */
	public String updateOrder() {
		String result = OrderController.VIEW_ORDER_PAGE;
		orderService.modifyOrder(order);
		FacesContext.getCurrentInstance().addMessage(
				"ORDER_MODIFIED",
				new FacesMessage("Order " + order.getId()
						+ " updated successfully!"));
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public String deleteOrder() {
		orderService.deleteOrder(orderId);
		FacesContext.getCurrentInstance().addMessage("ORDER_DELETED",
				new FacesMessage("Order " + orderId + " deleted successfuly!"));
		return OrderController.HOME_PAGE;
	}

	public String listOrders() {
		orders = orderService.findAllOrders();
		LOGGER.debug("Number of orders found: " + orders.size());
		return OrderController.LIST_ORDER_PAGE;
	}

	public Order getOrder() {
		return order;
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
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}