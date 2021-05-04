package controller;

import controller.dto.CarRacingRequest;
import controller.dto.CarRacingResponse;
import domain.dto.RoundResult;
import domain.dto.RoundScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarRacingControllerTest {

    private final CarRacingController controller = new CarRacingController();

    @Test
    @DisplayName("레이싱 실행 - 라운드 실행건수")
    void executeRacing_roundCount() {
        // given when
        CarRacingRequest carRacingRequest = new CarRacingRequest("aaa,bbb,ccc", 5);
        CarRacingResponse carRacingResponse = controller.executeCarRacing(carRacingRequest);

        // then
        assertThat(carRacingResponse.getRoundResults().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("레이싱 실행 - 라운드별 점수건수")
    void executeRacing_racingScores() {
        // given when
        CarRacingRequest carRacingRequest = new CarRacingRequest("aaa,bbb,ccc", 5);
        CarRacingResponse carRacingResponse = controller.executeCarRacing(carRacingRequest);

        // then
        for (RoundResult roundResult : carRacingResponse.getRoundResults()) {
            assertThat(roundResult.getRoundScores().size()).isEqualTo(3);
        }
    }

    @Test
    @DisplayName("우승자 판별")
    void winners() {
        // given
        CarRacingRequest carRacingRequest = new CarRacingRequest("aaa,bbb,ccc", 5);
        CarRacingResponse carRacingResponse = controller.executeCarRacing(carRacingRequest);
        List<String> winnerNames = carRacingResponse.getWinnerNames();

        // when
        RoundResult finalRound = null;
        for (RoundResult roundResult : carRacingResponse.getRoundResults()) {
            finalRound = roundResult;
        }

        // then
        assertThat(finalRound).isNotNull();

        int winnerScore = 0;
        for (RoundScore roundScore : finalRound.getRoundScores()) {
            winnerScore = Integer.max(winnerScore, roundScore.getPosition());
        }

        for (String winnerName : winnerNames) {
            for (RoundScore roundScore : finalRound.getRoundScores()) {
                if (roundScore.getParticipantName().equals(winnerName)) {
                    assertThat(roundScore.getPosition()).isEqualTo(winnerScore);
                }
            }
        }
    }
}