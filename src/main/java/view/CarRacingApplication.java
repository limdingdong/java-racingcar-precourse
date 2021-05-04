package view;

import controller.CarRacingController;

public class CarRacingApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CarRacingController carRacingController = new CarRacingController();
        outputView.printCarRacingResult(carRacingController.executeCarRacing(inputView.inputRequest()));
    }
}
