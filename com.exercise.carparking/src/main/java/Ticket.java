
public class Ticket {
    private int parkingLotNumber;
    private int ticketNumber;

    public Ticket(int parkingLotNumber, int ticketNumber) throws Exception {
        if(parkingLotNumber <0 || ticketNumber <0) {
            throw new Exception("Ticket Cannot be Created For Invalid Input");
        }
        this.parkingLotNumber = parkingLotNumber;
        this.ticketNumber = ticketNumber;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}
