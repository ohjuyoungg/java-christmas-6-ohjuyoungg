package christmas.domain.order;

import static christmas.domain.menu.MenuInfo.isDessert;
import static christmas.service.BeforeEventService.getMenuPrice;

import christmas.domain.menu.MenuInfo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private final Map<String, Integer> orderItems;

    public Order() {
        this.orderItems = new HashMap<>();
    }

    public void addOrder(String menu, int quantity) {
        orderItems.put(menu, quantity);
    }

    public LinkedHashMap<String, Integer> getOrderItems() {
        return new LinkedHashMap<>(orderItems);
    }

    public int calculateDessertDiscount(int discountPerMenu) {
        int dessertCount = getOrderItems().entrySet().stream()
            .filter(entry -> isDessert(entry.getKey()))
            .mapToInt(Map.Entry::getValue)
            .sum();

        return dessertCount * discountPerMenu;
    }

    public int calculateMainDishDiscount(int discountAmount) {
        return getOrderItems().entrySet().stream()
            .filter(entry -> !MenuInfo.isDessert(entry.getKey()))
            .mapToInt(entry -> getMenuPrice(entry.getKey()) * entry.getValue())
            .map(price -> Math.max(price - discountAmount, 0))
            .sum();
    }
}
