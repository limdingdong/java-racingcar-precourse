package service;

import domain.CarRacing;
import domain.Participants;
import domain.RacingResult;

public class CarRacingService {

    public RacingResult executeCarRacing(String participantNames, int roundCount) {
        Participants participants = Participants.from(participantNames);
        CarRacing carRacing = CarRacing.from(participants);
        return carRacing.executeRacing(roundCount);
    }
}
