package com.flextrade;

import java.util.List;

import com.flextrade.review.to.Order;

public interface OrderStore {

    List<Order> getOrders();

}