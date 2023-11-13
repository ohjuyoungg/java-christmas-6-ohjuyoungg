package christmas.domain.order;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<String, Integer> orderItems;

    public Order() {
        this.orderItems = new HashMap<>();
    }

    public void addOrder(String menu, int quantity) {
        orderItems.put(menu, quantity);
    }

    public Map<String, Integer> getOrderItems() {
        return new HashMap<>(orderItems);
    }
}
