package com.flextrade.review;

import java.util.List;

import com.flextrade.DefaultOrderWriter;
import com.flextrade.OrderStore;
import com.flextrade.review.filter.LargeOrderFilter;
import com.flextrade.review.filter.SmallOrderFilter;
import com.flextrade.review.to.Order;

public class OrderManager { // A comment/author should be added for javadoc to pick it up

    private final OrderStore orderStore; // This shouldn't be final if we plan of having multiple orderStores. Are we planning that? :)

    public OrderManager(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

    public void writeOutSmallOrders() {
        List<Order> orders = orderStore.getOrders(); // We need to check here if orderStore is null so we avoid NPE and check the list size (if the size is 0, we should exit the method)
        SmallOrderFilter filter = new SmallOrderFilter(new DefaultOrderWriter(), orders); 
        filter.writeOutFiltrdAndPriceSortedOrders(new DefaultOrderWriter()); // As explained in the LargeOrderFilter class, you don't need to pass the writer as a parameter.
    }

    public void writeOutLargeOrders() { // This is duplicated code, it would have been easier to just have one method that takes a filterSize parameter and just use the LargeOrderFilter class (actually rename it to something more generic la OrderFilter).
        List<Order> orders = orderStore.getOrders();
        LargeOrderFilter filter = new LargeOrderFilter(new DefaultOrderWriter(), orders);
        filter.writeOutFiltrdAndPriceSortedOrders(new DefaultOrderWriter());
    }

}