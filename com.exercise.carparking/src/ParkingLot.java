/**
 * Created by Junaid on 22-04-2015.
 */
public class ParkingLot {

    public static final String CAR_OBJECT_IS_NULL = "Car not present";
    private final int parkingLotSize;
    boolean parkingSlots;

    public ParkingLot(int parkingLotSize) throws Exception {
        if(parkingLotSize <= 0){
            throw new Exception("Cannot create parking lot with no space");
        }
        this.parkingLotSize = parkingLotSize;
    }

    public int parkCar(Car car) {
        if(car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
        if()
        return 0;
    }
}
