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

    private static void validateOrder(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        List<String> validationErrors = new ArrayList<>();
        boolean containsNonDrink = false;
        validateOrderSplit(tokenizer, validationErrors, containsNonDrink);
    }

    private static void validateOrderSplit(StringTokenizer tokenizer, List<String> validationErrors,
        boolean containsNonDrink) {
        int totalQuantity = 0;
        while (tokenizer.hasMoreTokens()) {
            String orderItem = tokenizer.nextToken().trim();
            validateOrderItem(orderItem);
            String menuName = orderItem.split("-")[0].trim();
            if (!MenuInfo.isDrink(menuName)) {
                containsNonDrink = true;
            }
            int quantity = Integer.parseInt(orderItem.split("-")[1].trim());
            totalQuantity += quantity;
            validateOrderLimit(totalQuantity);
            validateDrinkAndEmpty(containsNonDrink, validationErrors);
        }
    }

    private static void validateDrinkAndEmpty(boolean containsNonDrink, List<String> validationErrors) {
        if (!containsNonDrink) {
            validationErrors.add(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(System.lineSeparator(), validationErrors));
        }
    }

    private static void validateOrderItem(String orderItem) {
        List<String> parts = Arrays.asList(orderItem.split("-"));
        if (parts.size() != 2 || parts.get(0).trim().isEmpty() || parts.get(1).trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
        String menuName = parts.get(0).trim();
        int quantity;
        try {
            quantity = Integer.parseInt(parts.get(1).trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
        validateNotOnMenu(menuName);
        validateQuantity(quantity);
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