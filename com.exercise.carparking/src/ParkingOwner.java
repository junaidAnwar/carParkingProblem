import java.util.Observable;
import java.util.Observer;

public class ParkingOwner implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Boolean isParkingSpaceFull =(Boolean)arg;
        if(isParkingSpaceFull) {
            showFullSign();
            return;
        }
        removeFullSign();
        
    }

    private void removeFullSign() {

    }


    private void showFullSign(){

    }
}
