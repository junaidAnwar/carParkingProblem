import java.util.ArrayList;
import java.util.List;

public class ParkingLotOwner implements PersonObserver {

    private final List<ParkingLot> parkingLotList;

    public ParkingLotOwner() {
       parkingLotList = new ArrayList<ParkingLot>();
    }

    public boolean addParkingLotToTheList(ParkingLot parkingLot){
        if(parkingLot != null) {
            return parkingLotList.add(parkingLot);
        }
        return false;
    }


    @Override
    public void updateWhenParkingLotIsFull() {

    }

    @Override
    public void updateWhenParkingLotIsAvailable() {

    }

    public static ParkingStrategy getParkingStrategy() {
        return new NormalSeasonParkingStrategy();
    }
}
