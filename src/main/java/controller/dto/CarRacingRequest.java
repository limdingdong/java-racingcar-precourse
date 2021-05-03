package controller.dto;

public class CarRacingRequest {

    private final String racingCarNames;
    private final int roundCount;

    public CarRacingRequest(String racingCarNames, int roundCount) {
        this.racingCarNames = racingCarNames;
        this.roundCount = roundCount;
    }

    public String getRacingCarNames() {
        return racingCarNames;
    }

    public int getRoundCount() {
        return roundCount;
    }
}
