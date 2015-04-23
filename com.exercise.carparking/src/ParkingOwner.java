import java.util.Observable;
import java.util.Observer;

public class ParkingOwner implements Observer {

    private boolean parkingFull;

    @Override
    public void update(Observable o, Object arg) {

        Boolean isParkingSpaceFull = (Boolean) arg;
        if (isParkingSpaceFull) {
            showFullSign();
            return;
        }
        removeFullSign();

    }

    private void removeFullSign() {
        parkingFull = false;
    }


    private void showFullSign() {
        parkingFull = true;
    }

    public boolean isParkingFull() {

        return parkingFull;
    }
}
