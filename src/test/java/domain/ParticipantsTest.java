package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ParticipantsTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자 등록 - 참가자명 미입력")
    void registerParticipants_nullOrEmpty(String participantNames) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Participants.from(participantNames))
                .withMessageMatching("참가자명을 입력해 주세요.");
    }

    @Test
    @DisplayName("참가자 등록 - 중복된 참가자")
    void registerParticipants_duplicate() {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Participants.from("lds,lds,pobi"))
                .withMessageMatching("중복된 이름이 존재합니다. 참가자명을 확인해주세요.");
    }

}