package com.flextrade.test;

import java.util.Collection;

import com.flextrade.OrderWriter;
import com.flextrade.review.to.Order;

public class StubOrderWriter implements OrderWriter {
	
	@Override
	public void writeOrders(Collection<Order> orders) {}

}
