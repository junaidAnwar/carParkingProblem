import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {

    private Ticket ticket;

    @Test
    public  void shouldCreateTicketWithGivenParkingLotNumber() throws Exception {
        ticket = new Ticket(1, 2);
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(1, parkingLotNumber);
    }

    @Test
    public  void shouldCreateTicketWithGivenTicketNumber() throws Exception {
        ticket = new Ticket(1, 2);
        int ticketNumber = ticket.getTicketNumber();
        assertEquals(2,ticketNumber);
    }

    @Test
    public  void shouldNotCreateTicketWithInvalidArguments() throws Exception {
        try {
            ticket = new Ticket(-1, 2);
        } catch (Exception e) {
            assertEquals("Ticket Cannot be Created For Invalid Input", e.getMessage());
        }

    }


}