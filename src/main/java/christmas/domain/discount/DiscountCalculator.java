package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.CHRISTMAS;
import static christmas.domain.discount.DiscountType.SPECIAL;
import static christmas.domain.discount.DiscountType.WEEKDAY;
import static christmas.domain.discount.DiscountType.WEEKEND;

import christmas.domain.order.Order;
import christmas.view.OutputView;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountCalculator {

    private static final LocalDate endOfDecember = LocalDate.of(2023, 12, 1);
    public static final int MINIMUM_PRICE_FOR_GIVEAWAY = 120_000;

    public static int calculateChristmasDiscount(int eventDate) {
        int totalDiscount = 1000;
        if (eventDate >= 26) {
            return 0;
        }
        for (int day = 1; day < eventDate; day++) {
            totalDiscount += 100;
        }
        return totalDiscount;
    }

    public static int calculateWeekendDiscount(int eventDate, Order userOrder) {
        DayOfWeek dayOfWeek = endOfDecember.plusDays(eventDate - 1).getDayOfWeek();
        int discountPerMainDish = 2023;
        if (eventDate >= 26) {
            return 0;
        }
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return userOrder.calculateMainDishDiscount(discountPerMainDish);
        }
        return 0;
    }

    public static int calculateWeekdayDiscount(int eventDate, Order userOrder) {
        DayOfWeek dayOfWeek = endOfDecember.plusDays(eventDate - 1).getDayOfWeek();
        int discountPerDessert = 2023;
        if (eventDate >= 26) {
            return 0;
        }

        if (!(dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY)) {
            return userOrder.calculateDessertDiscount(discountPerDessert);
        }
        return 0;
    }

    public static int calculateStarDiscount(int eventDate, DayOfWeek dayOfWeek) {
        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);
        int discountAmount = 0;
        if (dayOfWeek == DayOfWeek.SUNDAY || dateUtils.getDayOfMonth() == 25) {
            discountAmount = 1000;
        }
        return discountAmount;
    }

    public static void calculateEventBadge(Discount discount, int beforeDiscountPrice) {
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
