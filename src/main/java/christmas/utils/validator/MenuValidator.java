package christmas.utils.validator;

import christmas.domain.menu.MenuInfo;
import christmas.utils.constants.ExceptionMessage;
import christmas.view.InputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MenuValidator {

    private static final List<MenuInfo> MENU_LIST = Arrays.asList(MenuInfo.values());

    public static String userInput(String input) {
        try {
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
            return InputView.userMenu();
        }
    }

    public static boolean validateMenuErrors(String input) {
        try {
            validateMenu(input);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
            return false;
        }
    }

    private static void validateMenu(String input) {
        isBlank(input);
        validateOrder(input);
    }

    private static void isBlank(String menuValue) {
        if (menuValue == null || menuValue.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static void validateOrder(String order) {
        isBlank(order);

        StringTokenizer tokenizer = new StringTokenizer(order, ",");
        List<String> validationErrors = new ArrayList<>();
        int totalQuantity = 0;
        boolean containsNonDrink = false;

        while (tokenizer.hasMoreTokens()) {
            String orderItem = tokenizer.nextToken().trim();
            try {
                validateOrderItem(orderItem);
                String menuName = orderItem.split("-")[0].trim();
                if (!MenuInfo.isDrink(menuName)) {
                    containsNonDrink = true;
                }

                int quantity = Integer.parseInt(orderItem.split("-")[1].trim());
                totalQuantity += quantity;
                validateOrderLimit(totalQuantity);
            } catch (IllegalArgumentException e) {
                validationErrors.add(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
            }
        }

        if (!containsNonDrink) {
            validationErrors.add(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(
                String.join(System.lineSeparator(), validationErrors));
        }
    }

    private static void validateOrderItem(String orderItem) {
        String[] parts = orderItem.split("-");

        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }

        String menuName = parts[0].trim();
        int quantity;

        try {
            quantity = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }

        validateNotOnMenu(menuName);
        validateQuantity(quantity);
        validateOrderLimit(quantity);
    }

    private static void validateNotOnMenu(String menuName) {
        if (!containsMenu(menuName)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static void validateOrderLimit(int totalQuantity) {
        if (totalQuantity > 20) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static boolean containsMenu(String menuName) {
        return MENU_LIST.stream().anyMatch(menu -> menu.getMenuName().equals(menuName));
    }
}
