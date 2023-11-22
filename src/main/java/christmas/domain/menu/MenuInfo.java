package christmas.domain.menu;

public enum MenuInfo {

    // APPETIZER
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    // MAIN
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIBS("바비큐립", 54000),
    SEA_FOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    // DESSERT
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    // DRINKS
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String menuName;
    private final int price;

    MenuInfo(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public static boolean isDrink(String menuName) {
        return isZeroCoke(menuName) || isRedWine(menuName) || isChampagne(menuName);
    }

    public static boolean isDessert(String menuName) {
        return CHOCOLATE_CAKE.menuName.equals(menuName) || ICE_CREAM.menuName.equals(menuName);
    }

    private static boolean isZeroCoke(String menuName) {
        return "제로콜라".equals(menuName);
    }

    private static boolean isRedWine(String menuName) {
        return "레드와인".equals(menuName);
    }

    private static boolean isChampagne(String menuName) {
        return "샴페인".equals(menuName);
    }

}