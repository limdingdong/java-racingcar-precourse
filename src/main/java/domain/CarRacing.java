package domain;

import domain.dto.RacingResult;
import domain.dto.RoundResult;
import domain.dto.RoundScore;

import java.util.*;

public class CarRacing {

    private static final int FIRST_INDEX = 0;
    private static final int MIN_ROUND_COUNT = 1;

    private final Map<Participant, RacingCar> racing;

    private CarRacing(Map<Participant, RacingCar> racing) {
        this.racing = racing;
    }

    public static CarRacing from(Participants participants) {
        return new CarRacing(registerParticipants(participants));
    }

    private static Map<Participant, RacingCar> registerParticipants(Participants participants) {
        Map<Participant, RacingCar> racingCarMap = new LinkedHashMap<>();
        for (Participant Participant : participants) {
            racingCarMap.put(Participant, new RacingCar());
        }
        return racingCarMap;
    }

    public RacingCar racingCar(Participant participant) {
        return racing.get(participant);
    }

    public RacingResult executeRacing(int roundCount) {
        validateRoundCount(roundCount);
        List<RoundResult> roundResults = new ArrayList<>();
        for (int i = FIRST_INDEX; i < roundCount; i++) {
            roundResults.add(executeRound());
        }
        return new RacingResult(roundResults, chooseWinners().names());
    }

    private void validateRoundCount(int roundCount) {
        if (roundCount < MIN_ROUND_COUNT) {
            throw new IllegalArgumentException("라운드 횟수는 최소 " + MIN_ROUND_COUNT + " 이상 이어야 합니다.");
        }
    }

    private RoundResult executeRound() {
        driveAll(new RandomDriveEngine());
        return new RoundResult(assembleRoundResult());
    }

    private List<RoundScore> assembleRoundResult() {
        List<RoundScore> roundScores = new ArrayList<>();
        for (Participant participant : racing.keySet()) {
            roundScores.add(new RoundScore(participant.name(), racingCar(participant).position()));
        }
        return roundScores;
    }

    private void driveAll(DriveStrategy engine) {
        for (Participant participant : racing.keySet()) {
            racingCar(participant).drive(engine);
        }
    }

    private Participants chooseWinners() {
        int winnerScore = calculateWinnerScore();
        List<Participant> winners = new ArrayList<>();
        for (Participant participant : racing.keySet()) {
            chooseWinner(winners, participant, winnerScore);
        }
        return new Participants(winners);
    }

    private int calculateWinnerScore() {
        List<RacingCar> racingCars = new ArrayList<>(racing.values());
        racingCars.sort(Comparator.reverseOrder());
        RacingCar firstCar = racingCars.get(FIRST_INDEX);
        return firstCar.position();
    }

    private void chooseWinner(List<Participant> winners, Participant participant, int winnerScore) {
        if (racingCar(participant).isWinner(winnerScore)) {
            winners.add(participant);
        }
    }
}
