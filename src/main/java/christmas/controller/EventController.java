package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.service.BeforeEventService;
import christmas.domain.service.EventPlanner;
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
        OutputView.giveAway(totalPrice);
        OutputView.benefitDetails();
        EventPlanner.calculateBenefits(userVisitDate, userOrderMenu, totalPrice);
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