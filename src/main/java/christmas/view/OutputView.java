package christmas.view;

import christmas.utils.constants.OutputComment;

public class OutputView {

    public static void orderMenu() {
        System.out.println(OutputComment.ORDER_MENU.getOutputComment());
    }

    public static void beforeDiscount(int totalAmount) {
        System.out.println();
        System.out.println(OutputComment.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getOutputComment());
        System.out.printf("%,d원%n", totalAmount);
    }

    public static void giveawayMenu() {
        System.out.println();
        System.out.println(OutputComment.GIVEAWAY_MENU.getOutputComment());
    }

    public static void benefitDetails() {
        System.out.println();
        System.out.println(OutputComment.BENEFIT_DETAILS.getOutputComment());
    }
}


