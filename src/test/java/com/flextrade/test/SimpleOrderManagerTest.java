package com.flextrade.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flextrade.OrderStore;
import com.flextrade.OrderWriter;
import com.flextrade.review.to.Order;
import com.flextrade.test.exception.InvalidOperationException;
import com.google.common.collect.Lists;

/**
 * Tests for {@link SimpleOrderManager}
 *
 * @author Paul Pop
 *
 */
@SuppressWarnings("unused")
public class SimpleOrderManagerTest {
	
	private StubOrderStore orderStore;
	private StubOrderWriter orderWriter;
	
	private SimpleOrderManager simpleOrderManager;
	
	@Test(expectedExceptions = NullPointerException.class)
	public void testNPEIfOrderStoreIsNull() {
		//given
		orderStore = new StubOrderStore(null);
		simpleOrderManager = new SimpleOrderManager(orderStore, orderWriter);
		
		//when
		simpleOrderManager.writeAllOrders();
		
		//then
		Mockito.verifyZeroInteractions(orderWriter);
	}
	
	@Test(expectedExceptions = InvalidOperationException.class, expectedExceptionsMessageRegExp = "No orders in store")
	public void testCheckedExceptionIfOrderStoreIsEmpty() {
		//given
		orderStore = new StubOrderStore(new ArrayList<Order>());
		simpleOrderManager = new SimpleOrderManager(orderStore, orderWriter);
		
		//when
		simpleOrderManager.writeAllOrders();
		
		//then
		Mockito.verifyZeroInteractions(orderWriter);
	}
	
	@Test(dataProvider = "validOrderStore")
	public void testWriteAllOrders(int storeSize, boolean checkListItems, OrderStore orderStore) {
		//given
		orderWriter = new StubOrderWriter();
		simpleOrderManager = new SimpleOrderManager(orderStore, orderWriter);
		
		//when
		simpleOrderManager.writeAllOrders();
		
		//then
		Assert.assertEquals(orderStore.getOrders().size(), storeSize);
		if (checkListItems) {
			Assert.assertEquals(orderStore.getOrders().get(0).getPrice(), 5d);
			Assert.assertEquals(orderStore.getOrders().get(0).getSize(), 3);
			Assert.assertEquals(orderStore.getOrders().get(0).getSymbol(), "ssSS");
		}
	}
	
	@DataProvider(name = "validOrderStore")
	private Object[][] getValidOrderStore() {
		return new Object[][] {
				{3, false, createOrderStore(Lists.newArrayList(createOrder(1, 1, "s"), createOrder(2, 2, "s"), createOrder(3, 3, "s")))},
				{2, false, createOrderStore(Lists.newArrayList(createOrder(1, 2, "ss"), createOrder(1, 3, "sss")))},
				{1, true, createOrderStore(Lists.newArrayList(createOrder(5, 3, "ssSS")))},
		};
	}
	
	private OrderStore createOrderStore(final List<Order> orderList) {
		return new OrderStore() {
			@Override
			public List<Order> getOrders() {
				return orderList;
			}
		};
	}
	
	private Order createOrder(double price, int size, String symbol) {
		Order order = new Order();
		order.setPrice(price);
		order.setSize(size);
		order.setSymbol(symbol);
		return order;
	}
	
}