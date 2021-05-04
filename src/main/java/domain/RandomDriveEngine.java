package domain;

import java.util.Random;

public class RandomDriveEngine implements DriveStrategy {

    private static final int MOVABLE_MIN_NUMBER = 4;
    private static final int MOVABLE_MAX_NUMBER = 9;

    private final Random random;

    public RandomDriveEngine() {
        this.random = new Random();
    }

    @Override
    public boolean driveable() {
        return random.nextInt(MOVABLE_MAX_NUMBER)>=MOVABLE_MIN_NUMBER;
    }
}
