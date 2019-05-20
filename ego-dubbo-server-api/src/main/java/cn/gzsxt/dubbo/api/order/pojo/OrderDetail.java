package cn.gzsxt.dubbo.api.order.pojo;

import java.io.Serializable;
import java.util.List;

import cn.gzsxt.manager.pojo.Order;
import cn.gzsxt.manager.pojo.OrderItem;
import cn.gzsxt.manager.pojo.OrderShipping;

public class OrderDetail extends Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OrderItem> orderItems;
	private OrderShipping orderShipping;
	public OrderDetail() {
		super();
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
