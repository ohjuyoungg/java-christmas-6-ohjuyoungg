package christmas.domain.service;

import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountCalculator;
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
        DiscountCalculator.calculateEventBadge(discount, beforeDiscountPrice);
    }
}