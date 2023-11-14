package christmas.service;

import christmas.domain.order.Order;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventPlanner {

    private static LocalDate dateUtils = LocalDate.of(2023, 12, 1);
    private static DecimalFormat df = new DecimalFormat("#,###");

    public static void calculateBenefits(int eventDate, Order userOrder, int beforeDiscountPrice) {

        final int christmasDiscount = calculateChristmasDiscount(eventDate);
        final int weekdayDiscount = calculateWeekdayDiscount(eventDate, userOrder);
        final int weekendDiscount = calculateWeekendDiscount(eventDate, userOrder);

        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);
        final int starDiscount = calculateStarDiscount(eventDate, dateUtils.getDayOfWeek());

        if (christmasDiscount > 0) {
            System.out.println("크리스마스 디데이 할인: -" + df.format(christmasDiscount) + "원");
        }
        if (weekdayDiscount > 0) {
            System.out.println("평일 할인: -" + df.format(weekdayDiscount) + "원");
        }
        if (weekendDiscount > 0) {
            System.out.println("주말 할인: -" + df.format(weekendDiscount) + "원");
        }
        if (starDiscount > 0) {
            System.out.println("특별 할인: -" + df.format(starDiscount) + "원");
        }
        if (beforeDiscountPrice >= 120000) {
            System.out.println("증정 이벤트: -25,000원");
        }
        if (christmasDiscount == 0 && weekdayDiscount == 0 && weekendDiscount == 0 && starDiscount == 0
            && beforeDiscountPrice < 120000) {
            System.out.println("없음");
        }
        calculateTotalBenefits(christmasDiscount, weekdayDiscount, weekendDiscount, starDiscount, beforeDiscountPrice);
        int benefits = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount;
        printAfterDiscountPrice(beforeDiscountPrice, benefits);
        int benefit = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount + 250000;
        calculateEventBadge(benefits);
    }


    private static void calculateTotalBenefits(int christmasDiscount, int weekdayDiscount,
        int weekendDiscount, int starDiscount, int beforeDiscountPrice) {
        DecimalFormat df = new DecimalFormat("#,###");
        int benefits = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount;

        if (benefits <= 0) {
            System.out.println();
            System.out.println("<총혜택 금액>");
            System.out.println(0 + "원");
        }
        if (beforeDiscountPrice >= 120000) {
            int total = benefits + 25000;
            System.out.println();
            System.out.println("<총혜택 금액>");
            System.out.println("-" + df.format(total) + "원");
        } else if (beforeDiscountPrice < 120000 && benefits > 0) {
            System.out.println();
            System.out.println("<총혜택 금액>");
            System.out.println("-" + df.format(benefits) + "원");
        }
    }

    public static void giveAway(int beforeDiscountPrice) {
        if (beforeDiscountPrice >= 120000) {
            System.out.println("샴페인 1개");
        } else if (beforeDiscountPrice < 120000) {
            System.out.println("없음");
        }
    }

    private static void printAfterDiscountPrice(int beforeDiscountPrice, int benefits) {
        int afterDiscountPrice = beforeDiscountPrice - benefits;
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(df.format(afterDiscountPrice) + "원");
    }


    private static int calculateChristmasDiscount(int eventDate) {
        int totalDiscount = 1000;
        if (eventDate >= 26) {
            return 0;
        }
        for (int day = 1; day < eventDate; day++) {
            totalDiscount += 100;
        }
        return totalDiscount;
    }

    private static int calculateWeekendDiscount(int eventDate, Order userOrder) {
        DayOfWeek dayOfWeek = dateUtils.plusDays(eventDate - 1).getDayOfWeek();
        int discountPerMainDish = 2023;

        if (eventDate >= 26) {
            return 0;
        }

        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return userOrder.calculateMainDishDiscount(discountPerMainDish);
        }
        return 0;
    }

    private static int calculateWeekdayDiscount(int eventDate, Order userOrder) {
        DayOfWeek dayOfWeek = dateUtils.plusDays(eventDate - 1).getDayOfWeek();
        int discountPerDessert = 2023;

        if (eventDate >= 26) {
            return 0;
        }

        if (!(dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY)) {
            return userOrder.calculateDessertDiscount(discountPerDessert);
        }
        return 0;
    }

    private static int calculateStarDiscount(int eventDate, DayOfWeek dayOfWeek) {
        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);

        int discountAmount = 0;
        if (dayOfWeek == DayOfWeek.SUNDAY || dateUtils.getDayOfMonth() == 25) {
            discountAmount = 1000;
        }
        return discountAmount;
    }

    private static void calculateEventBadge(int totalBenefits) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        if (totalBenefits >= 20000) {
            System.out.println("산타");
        } else if (totalBenefits >= 10000) {
            System.out.println("트리");
        } else if (totalBenefits >= 5000) {
            System.out.println("별");
        } else if (totalBenefits < 5000) {
            System.out.println("없음");
        }
    }
}
