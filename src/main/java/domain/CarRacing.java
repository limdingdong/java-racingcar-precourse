package domain;

import common.utils.StringUtils;
import domain.dto.RacingResult;
import domain.dto.RoundResult;
import domain.dto.RoundScore;

import java.util.*;

public class CarRacing {

    private static final String NAME_DELIMITER = ",";
    private static final int FIRST_INDEX = 0;
    private static final int MIN_ROUND_COUNT = 1;

    private final Map<Participant, RacingCar> racing;

    private CarRacing(Map<Participant, RacingCar> racing) {
        this.racing = racing;
    }

    public static CarRacing registerParticipants(String participantNames) {
        validateNames(participantNames);
        String[] participantNamesArray = participantNames.split(NAME_DELIMITER);
        return new CarRacing(registerParticipants(participantNamesArray));
    }

    private static void validateNames(String participantNames) {
        if (StringUtils.isBlank(participantNames)) {
            throw new IllegalArgumentException("참가자명을 입력해 주세요.");
        }
    }

    private static Map<Participant, RacingCar> registerParticipants(String[] participantNamesArray) {
        Map<Participant, RacingCar> racingCarMap = new LinkedHashMap<>();
        for (String participantName : participantNamesArray) {
            racingCarMap.put(Participant.from(participantName), new RacingCar());
        }
        validateDuplicate(participantNamesArray, racingCarMap);
        return racingCarMap;
    }

    private static void validateDuplicate(String[] participantNamesArray, Map<Participant, RacingCar> racingCarMap) {
        if (participantNamesArray.length > racingCarMap.size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다. 참가자명을 확인해주세요.");
        }
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
