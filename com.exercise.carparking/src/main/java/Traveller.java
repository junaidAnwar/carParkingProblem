public class Traveller {
    private static final String CAR_IS_ALREADY_PARKED = "Car is already Parked!";
    private static final String CAR_IS_RETRIEVED = "Car is retrieved";
    private static final String CAR_IS_NOT_PARKED = "Car is not parked";
    private static final Ticket INVALID_TICKET = null;
    private static final String CAR_IS_PARKED = "Car is Parked";

    private final Car car;
    private Ticket ticket = INVALID_TICKET;


    public Traveller(Car car) {
        this.car = car;
    }

    public String parkCar(ParkingAttendant parkingAttendant) throws Exception {
        if (isCarParked()) {
            throw new Exception(CAR_IS_ALREADY_PARKED);
        }
        ticket = parkingAttendant.parkCar(car);
        return CAR_IS_PARKED;
    }

    public boolean isCarParked() {
        return ticket != INVALID_TICKET;
    }

    public String retrieveParkedCar(ParkingAttendant parkingAttendant) throws Exception {
        if (isCarParked()) {
            parkingAttendant.retrieveParkedCarForTicket(ticket);
            ticket = INVALID_TICKET;
            return CAR_IS_RETRIEVED;
        }
        return CAR_IS_NOT_PARKED;
    }
}