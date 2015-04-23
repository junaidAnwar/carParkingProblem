import java.util.List;

public class ParkingAttendant {

    private List<ParkingLot> parkingLotList;


    public ParkingAttendant(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket parkCar(Car car) throws Exception {
        Ticket ticket;
        ParkingLot parkingLot = getFreeParkingLot();
        int ticketNumber = parkingLot.parkCar(car);
        ticket = new Ticket(parkingLotList.indexOf(parkingLot), ticketNumber);
        return ticket;
    }

    private ParkingLot getFreeParkingLot() throws Exception {
        for (ParkingLot parkingLot : parkingLotList) {
            if (!parkingLot.isParkingFull()) {
                return parkingLot;
            }
        }
        throw new Exception("All parking lots are full.");
    }


    public Car retrieveParkedCarForTicket(Ticket ticket) {
        return null;
    }
}
