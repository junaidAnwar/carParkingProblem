import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TicketTest {

    private Ticket ticket;

    @Test
    public  void shouldCreateTicketWithGivenParkingLotNumber() throws Exception {
        ticket = new Ticket(1, 2, "");
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(1, parkingLotNumber);
    }

    @Test
    public  void shouldCreateTicketWithGivenTicketNumber() throws Exception {
        ticket = new Ticket(1, 2, "");
        int ticketNumber = ticket.getTicketNumber();
        assertEquals(2,ticketNumber);
    }

    @Test
    public  void shouldCreateTicketWithGivenParkingTime() throws Exception {
        String expectedParkingTime = new Date().toString();
        ticket = new Ticket(1, 2, expectedParkingTime);
        String actualParkingTime = ticket.getParkingTime();
        assertEquals(expectedParkingTime,actualParkingTime);
    }

    @Test
    public  void shouldNotCreateTicketWithInvalidArguments() throws Exception {
        try {
            ticket = new Ticket(-1, 2, null);
        } catch (Exception e) {
            assertEquals("Ticket Cannot be Created For Invalid Input", e.getMessage());
        }

    }


}