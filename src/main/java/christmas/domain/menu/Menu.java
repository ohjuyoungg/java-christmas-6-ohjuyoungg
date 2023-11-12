package christmas.domain.menu;

import christmas.domain.price.Price;

public class Menu {
    private final MenuInfo info;
    private final Price price;

    public Menu(MenuInfo info, Price price) {
        this.info = info;
        this.price = price;
    }

    public MenuInfo getInfo() {
        return info;
    }

    public Price getPrice() {
        return price;
    }
}
