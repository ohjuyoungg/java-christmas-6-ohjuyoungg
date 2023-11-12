package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.constants.InputComment;
import christmas.utils.validator.DateValidator;
import christmas.utils.validator.MenuValidator;

public class InputView {

    public static int userDate() {
        while (true) {
            String input = Console.readLine();
            if (DateValidator.validateDateErrors(input)) {
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

    public void EventStart() {
        System.out.println(InputComment.EVENT_PLANNER_STATIONERY_START.getInputComment());
    }

    public void ExpectedVisitDate() {
        System.out.println(InputComment.EXPECTED_DATE_OF_VISIT_TO_RESTAURANT.getInputComment());
    }

    public void orderMenuNumberOfMenus() {
        System.out.println(InputComment.GET_THE_MENU_AND_QUANTITY_TO_ORDER.getInputComment());
    }
}
