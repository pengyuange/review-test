package com.flextrade;

import java.util.Collection;

import com.flextrade.review.to.Order;

public interface OrderWriter {

    void writeOrders(Collection<Order> orders);

}