package christmas.service;

import christmas.domain.order.Order;
import christmas.utils.constants.OutputComment;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventPlanner {

    private static LocalDate dateUtils = LocalDate.of(2023, 12, 1);
    private static DecimalFormat df = new DecimalFormat("#,###");
    private static final int ZERO = 0;
    private static final int TWELVE_THOUSAND = 120000;
    private static final int THOUSAND = 1000;

    public static void calculateBenefits(int eventDate, Order userOrder, int beforeDiscountPrice) {

        final int christmasDiscount = calculateChristmasDiscount(eventDate);
        final int weekdayDiscount = calculateWeekdayDiscount(eventDate, userOrder);
        final int weekendDiscount = calculateWeekendDiscount(eventDate, userOrder);

        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);
        final int starDiscount = calculateStarDiscount(eventDate, dateUtils.getDayOfWeek());

        if (christmasDiscount > ZERO) {
            System.out.println("크리스마스 디데이 할인: -" + df.format(christmasDiscount) + OutputComment.KRW.getOutputComment());
        }
        if (weekdayDiscount > ZERO) {
            System.out.println("평일 할인: -" + df.format(weekdayDiscount) + OutputComment.KRW.getOutputComment());
        }
        if (weekendDiscount > ZERO) {
            System.out.println("주말 할인: -" + df.format(weekendDiscount) + OutputComment.KRW.getOutputComment());
        }
        if (starDiscount > ZERO) {
            System.out.println("특별 할인: -" + df.format(starDiscount) + OutputComment.KRW.getOutputComment());
        }
        if (beforeDiscountPrice >= TWELVE_THOUSAND) {
            System.out.println("증정 이벤트: -25,000원");
        }
        if (christmasDiscount == ZERO && weekdayDiscount == ZERO && weekendDiscount == ZERO && starDiscount == ZERO
            && beforeDiscountPrice < TWELVE_THOUSAND) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
        calculateTotalBenefits(christmasDiscount, weekdayDiscount, weekendDiscount, starDiscount, beforeDiscountPrice);
        int benefits = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount;
        printAfterDiscountPrice(beforeDiscountPrice, benefits);
        int totalBenefits = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount + 25000;
        calculateEventBadge(totalBenefits);
    }


    private static void calculateTotalBenefits(int christmasDiscount, int weekdayDiscount,
        int weekendDiscount, int starDiscount, int beforeDiscountPrice) {
        DecimalFormat df = new DecimalFormat("#,###");
        int benefits = christmasDiscount + weekdayDiscount + weekendDiscount + starDiscount;

        if (benefits <= ZERO) {
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println(ZERO + OutputComment.KRW.getOutputComment());
        }
        if (beforeDiscountPrice >= TWELVE_THOUSAND) {
            int total = benefits + 25000;
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println("-" + df.format(total) + OutputComment.KRW.getOutputComment());
        } else if (beforeDiscountPrice < 120000 && benefits > ZERO) {
            System.out.println();
            System.out.println(OutputComment.TOTAL_BENEFIT_DETAILS.getOutputComment());
            System.out.println("-" + df.format(benefits) + OutputComment.KRW.getOutputComment());
        }
    }

    public static void giveAway(int beforeDiscountPrice) {
        if (beforeDiscountPrice >= TWELVE_THOUSAND) {
            System.out.println("샴페인 1개");
        } else if (beforeDiscountPrice < TWELVE_THOUSAND) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }

    private static void printAfterDiscountPrice(int beforeDiscountPrice, int benefits) {
        int afterDiscountPrice = beforeDiscountPrice - benefits;
        System.out.println();
        System.out.println(OutputComment.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT.getOutputComment());
        System.out.println(df.format(afterDiscountPrice) + OutputComment.KRW.getOutputComment());
    }


    private static int calculateChristmasDiscount(int eventDate) {
        int totalDiscount = THOUSAND;
        if (eventDate >= 26) {
            return ZERO;
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
            return ZERO;
        }
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return userOrder.calculateMainDishDiscount(discountPerMainDish);
        }
        return ZERO;
    }

    private static int calculateWeekdayDiscount(int eventDate, Order userOrder) {
        DayOfWeek dayOfWeek = dateUtils.plusDays(eventDate - 1).getDayOfWeek();
        int discountPerDessert = 2023;

        if (eventDate >= 26) {
            return ZERO;
        }

        if (!(dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY)) {
            return userOrder.calculateDessertDiscount(discountPerDessert);
        }
        return ZERO;
    }

    private static int calculateStarDiscount(int eventDate, DayOfWeek dayOfWeek) {
        LocalDate dateUtils = LocalDate.of(2023, 12, eventDate);

        int discountAmount = ZERO;
        if (dayOfWeek == DayOfWeek.SUNDAY || dateUtils.getDayOfMonth() == 25) {
            discountAmount = THOUSAND;
        }
        return discountAmount;
    }

    private static void calculateEventBadge(int totalBenefits) {
        System.out.println();
        System.out.println(OutputComment.DECEMBER_EVENT_BADGE.getOutputComment());
        if (totalBenefits >= 20000) {
            System.out.println("산타");
        } else if (totalBenefits >= 10000) {
            System.out.println("트리");
        } else if (totalBenefits >= 5000) {
            System.out.println("별");
        } else if (totalBenefits < 5000) {
            System.out.println(OutputComment.NOTHING.getOutputComment());
        }
    }
}


