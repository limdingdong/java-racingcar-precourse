package domain;

import domain.dto.RacingResult;
import domain.dto.RoundResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CarRacingTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("레이싱 실행 - 최소 1라운드 이상 입력")
    void executeRacing_minRoundCount(int roundCount) {
        // given
        Participants participants = Participants.from("aaa,bbb,ccc");
        CarRacing carRacing = CarRacing.from(participants);

        // when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> carRacing.executeRacing(roundCount))
                .withMessageMatching("라운드 횟수는 최소 1 이상 이어야 합니다.");
    }

    @Test
    @DisplayName("레이싱 실행 - 라운드 실행건수")
    void executeRacing_roundCount() {
        // given
        Participants participants = Participants.from("aaa,bbb,ccc");
        CarRacing carRacing = CarRacing.from(participants);

        // when
        RacingResult racingResult = carRacing.executeRacing(5);

        // then
        assertThat(racingResult.getRoundResults().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("레이싱 실행 - 라운드별 점수건수")
    void executeRacing_racingScores() {
        // given
        Participants participants = Participants.from("aaa,bbb,ccc");
        CarRacing carRacing = CarRacing.from(participants);

        // when
        RacingResult racingResult = carRacing.executeRacing(5);

        // then
        for (RoundResult roundResult : racingResult.getRoundResults()) {
            assertThat(roundResult.getRoundScores().size()).isEqualTo(3);
        }
    }
}