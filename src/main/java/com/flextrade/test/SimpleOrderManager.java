package com.flextrade.test;

import java.util.List;

import com.flextrade.OrderStore;
import com.flextrade.OrderWriter;
import com.flextrade.review.to.Order;
import com.flextrade.test.exception.InvalidOperationException;

public class SimpleOrderManager {

    private final OrderStore orderStore;
    private final OrderWriter orderWriter;

    public SimpleOrderManager(OrderStore orderStore, OrderWriter orderWriter) {
        this.orderStore = orderStore;
        this.orderWriter = orderWriter;
    }

    public void writeAllOrders() {
        List<Order> allOrders = orderStore.getOrders();
        if (allOrders.size() == 0) {
            throw new InvalidOperationException("No orders in store");
        }

        this.orderWriter.writeOrders(allOrders);
    }
}