package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}