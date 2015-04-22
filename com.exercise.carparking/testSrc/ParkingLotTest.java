
import org.junit.Test;

import java.util.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Test
    public void shouldNotCreateParkingLotWithNoParkingLotSpace() {
        String exceptionMsg = null;
        try {
            ParkingOwner parkingOwner = new ParkingOwner();
            ParkingLot parkingLot = new ParkingLot(0);
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
            ParkingOwner parkingOwner = new ParkingOwner();
            ParkingLot parkingLot = new ParkingLot(-1);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Cannot create parking lot with no space", exceptionMsg);
    }


    @Test
    public void shouldParkCarWhenParkingSpaceIsAvailable() throws Exception {
        Car car = new Car();
        ParkingOwner parkingOwner = new ParkingOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        int parkingTicketNumber = parkingLot.parkCar(car);
        assertTrue(parkingTicketNumber >= 0);
    }

    @Test
    public void shouldNotParkCarWhenNoCar() throws Exception {
        String exceptionMsg = null;
        try {
            ParkingOwner parkingOwner = new ParkingOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            int parkingTicketNumber = parkingLot.parkCar(null);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Car not present", exceptionMsg);


    }


    @Test
    public void shouldNotParkCarWhenParkingSpaceIsNotAvailable() throws Exception {
        String exceptionMsg = null;
        try {
            Car carA = new Car();
            Car carB = new Car();
            ParkingOwner parkingOwner = new ParkingOwner();
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
    public void shouldNotifyObserverWhenParkingSpaceIsFull() throws Exception {

            Car carA = new Car();
            ParkingLot parkingLot = new ParkingLot(1);
            ParkingOwner parkingOwner = mock(ParkingOwner.class);
            parkingLot.addParkingOwnerAsObserver(parkingOwner);
            doNothing().when(parkingOwner).update(parkingLot, true);
            parkingLot.parkCar(carA);

            verify(parkingOwner).update(parkingLot,true);
    }



    @Test
    public void shouldNotParkCarAtSameSlotWhenAnotherCarIsAlreadyParked() throws Exception {
        Car carA = new Car();
        Car carB = new Car();
        ParkingOwner parkingOwner = new ParkingOwner();
        ParkingLot parkingLot = new ParkingLot(2);
        int parkingSlotA = parkingLot.parkCar(carA);
        int parkingSlotB = parkingLot.parkCar(carB);
        assertNotEquals(parkingSlotA, parkingSlotB);
    }

    @Test
    public void shouldRetrieveTheParkedCarForTheAssignedTicket() throws Exception {
        Car carA = new Car();
        ParkingOwner parkingOwner = new ParkingOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        int parkingSlotA = parkingLot.parkCar(carA);
        boolean isCarRetrieved = parkingLot.retrieveParkedCarForTicket(parkingSlotA);
        assertTrue(isCarRetrieved);
    }

    @Test
    public void shouldNotRetrieveTheCarWithInvalidTicket() throws Exception {
        String exceptionMsg = null;
        try {
            ParkingOwner parkingOwner = new ParkingOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            int parkingSlotA = -1;
            parkingLot.retrieveParkedCarForTicket(parkingSlotA);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Invalid Ticket Number", exceptionMsg);

    }

    @Test
    public void shouldNotifyObserverWhenParkingSpaceIsAvailable() throws Exception {

        Car carA = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLot.addParkingOwnerAsObserver(parkingOwner);

        doNothing().when(parkingOwner).update(parkingLot, false);

        int ticketNumber = parkingLot.parkCar(carA);
        parkingLot.retrieveParkedCarForTicket(ticketNumber);
        verify(parkingOwner).update(parkingLot,false);
    }

    @Test
    public void shouldNotRetrieveTheCarForEmptyParkingSpace() throws Exception {
        ParkingOwner parkingOwner = new ParkingOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        int parkingSlotA = 0;
        boolean result = parkingLot.retrieveParkedCarForTicket(parkingSlotA);
        assertFalse(result);

    }


    @Test
    public void shouldNotAddParkingOwnerAsObserverWhenThereIsNoObserver() {
        String exceptionMsg = null;
        try {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.addParkingOwnerAsObserver(null);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Cannot add parking owner as owner is not present", exceptionMsg);
    }

}