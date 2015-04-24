import java.util.List;

public interface ParkingStrategy {
    ParkingLot getFreeParkingLot(List<ParkingLot> parkingLotList, String carSize) throws Exception;

}
