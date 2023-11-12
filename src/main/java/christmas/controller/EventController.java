package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void eventStart() {
        int userVisitDate = inputEventDate();
    }

    private int inputEventDate() {
        inputView.EventStart();
        inputView.ExpectedVisitDate();
        int eventDate;
        return eventDate = inputView.userDate();
    }
}
