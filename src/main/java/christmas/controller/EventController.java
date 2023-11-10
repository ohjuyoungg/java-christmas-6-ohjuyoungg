package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void eventStart() {
        inputView.printEventStart();

    }
}
