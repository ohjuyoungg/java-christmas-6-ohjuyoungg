package christmas.utils.validator;

import christmas.domain.menu.MenuInfo;
import christmas.utils.constants.ExceptionMessage;
import christmas.view.InputView;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MenuValidator {

    private static final List<MenuInfo> MENU_LIST = Arrays.asList(
        MenuInfo.BUTTON_MUSHROOM_SOUP,
        MenuInfo.TAPAS,
        MenuInfo.CAESAR_SALAD,
        MenuInfo.T_BONE_STEAK,
        MenuInfo.BARBECUE_RIBS,
        MenuInfo.SEA_FOOD_PASTA,
        MenuInfo.CHRISTMAS_PASTA,
        MenuInfo.CHOCOLATE_CAKE,
        MenuInfo.ICE_CREAM,
        MenuInfo.ZERO_COKE,
        MenuInfo.RED_WINE,
        MenuInfo.CHAMPAGNE
    );

    private static void validateMenu(String input) {
        isBlank(input);
        validateOrder(input);
    }

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

    private static void isBlank(String menuValue) {
        if (menuValue == null || menuValue.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    public static void validateOrder(String order) {
        StringTokenizer tokenizer = new StringTokenizer(order, ", ");
        while (tokenizer.hasMoreTokens()) {
            validateOrderItem(tokenizer.nextToken());
        }
    }

    private static void validateOrderItem(String orderItem) {
        StringTokenizer tokenizer = new StringTokenizer(orderItem, "-");
        if (tokenizer.countTokens() != 2) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
        String menuName = tokenizer.nextToken().trim();
        int quantity;
        try {
            quantity = Integer.parseInt(tokenizer.nextToken().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
        validateNotOnMenu(menuName);
        validateQuantity(quantity);
        validateOnlyDrink(menuName, quantity);
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

    private static void validateOnlyDrink(String menuName, int quantity) {
        if (MenuInfo.isDrink(menuName) && quantity > 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static void validateOrderLimit(int quantity) {
        if (quantity < 1 || quantity > 20) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getExceptionMessage());
        }
    }

    private static boolean containsMenu(String menuName) {
        return MENU_LIST.stream().anyMatch(menu -> menu.getMenuName().equals(menuName));
    }
}

