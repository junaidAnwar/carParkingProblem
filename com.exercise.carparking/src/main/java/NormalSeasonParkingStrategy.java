import java.util.List;

public class NormalSeasonParkingStrategy implements ParkingStrategy {
    private ParkingLot freeParkingLotForLargeCar;

    @Override
    public ParkingLot getFreeParkingLot(List<ParkingLot> parkingLotList, String carSize) throws Exception {
        isValidParkingLotList(parkingLotList);
        if(carSize ==null) {
            throw new Exception("Car size not present");
        }
        switch (carSize.toLowerCase()) {
            case "large": {
                return getFreeParkingLotForLargeCar(parkingLotList);
            }
            default: {
                return  getFreeParkingLotForNormalCar(parkingLotList);
            }
        }
    }

    private ParkingLot getFreeParkingLotForNormalCar(List<ParkingLot> parkingLotList) throws Exception {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot != null && !parkingLot.isParkingFull()) {
                return parkingLot;
            }
        }
        throw new Exception("All parking lots are full.");
    }

    public ParkingLot getFreeParkingLotForLargeCar(List<ParkingLot> parkingLotList) throws Exception {
        isValidParkingLotList(parkingLotList);
        ParkingLot parkingLotWithMaximumFreeSpace = parkingLotList.get(0);
        for (ParkingLot parkingLot : parkingLotList) {
            if (IsCurrentFreeParkingLotSpaceIsGreaterThanPreviousFreeParkingLotSpace(parkingLotWithMaximumFreeSpace, parkingLot)) {
                parkingLotWithMaximumFreeSpace = parkingLot;
            }
        }
        if (parkingLotWithMaximumFreeSpace.isParkingFull()) {
            throw new Exception("All parking lots are full.");
        }
        return parkingLotWithMaximumFreeSpace;
    }
    private boolean IsCurrentFreeParkingLotSpaceIsGreaterThanPreviousFreeParkingLotSpace(ParkingLot parkingLotWithMaximumFreeSpace, ParkingLot parkingLot) {
        return parkingLot != null && parkingLotWithMaximumFreeSpace != null && parkingLot.getNumberOfFreeParkingLots() > parkingLotWithMaximumFreeSpace.getNumberOfFreeParkingLots();
    }

    private boolean isValidParkingLotList(List<ParkingLot> parkingLotList) throws Exception {
        if(parkingLotList != null && parkingLotList.size() >0)
            return true;
        throw  new Exception("Invalid parking lot list.");

    }
}
