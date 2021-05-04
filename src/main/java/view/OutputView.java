package view;

import controller.dto.CarRacingResponse;
import domain.dto.RoundResult;
import domain.dto.RoundScore;

import java.util.List;

public class OutputView {

    private static final String SCORE_LINE = "-";
    private static final String WINNER_DELIMITER = ", ";

    public void printCarRacingResult(CarRacingResponse response) {
        System.out.println(System.lineSeparator() + "실행 결과");
        for (RoundResult roundResult : response.getRoundResults()) {
            printRacingScore(roundResult.getRoundScores());
            System.out.println();
        }
        printWinners(response.getWinnerNames());
    }

    private void printRacingScore(List<RoundScore> roundScores) {
        for (RoundScore roundScore : roundScores) {
            System.out.println(roundScore.getParticipantName() + " : " + showScore(roundScore.getPosition()));
        }
    }

    private String showScore(int score) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < score; i++) {
            line.append(SCORE_LINE);
        }
        return line.toString();
    }

    private void printWinners(List<String> winnerList) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(winnerList.get(0));
//        for (int i = 1; i < winnerList.size(); i++) {
//            stringBuilder.append(WINNER_DELIMITER);
//            stringBuilder.append(winnerList.get(i));
//        }
        System.out.println(String.join(WINNER_DELIMITER, winnerList) + "가 최종 우승했습니다.");
    }
}
