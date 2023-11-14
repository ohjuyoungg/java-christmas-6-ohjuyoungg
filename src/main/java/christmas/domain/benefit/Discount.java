package christmas.domain.benefit;

import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<DiscountType, Integer> discountMap = new HashMap<>();

    private final LocalDate endOfDecember = LocalDate.of(2023, 12, 1);
    private final LocalDate eventDate;

    public Discount(int eventDateInt, Order userOrder) {
        this.eventDate = LocalDate.of(2023, 12, eventDateInt);
        this.discountMap.put(DiscountType.CHRISTMAS, calculateChristmasDiscount(eventDateInt));
        this.discountMap.put(DiscountType.WEEKDAY, calculateWeekdayDiscount(eventDateInt, userOrder));
        this.discountMap.put(DiscountType.WEEKEND, calculateWeekendDiscount(eventDateInt, userOrder));
        this.discountMap.put(DiscountType.SPECIAL, calculateStarDiscount(eventDateInt, eventDate.getDayOfWeek()));
    }

    public boolean hasNoDiscount() {
        return getTotalAmount() == 0;
    }

    public int getTotalAmount() {
        return discountMap.values().stream().reduce(0, Integer::sum);
    }

    public int getAmount(DiscountType type) {
        return discountMap.get(type);
    }

    private int calculateChristmasDiscount(int eventDate) {
        int totalDiscount = 1000;
        if (eventDate >= 26) {
            return 0;
        }
        for (int day = 1; day < eventDate; day++) {
            totalDiscount += 100;
        }
        return totalDiscount;
    }

    private int calculateWeekendDiscount(int eventDate, Order userOrder) {
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

    private int calculateWeekdayDiscount(int eventDate, Order userOrder) {
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

    private int calculateStarDiscount(int eventDate, DayOfWeek dayOfWeek) {
        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);

        int discountAmount = 0;
        if (dayOfWeek == DayOfWeek.SUNDAY || dateUtils.getDayOfMonth() == 25) {
            discountAmount = 1000;
        }
        return discountAmount;
    }
}
