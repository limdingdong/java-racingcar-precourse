package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<String> names() {
        List<String> names = new ArrayList<>();
        for (Participant participant : participants) {
            names.add(participant.name());
        }
        return names;
    }
}
