package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ParticipantTest {

    @Test
    @DisplayName("참가자 생성")
    void create() {
        // given when
        Participant participant = Participant.from("pobi");

        // then
        assertThat(participant.name()).isEqualTo("pobi");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 생성 - 이름 미입력")
    void create_nullOrEmpty(String name) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Participant.from(name))
                .withMessageMatching("참가자 이름을 입력해 주세요.");
    }

    @Test
    @DisplayName("참가자 생성 - 이름 최대길이 초과")
    void create_nameIsGreaterThanMaxLength() {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Participant.from("ABCDEF"))
                .withMessageMatching("참가자 이름은 5 자 이하여야 합니다.");
    }
}