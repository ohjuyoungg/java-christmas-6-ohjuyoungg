package christmas.controller;

import christmas.domain.order.Order;
import christmas.service.EventPlanner;
import christmas.service.BeforeEventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    public void eventStart() {
        int userVisitDate = inputEventDate();
        Order userOrderMenu = inputOrderMenu();
        InputView.eventBenefitsPreview(userVisitDate);
        OutputView.orderMenu();
        BeforeEventService.printMenu(userOrderMenu);
        int totalPrice = BeforeEventService.calculateTotalPrice(userOrderMenu);
        OutputView.beforeDiscount(totalPrice);
        OutputView.giveawayMenu();
        EventPlanner.giveAway(totalPrice);
        OutputView.benefitDetails();
    }

    private int inputEventDate() {
        InputView.eventStart();
        InputView.expectedVisitDate();
        int eventDate = InputView.userDate();
        return eventDate;
    }

    private Order inputOrderMenu() {
        InputView.orderMenuNumberOfMenus();
        String eventMenu = InputView.userMenu();
        Order userOrder = new Order();
        InputView.tokenizeAndAddToOrder(eventMenu, userOrder);
        return userOrder;
    }
}