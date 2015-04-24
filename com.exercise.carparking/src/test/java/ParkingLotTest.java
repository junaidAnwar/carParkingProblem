import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Test
    public void shouldNotCreateParkingLotWithNoParkingLotSpace() {
        String exceptionMsg = null;
        try {
            new ParkingLot(0);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Cannot create parking lot with no space", exceptionMsg);
    }

    @Test
    public void shouldNotCreateParkingLotWithNegativeParkingLotSize() {
        String exceptionMsg = null;
        try {
            new ParkingLot(-1);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Cannot create parking lot with no space", exceptionMsg);
    }

    @Test
    public void shouldParkCarWhenParkingSpaceIsAvailable() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        int parkingTicketNumber = parkingLot.parkCar(car);
        boolean isValidParkingTicket = parkingTicketNumber >= 0;
        assertTrue(isValidParkingTicket);
    }

    @Test
    public void shouldNotParkWithNoCar() throws Exception {
        String exceptionMsg = null;
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.parkCar(null);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Car not present", exceptionMsg);
    }

    @Test
    public void shouldNotParkCarWhenSameCarIsParked() throws Exception {
        try {
            ParkingLot parkingLot = new ParkingLot(2);
            Car car = new Car();
            int parkingTicketNumber = parkingLot.parkCar(car);
            int parkingTicketNumber1 = parkingLot.parkCar(car);
        }
        catch(Exception e) {
            assertEquals("Same Car is Already Parked", e.getMessage());
        }
    }

    @Test
    public void shouldNotParkCarWhenParkingSpaceIsNotAvailable() throws Exception {
        String exceptionMsg = null;
        try {
            Car carA = new Car();
            Car carB = new Car();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.parkCar(carA);
            parkingLot.parkCar(carB);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Parking Lot is full", exceptionMsg);
    }

    @Test
    public void shouldNotParkCarAtSameSlotWhenAnotherCarIsAlreadyParked() throws Exception {
        Car carA = new Car();
        Car carB = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        int tokenA = parkingLot.parkCar(carA);
        int tokenB = parkingLot.parkCar(carB);
        assertNotEquals(tokenA, tokenB);
    }

    @Test
    public void shouldRetrieveTheParkedCarForTheAssignedTicket() throws Exception {
        Car carA = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        int ticket = parkingLot.parkCar(carA);
        Car carRetrieved = parkingLot.retrieveParkedCarForTicket(ticket);
        assertSame(carA, carRetrieved);
    }

    @Test
    public void shouldNotRetrieveTheCarWithInvalidTicket() throws Exception {
        String exceptionMsg = null;
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            int invalidTicket = -1;
            parkingLot.retrieveParkedCarForTicket(invalidTicket);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Invalid Ticket Number", exceptionMsg);
    }

    @Test
    public void shouldNotifyOnlyParkingLotOwnerObserverWhenParkingSpaceIsFull() throws Exception {
        Car carA = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        PersonObserver personObserver = mock(PersonObserver.class);
        parkingLot.addPersonAsObserver(personObserver);
        parkingLot.parkCar(carA);

        verify(personObserver).updateWhenParkingLotIsFull();
    }

    @Test
    public void shouldNotifyParkingLotOwnerObserverWhenParkingSpaceIsAvailable() throws Exception {
        Car carA = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        PersonObserver personObserver = mock(PersonObserver.class);
        parkingLot.addPersonAsObserver(personObserver);
        int ticketNumber = parkingLot.parkCar(carA);
        parkingLot.retrieveParkedCarForTicket(ticketNumber);
        verify(personObserver).updateWhenParkingLotIsAvailable();
    }

    @Test
    public void shouldNotifyAgentObserverWhenParkingSpaceIs80PercentFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        AgentObserver agentObserver = mock(AgentObserver.class);
        parkingLot.addAgentObserver(agentObserver);

        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
       verify(agentObserver).updateWhenParkingLotIs80PercentFull();
    }

    @Test
    public void shouldNotNotifyAgentObserverWhenParkingSpaceIsNot80PercentFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        AgentObserver agentObserver = mock(AgentObserver.class);
        parkingLot.addAgentObserver(agentObserver);

        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        verify(agentObserver,never()).updateWhenParkingLotIs80PercentFull();
    }



    @Test
    public void shouldNotifyAgentObserverAsSoonAsParkingSpaceIsNoLonger80PercentFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        AgentObserver agentObserver = mock(AgentObserver.class);
        parkingLot.addAgentObserver(agentObserver);
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        int ticket = parkingLot.parkCar(new Car());
        parkingLot.retrieveParkedCarForTicket(ticket);
        verify(agentObserver, times(1)).updateWhenParkingLotIsNoLonger80PercentFull();
    }

    @Test
    public void shouldNotNotifyAgentObserverIfParkingSpaceWasNot80PercentFullBeforeCarRetrieval() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        AgentObserver agentObserver = mock(AgentObserver.class);
        parkingLot.addAgentObserver(agentObserver);
        parkingLot.parkCar(new Car());
        parkingLot.parkCar(new Car());
        int ticket = parkingLot.parkCar(new Car());
        parkingLot.retrieveParkedCarForTicket(ticket);
        verify(agentObserver, times(0)).updateWhenParkingLotIsNoLonger80PercentFull();
    }

    @Test
    public void shouldNotAddParkingOwnerAsObserverWhenThereIsNoObserver() {
        String exceptionMsg = null;
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.addPersonAsObserver(null);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Cannot add Person as Observer as person is not present", exceptionMsg);
    }

    @Test
    public void shouldNotRetrieveTheCarForEmptyParkingSpace() throws Exception {
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            int ticket = 0;
            parkingLot.retrieveParkedCarForTicket(ticket);
        }
        catch (Exception e) {
            assertEquals("Car not Found", e.getMessage());
        }
    }

}