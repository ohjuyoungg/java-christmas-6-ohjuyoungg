package christmas.service;

import christmas.domain.benefit.Discount;
import christmas.domain.order.Order;
import christmas.utils.constants.OutputComment;
import christmas.view.OutputView;
import java.text.DecimalFormat;

public class EventPlanner {

    public static final int MINIMUM_PRICE_FOR_GIVEAWAY = 120_000;
    public static DecimalFormat df = new DecimalFormat("#,###");

    public static void calculateBenefits(int eventDate, Order userOrder, int beforeDiscountPrice) {
        Discount discount = new Discount(eventDate, userOrder);
        OutputView.benefits(discount, beforeDiscountPrice);

        calculateTotalBenefits(beforeDiscountPrice, discount);
        int benefits = discount.getTotalAmount();
        printAfterDiscountPrice(beforeDiscountPrice, benefits);
        int totalBenefits = benefits + 25000;
        calculateEventBadge(totalBenefits);
    }


    private static void calculateTotalBenefits(int beforeDiscountPrice, Discount discount) {
        DecimalFormat df = new DecimalFormat("#,###");
        int benefits = discount.getTotalAmount();

        if (benefits <= 0) {
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println(0 + OutputComment.KRW.getOutputComment());
        }
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            int total = benefits + 25000;
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println("-" + df.format(total) + OutputComment.KRW.getOutputComment());
        } else if (beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY && benefits > 0) {
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println("-" + df.format(benefits) + OutputComment.KRW.getOutputComment());
        }
    }

    public static void giveAway(int beforeDiscountPrice) {
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println("샴페인 1개");
        } else if (beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }

    private static void printAfterDiscountPrice(int beforeDiscountPrice, int benefits) {
        int afterDiscountPrice = beforeDiscountPrice - benefits;
        System.out.println();
        System.out.println(OutputComment.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT.getOutputComment());
        System.out.println(df.format(afterDiscountPrice) + OutputComment.KRW.getOutputComment());
    }

    private static void calculateEventBadge(int totalBenefits) {
        System.out.println();
        System.out.println(OutputComment.DECEMBER_EVENT_BADGE.getOutputComment());
        if (totalBenefits >= 20000) {
            System.out.println("산타");
        } else if (totalBenefits >= 10000) {
            System.out.println("트리");
        } else if (totalBenefits >= 5000) {
            System.out.println("별");
        } else if (totalBenefits < 5000) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }
}


