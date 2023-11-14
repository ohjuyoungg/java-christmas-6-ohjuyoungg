package christmas.domain.discount;


import christmas.domain.order.Order;

public static class DiscountInfo {
    private final String message;
    private final DiscountCalculator calculator;

    public DiscountInfo(String message, DiscountCalculator calculator) {
        this.message = message;
        this.calculator = calculator;
    }

    public Discount apply(int eventDate, Order userOrder) {
        return new Discount(message, calculator.calculate(eventDate, userOrder));
    }
}
