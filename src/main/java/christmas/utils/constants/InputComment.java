package christmas.utils.constants;

public enum Comment {

    EVENT_PLANNER_STATIONERY_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EXPECTED_DATE_OF_VISIT_TO_RESTAURANT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    GET_THE_MENU_AND_QUANTITY_TO_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFITS_PREVIEW("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<총혜택 금액>"),
    ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>"),
    KRW("원"),
    COUNT("개"),
    NOTHING("없음");

    private final String comment;

    Comment(final String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}

