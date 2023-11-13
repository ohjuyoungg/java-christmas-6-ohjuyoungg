package christmas.domain;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public class Event {
    private final Date visitDate;
    private final Order order;

    public Event(Date visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public Order getOrder() {
        return order;
    }
}
