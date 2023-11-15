package christmas.service;

import static christmas.domain.benefit.DiscountType.CHRISTMAS;
import static christmas.domain.benefit.DiscountType.SPECIAL;
import static christmas.domain.benefit.DiscountType.WEEKDAY;
import static christmas.domain.benefit.DiscountType.WEEKEND;

import christmas.domain.benefit.Discount;
import christmas.domain.order.Order;
import christmas.view.OutputView;
import java.text.DecimalFormat;

public class EventPlanner {

    public static final int MINIMUM_PRICE_FOR_GIVEAWAY = 120_000;
    public static DecimalFormat df = new DecimalFormat("#,###");

    public static void calculateBenefits(int eventDate, Order userOrder, int beforeDiscountPrice) {
        Discount discount = new Discount(eventDate, userOrder);
        OutputView.benefits(discount, beforeDiscountPrice);
        OutputView.calculateTotalBenefits(beforeDiscountPrice, discount);
        int benefits = discount.getTotalAmount();
        OutputView.printAfterDiscountPrice(beforeDiscountPrice, benefits);
        calculateEventBadge(discount, beforeDiscountPrice);
    }

    private static void calculateEventBadge(Discount discount, int beforeDiscountPrice) {
        int totalBenefits = 0;
        int benefits = discount.getAmount(CHRISTMAS) + discount.getAmount(WEEKDAY) + discount.getAmount(WEEKEND)
            + discount.getAmount(SPECIAL);
        if (beforeDiscountPrice >= MINIMUM_PRICE_FOR_GIVEAWAY) {
            totalBenefits = benefits + 25000;
        } else if (beforeDiscountPrice < MINIMUM_PRICE_FOR_GIVEAWAY) {
            totalBenefits = benefits;
        }
        OutputView.printEventBadge(totalBenefits);
    }
}