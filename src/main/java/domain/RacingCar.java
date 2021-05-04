package domain;

public class RacingCar implements Comparable<RacingCar> {

    private int position;

    public void drive(DriveStrategy driveStrategy) {
        if (driveStrategy.driveable()) {
            position++;
        }
    }

    public int position() {
        return this.position;
    }

    public boolean isWinner(int winnerPosition) {
        return this.position >= winnerPosition;
    }

    @Override
    public int compareTo(RacingCar obj) {
        return Integer.compare(this.position, obj.position);
    }
}
