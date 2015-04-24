
public class Ticket {
    private int parkingLotNumber;
    private int ticketNumber;
    private String parkingTime;

    public Ticket(int parkingLotNumber, int ticketNumber, String parkingTime) throws Exception {
        if(parkingLotNumber <0 || ticketNumber <0 || parkingTime == null) {
            throw new Exception("Ticket Cannot be Created For Invalid Input");
        }
        this.parkingLotNumber = parkingLotNumber;
        this.ticketNumber = ticketNumber;
        this.parkingTime = parkingTime;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getParkingTime() {
        return parkingTime;
    }
}
