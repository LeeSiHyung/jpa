package com.jpa.app.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name="ORDER_ITEM_ID")
	private Long id;
	
	// @Column(name="ITEM_ID")
	// private Long itemId;
	
	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	private Order order;
	
	// @Column(name="ORDER_ID")
	// Aprivate Long orderId;
	
	private int OrderPrice;
	private int count;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	// public Long getItemId() {
	// 	return itemId;
	// }
	// public void setItemId(Long itemId) {
	// 	this.itemId = itemId;
	// }
	// public Long getOrderId() {
	// 	return orderId;
	// }
	// public void setOrderId(Long orderId) {
	// 	this.orderId = orderId;
	// }
	public int getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		OrderPrice = orderPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	

}
