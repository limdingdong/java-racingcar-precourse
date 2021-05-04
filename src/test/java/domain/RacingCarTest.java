package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarTest {

    @Test
    @DisplayName("경주 자동차 - 이동")
    void car_move() {
        // given
        RacingCar racingCar = new RacingCar();

        // when
        racingCar.drive(() -> true);

        // then
        assertThat(racingCar.position()).isEqualTo(1);
    }

    @Test
    @DisplayName("경주 자동차 - 정지")
    void car_stop() {
        // given
        RacingCar racingCar = new RacingCar();

        // when
        racingCar.drive(() -> false);

        // then
        assertThat(racingCar.position()).isEqualTo(0);
    }

    @Test
    @DisplayName("RacingCar 비교")
    void car_compare() {
        // given
        RacingCar racingCar_move = new RacingCar();
        RacingCar racingCar_stop = new RacingCar();

        // when
        racingCar_move.drive(() -> true);
        racingCar_stop.drive(() -> false);

        // then
        assertThat(racingCar_move).isGreaterThan(racingCar_stop);
    }

    @ParameterizedTest
    @CsvSource(value = {"2:true", "3:true", "4:false"}, delimiter = ':')
    @DisplayName("우승자 판별")
    void car_winner(int winnerScore, boolean expected) {
        // given
        RacingCar racingCar = new RacingCar();

        // when
        racingCar.drive(() -> true);
        racingCar.drive(() -> true);
        racingCar.drive(() -> true);

        // then
        assertThat(racingCar.isWinner(winnerScore)).isEqualTo(expected);
    }
}