package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.order.Order;
import christmas.utils.constants.InputComment;
import christmas.utils.validator.DateValidator;
import christmas.utils.validator.MenuValidator;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class InputView {

    public static int userDate() {
        while (true) {
            String input = Console.readLine();
            if (DateValidator.validateDateErrors(input)) {
                int day = Integer.parseInt(input);
                return DateValidator.userInput(input);
            }
        }
    }

    public static String userMenu() {
        while (true) {
            String input = Console.readLine();
            if (MenuValidator.validateMenuErrors(input)) {
                return MenuValidator.userInput(input);
            }
        }
    }

    public static void eventStart() {
        System.out.println(InputComment.EVENT_PLANNER_STATIONERY_START.getInputComment());
    }

    public static void expectedVisitDate() {
        System.out.println(InputComment.EXPECTED_DATE_OF_VISIT_TO_RESTAURANT.getInputComment());
    }

    public static void orderMenuNumberOfMenus() {
        System.out.println(InputComment.GET_THE_MENU_AND_QUANTITY_TO_ORDER.getInputComment());
    }

    public static void eventBenefitsPreview(int day) {
        String message = String.format(InputComment.EVENT_BENEFITS_PREVIEW.getInputComment(), day);
        System.out.println(message);
        System.out.println();
    }

    public static void tokenizeAndAddToOrder(String eventMenu, Order userOrder) {
        StringTokenizer tokenizer = new StringTokenizer(eventMenu, ",");
        while (tokenizer.hasMoreTokens()) {
            String menuToken = tokenizer.nextToken().trim();
            List<String> menuInfo = Arrays.asList(menuToken.split("-"));
            String menuName = menuInfo.get(0).trim();
            int quantity = Integer.parseInt(menuInfo.get(1).trim());
            userOrder.addOrder(menuName, quantity);
        }
    }
}