package controller;

import controller.dto.CarRacingRequest;
import controller.dto.CarRacingResponse;
import domain.RacingResult;
import service.CarRacingService;

public class CarRacingController {

    private final CarRacingService service;

    public CarRacingController() {
        service = new CarRacingService();
    }

    public CarRacingResponse executeCarRacing(CarRacingRequest carRacingRequest) {
        RacingResult racingResult = service.executeCarRacing(carRacingRequest.getRacingCarNames(), carRacingRequest.getRoundCount());
        return new CarRacingResponse(racingResult.roundResults(), racingResult.getWinnerNames());
    }
}
