package christmas.view;

import static christmas.domain.benefit.DiscountType.CHRISTMAS;
import static christmas.domain.benefit.DiscountType.SPECIAL;
import static christmas.domain.benefit.DiscountType.WEEKDAY;
import static christmas.domain.benefit.DiscountType.WEEKEND;
import static christmas.service.EventPlanner.MINIMUM_PRICE_FOR_GIVEAWAY;
import static christmas.service.EventPlanner.df;

import christmas.domain.benefit.Discount;
import christmas.utils.constants.OutputComment;
import java.text.DecimalFormat;

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
        if (discount.getAmount(CHRISTMAS) > 0) {System.out.println(OutputComment.CHRISTMAS_D_DAY_DISCOUNT.getOutputComment() + df.format(discount.getAmount(CHRISTMAS)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(WEEKDAY) > 0) {
            System.out.println(OutputComment.WEEKDAY_DISCOUNT.getOutputComment() + df.format(discount.getAmount(WEEKDAY)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(WEEKEND) > 0) {
            System.out.println(OutputComment.WEEKEND_DISCOUNT.getOutputComment() + df.format(discount.getAmount(WEEKEND)) + OutputComment.KRW.getOutputComment());
        }
        if (discount.getAmount(SPECIAL) > 0) {
            System.out.println(OutputComment.SPECIAL_DISCOUNT.getOutputComment() + df.format(discount.getAmount(SPECIAL)) + OutputComment.KRW.getOutputComment());
        }
        giveawayBenefit(discount, beforeDiscountPrice);
    }

    public static void giveAway(int beforeDiscountPrice) {
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println("샴페인 1개");
        } else if (beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }

    public static void calculateTotalBenefits(int beforeDiscountPrice, Discount discount) {
        DecimalFormat df = new DecimalFormat("#,###");
        int benefits = discount.getTotalAmount();
        System.out.println("\n" + OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
        if (benefits <= 0) {
            System.out.println(0 + OutputComment.KRW.getOutputComment());
        }
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            int total = benefits + 25000;
            System.out.println("-" + df.format(total) + OutputComment.KRW.getOutputComment());
        } else if (beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY && benefits > 0) {
            System.out.println("-" + df.format(benefits) + OutputComment.KRW.getOutputComment());
        }
    }

    public static void giveawayBenefit(Discount discount, int beforeDiscountPrice) {
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println(OutputComment.GIVEAWAY_EVENT.getOutputComment());
        }
        if (discount.hasNoDiscount() && beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }

    public static void printAfterDiscountPrice(int beforeDiscountPrice, int benefits) {
        int afterDiscountPrice = beforeDiscountPrice - benefits;
        System.out.println("\n" + OutputComment.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT.getOutputComment());
        System.out.println(df.format(afterDiscountPrice) + OutputComment.KRW.getOutputComment());
    }

    public static void printEventBadge(int totalBenefits) {
        System.out.println("\n" + OutputComment.DECEMBER_EVENT_BADGE.getOutputComment());
        if (totalBenefits >= 20000) {
            System.out.println(OutputComment.SANTA.getOutputComment());
        } else if (totalBenefits >= 10000) {
            System.out.println(OutputComment.TREE.getOutputComment());
        } else if (totalBenefits >= 5000) {
            System.out.println(OutputComment.STAR.getOutputComment());
        } else if (totalBenefits < 5000) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }
}


