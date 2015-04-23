import java.util.List;

public class ParkingAttendant {

    private List<ParkingLot> parkingLotList;


    public ParkingAttendant(List<ParkingLot> parkingLotList) throws Exception {
        if(parkingLotList == null || parkingLotList.isEmpty()) {
            throw new Exception("Cannot Create Parking Attendant as Parking lot list not Present");
        }
        this.parkingLotList = parkingLotList;
    }

    public Ticket parkCar(Car car) throws Exception {
        Ticket ticket;
        ParkingLot parkingLot = getFreeParkingLot();
        int ticketNumber = parkingLot.parkCar(car);
        int parkingLotNumber = parkingLotList.indexOf(parkingLot);
        ticket = new Ticket(parkingLotNumber, ticketNumber);
        return ticket;
    }

    private ParkingLot getFreeParkingLot() throws Exception {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot != null && !parkingLot.isParkingFull()) {
                return parkingLot;
            }
        }
        throw new Exception("All parking lots are full.");
    }


    public Car retrieveParkedCarForTicket(Ticket ticket) throws Exception {
        if(ticket ==  null) {
            throw new Exception("Invalid Ticket");
        }
        ParkingLot parkingLot = parkingLotList.get(ticket.getParkingLotNumber());
        if(parkingLot == null) {
            throw  new Exception("Parking lot does not exist");
        }
        return parkingLot.retrieveParkedCarForTicket(ticket.getTicketNumber());
    }
}
