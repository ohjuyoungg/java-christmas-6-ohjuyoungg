package christmas.service;

import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventService {

    public void printMenu(Order userOrder) {
        List<String> orderedMenu = new ArrayList<>();

        Map<String, Integer> orderItems = userOrder.getOrderItems();
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();

            orderedMenu.add(String.format("%s %d개", menuName, quantity));
        }
        Collections.reverse(orderedMenu);
        for (String menu : orderedMenu) {
            System.out.println(menu);
        }
    }
}