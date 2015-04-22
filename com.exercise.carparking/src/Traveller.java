
public class Traveller {
    private static final String CAR_OBJECT_IS_NULL = "Car not present";
    private static final String CAR_IS_ALREADY_PARKED = "Car is already Parked!";
    private static final String CAR_IS_UNPARKED = "Car is unparked";
    private static final String CAR_IS_NOT_PARKED = "Car is not parked";
    private static  final int INVALID_TICKET_NUMBER=-1;
    private static final String CAR_IS_PARKED = "Car is Parked";

    private final Car car;
    private boolean isCarParked=false;
    private int ticketNumber=INVALID_TICKET_NUMBER;


    public Traveller(Car car) {
        this.car = car;
    }

    public String parkCar(ParkingLot parkingLot) throws Exception {
        if(car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
        if(isCarParked) {
            throw new Exception(CAR_IS_ALREADY_PARKED);
        }
        ticketNumber=parkingLot.parkCar(car);
        isCarParked=true;
        return CAR_IS_PARKED;
    }

    public String retrieveParkedCar(ParkingLot parkingLot) throws Exception {
        if(isCarParked) {
            parkingLot.retrieveParkedCarForTicket(ticketNumber);
            isCarParked=false;
            ticketNumber=INVALID_TICKET_NUMBER;
            return CAR_IS_UNPARKED;
        }
        return CAR_IS_NOT_PARKED;
    }
}
