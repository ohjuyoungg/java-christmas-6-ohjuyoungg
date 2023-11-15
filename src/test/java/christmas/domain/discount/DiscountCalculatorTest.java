package christmas.domain.discount;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.order.Order;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    @Test
    void calculateChristmasDiscount_크리스마스_할인_계산_성공() {
        int eventDate = 3;
        int christmasDiscount = DiscountCalculator.calculateChristmasDiscount(eventDate);
        assertThat(christmasDiscount).isEqualTo(1200);
    }

    @Test
    void calculateChristmasDiscount_크리스마스_이후는_할인없음() {
        int eventDate = 26;
        int christmasDiscount = DiscountCalculator.calculateChristmasDiscount(eventDate);
        assertThat(christmasDiscount).isEqualTo(0);
    }

    @Test
    void calculateWeekendDiscount_주말_할인_계산_성공() {
        int eventDate = 2;
        Order userOrder = new Order();
        userOrder.addOrder("해산물파스타", 1);
        userOrder.addOrder("크리스마스파스타", 2);

        int weekendDiscount = DiscountCalculator.calculateWeekendDiscount(eventDate, userOrder);
        assertThat(weekendDiscount).isEqualTo(80954);
    }

    @Test
    void calculateWeekendDiscount_주말이_아니면_할인없음() {
        int eventDate = 3;
        Order userOrder = new Order();
        userOrder.addOrder("해산물파스타", 1);
        userOrder.addOrder("크리스마스파스타", 2);

        int weekendDiscount = DiscountCalculator.calculateWeekendDiscount(eventDate, userOrder);
        assertThat(weekendDiscount).isEqualTo(0);
    }

    @Test
    void calculateWeekendDiscount_이벤트_이후는_할인없음() {
        int eventDate = 26;
        Order userOrder = new Order();
        userOrder.addOrder("해산물파스타", 1);
        userOrder.addOrder("크리스마스파스타", 2);

        int weekendDiscount = DiscountCalculator.calculateWeekendDiscount(eventDate, userOrder);
        assertThat(weekendDiscount).isEqualTo(0);
    }

    @Test
    void calculateWeekdayDiscount_평일_할인_계산_성공() {
        int eventDate = 3;
        Order userOrder = new Order();
        userOrder.addOrder("제로콜라", 2);
        userOrder.addOrder("초코케이크", 3);

        int weekdayDiscount = DiscountCalculator.calculateWeekdayDiscount(eventDate, userOrder);
        assertThat(weekdayDiscount).isEqualTo(6069);
    }

    @Test
    void calculateWeekdayDiscount_평일이_아니면_할인없음() {
        int eventDate = 2;
        Order userOrder = new Order();
        userOrder.addOrder("제로콜라", 2);
        userOrder.addOrder("아이스크림", 3);

        int weekdayDiscount = DiscountCalculator.calculateWeekdayDiscount(eventDate, userOrder);
        assertThat(weekdayDiscount).isEqualTo(0);
    }

    @Test
    void calculateWeekdayDiscount_이벤트_이후는_할인없음() {
        int eventDate = 26;
        Order userOrder = new Order();
        userOrder.addOrder("제로콜라", 2);
        userOrder.addOrder("아이스크림", 3);

        int weekdayDiscount = DiscountCalculator.calculateWeekdayDiscount(eventDate, userOrder);
        assertThat(weekdayDiscount).isEqualTo(0);
    }

    @Test
    void calculateStarDiscount_별_표시날짜_일요일에_적용_성공() {
        int eventDate = 3;
        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;

        int starDiscount = DiscountCalculator.calculateStarDiscount(eventDate, dayOfWeek);
        assertThat(starDiscount).isEqualTo(1000);
    }

    @Test
    void calculateStarDiscount_별_표시날짜_아니면_할인없음() {
        int eventDate = 2;
        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;

        int starDiscount = DiscountCalculator.calculateStarDiscount(eventDate, dayOfWeek);
        assertThat(starDiscount).isEqualTo(0);
    }

    @Test
    void calculateStarDiscount_이벤트_이후는_할인없음() {
        int eventDate = 26;
        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;

        int starDiscount = DiscountCalculator.calculateStarDiscount(eventDate, dayOfWeek);
        assertThat(starDiscount).isEqualTo(0);
    }
}




