package christmas.service;

import christmas.domain.menu.MenuInfo;
import christmas.domain.order.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeforeEventService {


    public static void printMenu(Order userOrder) {
        List<String> orderedMenu = new ArrayList<>();

        LinkedHashMap<String, Integer> orderItems = userOrder.getOrderItems();
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();

            orderedMenu.add(String.format("%s %d개", menuName, quantity));
        }

        for (String menu : orderedMenu) {
            System.out.println(menu);
        }
    }



    public static int calculateTotalPrice(Order userOrder) {
        return userOrder.getOrderItems()
            .entrySet()
            .stream()
            .mapToInt(entry -> getMenuPrice(entry.getKey()) * entry.getValue())
            .sum();
    }

    public static int getMenuPrice(String menuName) {
        return Arrays.stream(MenuInfo.values())
            .filter(menuInfo -> menuInfo.getMenuName().equals(menuName))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .getPrice();
    }
}
