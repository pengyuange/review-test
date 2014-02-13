package com.flextrade.review.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.flextrade.OrderWriter;
import com.flextrade.review.to.Order;

public class LargeOrderFilter { // A comment/author should be added for javadoc to pick it up.

    private OrderWriter orderWriter;
    private List<Order> orders;

    public LargeOrderFilter(OrderWriter orderWriter, List<Order> orders) {
        filterSize = "100"; // The constructor should also have the filterSize parameter, this way we don't hardcode values here and we have something more generic. This way we can also keep this class only and remove the SmallOrderFilter.
        this.orderWriter = orderWriter;
        this.orders = orders;
    }

    protected String filterSize; // This should be replaced with a private field and have a setter, much safer this way. Also, it should be placed above the constructor with the rest of the field declarations.

    public void writeOutFiltrdAndPriceSortedOrders(OrderWriter writer) { // Method name is spelled wrong and there's no need to pass the writer as a parameter because we already have it from the moment the object was instantiated via the constructor.
        List<Order> filteredOrders = this.filterOrdersSmallerThan(orders, filterSize); // No need for 'this' when calling methods from the same class. 
        Collections.sort(filteredOrders, new Comparator() { // In order to avoid casting later in the code, you can use 'new Comparator<Order>()'. Another option would have been to implement the Comparable interface in the Order POJO and make the sort logic there.
            @Override
            public int compare(Object o1, Object o2) {
                Order order1 = (Order) o1;
                Order order2 = (Order) o2;
                return (int) (order1.getPrice() - order2.getPrice()); // You should probably use something like 'Double.compare(order1.getPrice(), order2.getPrice());' or 'return new Double(order1.getPrice()).compareTo(order2.getPrice());' to avoid floating point problems. 
            }
        });
        writer.writeOrders(filteredOrders); // Again, orderWriter must be used here and we can get rid of the method parameter.
    }

    protected List<Order> filterOrdersSmallerThan(List<Order> allOrders, String size) { // Method can be private. The first parameter can be removed because we already have references to it. The second parameter should be named filterSize (we can also remove this, but it helps for better understanding of what the method does).
        List<Order> filtered = new ArrayList<Order>(); // Variable name should be filteredOrders
        for (int i = 0; i <= allOrders.size(); i++) { // It's easier to read the code if you have an iterator-based loop - 'for (Order order : orders)'
            int number = orders.get(i).toNumber(size); // The 'toNumber' method shouldn't be in the Order POJO, it should probably be somewhere in a util/helper class and be a static method because it's not related to the Order class at all. 
            		// Also, if this is refactored this way, you won't need to create a new 'number' variable for each iteration, it will be a global variable (won't be subject to GC as much as it is now). 
            if (allOrders.get(i).getSize() <= number) { 
                continue; // This means we filter orders greater than the filterSize, and not smaller than it so the check needs to be reversed (the addition to the list should be done here, not in the else body). Doing so, we can completely get rid of the else body, it will be redundant to have 'continue' in it.
            } else {
                filtered.add(orders.get(i));
            }
        }

        return filtered;
    }

}