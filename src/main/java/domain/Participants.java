package domain;

import common.utils.StringUtils;

import java.util.*;

public class Participants implements Iterable<Participant> {

    private static final String NAME_DELIMITER = ",";

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(String participantNames) {
        validateNames(participantNames);
        return new Participants(createParticipants(participantNames.split(NAME_DELIMITER)));
    }

    private static List<Participant> createParticipants(String[] participantNames) {
        validateDuplicate(participantNames);
        List<Participant> participants = new ArrayList<>();
        for (String participantName : participantNames) {
            participants.add(Participant.from(participantName));
        }
        return participants;
    }

    private static void validateDuplicate(String[] participantNames) {
        Set<String> nameSet = new HashSet<>(Arrays.asList(participantNames));
        if (participantNames.length > nameSet.size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다. 참가자명을 확인해주세요.");
        }
    }

    private static void validateNames(String participantNames) {
        if (StringUtils.isBlank(participantNames)) {
            throw new IllegalArgumentException("참가자명을 입력해 주세요.");
        }
    }

    public List<String> names() {
        List<String> names = new ArrayList<>();
        for (Participant participant : participants) {
            names.add(participant.name());
        }
        return names;
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.iterator();
    }
}
