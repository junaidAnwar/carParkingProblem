import java.util.List;

public class FestiveSeasonParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot getFreeParkingLot(List<ParkingLot> parkingLotList) throws Exception {
        isValidParkingLotList(parkingLotList);
        ParkingLot parkingLotWithMaximumSize = parkingLotList.get(0);
        for (ParkingLot parkingLot : parkingLotList) {
            if (IsCurrentParkingLotIsGreaterThanPreviousParkingLot(parkingLotWithMaximumSize, parkingLot)) {
                parkingLotWithMaximumSize = parkingLot;
            }
        }
        if (parkingLotWithMaximumSize.isParkingFull()) {
            throw new Exception("All parking lots are full.");
        }
        return parkingLotWithMaximumSize;
    }

    private boolean IsCurrentParkingLotIsGreaterThanPreviousParkingLot(ParkingLot parkingLotWithMaximumSize, ParkingLot parkingLot) {
        return parkingLot != null && parkingLotWithMaximumSize != null && !parkingLot.isParkingFull() && parkingLot.getParkingLotSize() > parkingLotWithMaximumSize.getParkingLotSize();
    }

    private boolean isValidParkingLotList(List<ParkingLot> parkingLotList) throws Exception {
        if (parkingLotList != null && parkingLotList.size() > 0)
            return true;
        throw new Exception("Invalid parking lot list.");

    }
}
