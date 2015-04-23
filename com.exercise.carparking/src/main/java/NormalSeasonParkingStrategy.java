import java.util.List;

public class NormalSeasonParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot getFreeParkingLot(List<ParkingLot> parkingLotList) throws Exception {
        isValidParkingLotList(parkingLotList);
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot != null && !parkingLot.isParkingFull()) {
                return parkingLot;
            }
        }
        throw new Exception("All parking lots are full.");
    }

    private boolean isValidParkingLotList(List<ParkingLot> parkingLotList) throws Exception {
        if(parkingLotList != null && parkingLotList.size() >0)
            return true;
        throw  new Exception("Invalid parking lot list.");

    }
}
