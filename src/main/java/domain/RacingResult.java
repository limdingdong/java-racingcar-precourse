package domain;

import domain.dto.RoundResult;

import java.util.List;

public class RacingResult {

    private final List<RoundResult> roundResults;
    private final Participants winners;

    public RacingResult(List<RoundResult> roundResults, Participants winners) {
        this.roundResults = roundResults;
        this.winners = winners;
    }

    public List<RoundResult> roundResults() {
        return roundResults;
    }

    public List<String> getWinnerNames() {
        return winners.names();
    }
}
