import java.util.List;

public interface ParkingStrategy {
   ParkingLot getFreeParkingLot(List<ParkingLot> parkingLotList) throws Exception;
}
