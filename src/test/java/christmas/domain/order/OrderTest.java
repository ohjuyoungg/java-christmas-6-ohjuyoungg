package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }
    @Test
    void calculateDessertDiscount_디저트_할인_계산_성공() {
        order.addOrder("초코케이크", 2);
        order.addOrder("아이스크림", 3);
        int discountPerMenu = 2023;

        int dessertDiscount = order.calculateDessertDiscount(discountPerMenu);

        assertThat(dessertDiscount).isEqualTo(10115);
    }

    @Test
    void calculateDessertDiscount_디저트가_없으면_할인_없음() {
        order.addOrder("티본스테이크", 3);

        int discountPerMenu = 2023;
        int dessertDiscount = order.calculateDessertDiscount(discountPerMenu);

        assertThat(dessertDiscount).isEqualTo(0);
    }

    @Test
    void calculateMainDishDiscount_메인_할인_계산_성공() {
        order.addOrder("티본스테이크", 1);
        order.addOrder("바비큐립", 2);
        int discountAmount = 20000;

        int mainDishDiscount = order.calculateMainDishDiscount(discountAmount);

        assertThat(mainDishDiscount).isEqualTo(123000);
    }

    @Test
    void calculateMainDishDiscount_메인이_없으면_할인_없음() {
        int discountAmount = 20000;

        int mainDishDiscount = order.calculateMainDishDiscount(discountAmount);

        assertThat(mainDishDiscount).isEqualTo(0);
    }
}
