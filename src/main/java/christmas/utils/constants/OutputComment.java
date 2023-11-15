package christmas.utils.constants;

public enum OutputComment {
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT_DETAILS("<총혜택 금액>"),
    ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>"),
    KRW("원"),
    NOTHING("없음"),
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인: -"),
    WEEKDAY_DISCOUNT("평일 할인: -"),
    WEEKEND_DISCOUNT("주말 할인: -"),
    SPECIAL_DISCOUNT("특별 할인: -"),
    GIVEAWAY_EVENT("증정 이벤트: -25,000원"),
    SANTA("산타"),
    TREE("트리"),
    STAR("별");


    private final String outputComment;

    OutputComment(String outputComment) {
        this.outputComment = outputComment;
    }

    public String getOutputComment() {
        return outputComment;
    }
}
