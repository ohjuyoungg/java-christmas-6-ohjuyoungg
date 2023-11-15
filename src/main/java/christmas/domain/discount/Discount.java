package christmas.domain.discount;

import static christmas.domain.discount.DiscountCalculator.*;

import christmas.domain.order.Order;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<DiscountType, Integer> discountMap = new HashMap<>();

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
}
