public class Traveller {
    private static final String CAR_OBJECT_IS_NULL = "Car not present";
    private static final String CAR_IS_ALREADY_PARKED = "Car is already Parked!";
    private static final String CAR_IS_RETRIEVED = "Car is retrieved";
    private static final String CAR_IS_NOT_PARKED = "Car is not parked";
    private static final int INVALID_TICKET_NUMBER = -1;
    private static final String CAR_IS_PARKED = "Car is Parked";

    private final Car car;
    private int ticketNumber = INVALID_TICKET_NUMBER;


    public Traveller(Car car) {
        this.car = car;
    }

    public String parkCar(ParkingLot parkingLot) throws Exception {
        if (isCarParked()) {
            throw new Exception(CAR_IS_ALREADY_PARKED);
        }
        ticketNumber = parkingLot.parkCar(car);
        return CAR_IS_PARKED;
    }

    public boolean isCarParked() {
        return ticketNumber != INVALID_TICKET_NUMBER;
    }

    public String retrieveParkedCar(ParkingLot parkingLot) throws Exception {
        if (isCarParked()) {
            parkingLot.retrieveParkedCarForTicket(ticketNumber);
            ticketNumber = INVALID_TICKET_NUMBER;
            return CAR_IS_RETRIEVED;
        }
        return CAR_IS_NOT_PARKED;
    }
}