package com.flextrade.test;

import java.util.List;

import com.flextrade.OrderStore;
import com.flextrade.review.to.Order;

public class StubOrderStore implements OrderStore {

	private List<Order> orders;
	
	public StubOrderStore(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

}
