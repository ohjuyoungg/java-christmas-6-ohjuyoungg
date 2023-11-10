package christmas.utils.constants;

public enum InputComment {

    EVENT_PLANNER_STATIONERY_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EXPECTED_DATE_OF_VISIT_TO_RESTAURANT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    GET_THE_MENU_AND_QUANTITY_TO_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFITS_PREVIEW("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    private final String inputComment;

    InputComment(final String inputComment) {
        this.inputComment = inputComment;
    }

    public String getInputComment() {
        return inputComment;
    }
}

