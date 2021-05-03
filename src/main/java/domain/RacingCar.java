package domain;

public class RacingCar {

    private int position;

    public void drive(DriveStrategy driveStrategy) {
        if (driveStrategy.driveable()) {
           position++;
        }
    }

    public int position() {
        return this.position;
    }
}
