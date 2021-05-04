package controller.dto;

import domain.dto.RoundResult;

import java.util.List;

public class CarRacingResponse {

    private final List<RoundResult> roundResults;
    private final List<String> winnerNames;

    public CarRacingResponse(List<RoundResult> roundResults, List<String> winnerNames) {
        this.roundResults = roundResults;
        this.winnerNames = winnerNames;
    }

    public List<RoundResult> getRoundResults() {
        return roundResults;
    }

    public List<String> getWinnerNames() {
        return winnerNames;
    }
}
