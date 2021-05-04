package domain;

import domain.dto.RacingResult;
import domain.dto.RoundResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CarRacingTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 등록 - 참가자명 미입력")
    void registerParticipants_nullOrEmpty(String participantNames) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> CarRacing.registerParticipants(participantNames))
                .withMessageMatching("참가자명을 입력해 주세요.");
    }

    @Test
    @DisplayName("참가자 등록 - 중복된 참가자")
    void registerParticipants_duplicate() {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> CarRacing.registerParticipants("lds,lds,pobi"))
                .withMessageMatching("중복된 이름이 존재합니다. 참가자명을 확인해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("레이싱 실행 - 최소 1라운드 이상 입력")
    void executeRacing_minRoundCount(int roundCount) {
        // given
        CarRacing carRacing = CarRacing.registerParticipants("aaa,bbb,ccc");

        // when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> carRacing.executeRacing(roundCount))
                .withMessageMatching("라운드 횟수는 최소 1 이상 이어야 합니다.");
    }

    @Test
    @DisplayName("레이싱 실행 - 라운드 실행건수")
    void executeRacing_roundCount() {
        // given
        CarRacing carRacing = CarRacing.registerParticipants("aaa,bbb,ccc");

        // when
        RacingResult racingResult = carRacing.executeRacing(5);

        // then
        assertThat(racingResult.getRoundResults().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("레이싱 실행 - 라운드별 점수건수")
    void executeRacing_racingScores() {
        // given
        CarRacing carRacing = CarRacing.registerParticipants("aaa,bbb,ccc");

        // when
        RacingResult racingResult = carRacing.executeRacing(5);

        // then
        for (RoundResult roundResult : racingResult.getRoundResults()) {
            assertThat(roundResult.getRoundScores().size()).isEqualTo(3);
        }
    }
}