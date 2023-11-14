package christmas.view;

import static christmas.domain.benefit.DiscountType.*;
import static christmas.service.EventPlanner.MINIMUM_PRICE_FOR_GIVEAWAY;
import static christmas.service.EventPlanner.df;

import christmas.domain.benefit.Discount;
import christmas.utils.constants.OutputComment;

public class OutputView {

    public static void orderMenu() {
        System.out.println(OutputComment.ORDER_MENU.getOutputComment());
    }

    public static void beforeDiscount(int totalAmount) {
        System.out.println("\n" + OutputComment.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getOutputComment());
        System.out.printf("%,d원%n", totalAmount);
    }

    public static void giveawayMenu() {
        System.out.println("\n" + OutputComment.GIVEAWAY_MENU.getOutputComment());
    }

    public static void benefitDetails() {
        System.out.println("\n" + OutputComment.BENEFIT_DETAILS.getOutputComment());
    }

    public static void benefits(Discount discount, int beforeDiscountPrice) {
        if (discount.getAmount(CHRISTMAS) > 0) {
            System.out.println("크리스마스 디데이 할인: -" + df.format(discount.getAmount(CHRISTMAS)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(WEEKDAY) > 0) {
            System.out.println("평일 할인: -" + df.format(discount.getAmount(WEEKDAY)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(WEEKEND) > 0) {
            System.out.println("주말 할인: -" + df.format(discount.getAmount(WEEKEND)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(SPECIAL) > 0) {
            System.out.println("특별 할인: -" + df.format(discount.getAmount(SPECIAL)) + OutputComment.KRW.getOutputComment());
        }
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println("증정 이벤트: -25,000원");
        }
        if (discount.hasNoDiscount() && beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }
}


