package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.constants.InputComment;
import christmas.utils.validator.DateValidator;

public class InputView {

    public void EventStart() {
        System.out.println(InputComment.EVENT_PLANNER_STATIONERY_START.getInputComment());
    }

    public void ExpectedVisitDate() {
        System.out.println(InputComment.EXPECTED_DATE_OF_VISIT_TO_RESTAURANT.getInputComment());
    }

    public static int userDate() {
        while (true) {
            String input = Console.readLine();
            if (DateValidator.validateDateErrors(input)) {
                return DateValidator.parseInput(input);
            }
        }
    }
}
