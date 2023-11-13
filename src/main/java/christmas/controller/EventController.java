package christmas.controller;

import christmas.domain.Event;
import christmas.domain.date.Date;
import christmas.domain.order.Order;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final EventService eventService = new EventService();


    public void eventStart() {
        Date userVisitDate = inputEventDate();
        Order userOrderMenu = inputOrderMenu();
        InputView.eventBenefitsPreview(userVisitDate.getDate());
        Event event = new Event(userVisitDate, userOrderMenu);
        outputView.orderMenu();
        eventService.printMenu(userOrderMenu);
    }

    private Date inputEventDate() {
        inputView.EventStart();
        inputView.ExpectedVisitDate();
        int eventDate = inputView.userDate();
        return new Date(eventDate);
    }

    private Order inputOrderMenu() {
        inputView.orderMenuNumberOfMenus();
        String eventMenu = inputView.userMenu();
        Order userOrder = new Order();
        InputView.tokenizeAndAddToOrder(eventMenu, userOrder);
        return userOrder;
    }
}
