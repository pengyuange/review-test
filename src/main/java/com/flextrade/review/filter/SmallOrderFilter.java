package com.flextrade.review.filter;

import java.util.List;

import com.flextrade.OrderWriter;
import com.flextrade.review.to.Order;

public class SmallOrderFilter extends LargeOrderFilter { // A comment/author should be added for javadoc to pick it up.

    public SmallOrderFilter(OrderWriter orderWriter, List<Order> orders) {
        super(orderWriter, orders); // No need to have this class at all, if everything is parameterized nicely, we can just use the extended class in all cases.
        filterSize = "10"; // As mentioned in the extended class, this should be a parameter in the constructor and not be hardcoded.
    }

}