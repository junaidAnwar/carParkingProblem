import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingAttendantTest {

    ParkingAttendant parkingAttendant;
    List<ParkingLot> parkingLotList;

    @Before
    public void setUp() throws Exception {
        parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        parkingAttendant = new ParkingAttendant(parkingLotList);
    }

    @Test
    public void shouldNotCreateParkingAttendantForEmptyParkingLotList() throws Exception {
        try {
            new ParkingAttendant(null);
        } catch (Exception e) {
            assertEquals("Cannot Create Parking Attendant as Parking lot list not Present", e.getMessage());
        }
    }

    @Test
      public void shouldParkCarAtFirstParkingLot() throws Exception {
        Car car = new Car("");
        Ticket ticket = parkingAttendant.parkCar(car);
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(0, parkingLotNumber);
    }

    @Test
    public void shouldParkCarAtSecondParkingLot() throws Exception {
        parkingAttendant.parkCar(new Car(""));
        Ticket ticket = parkingAttendant.parkCar(new Car(""));
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(1, parkingLotNumber);
    }

    @Test
    public void shouldRetrieveCarFromParkingLot() throws Exception {
        Car car = new Car("");
        Ticket ticket = parkingAttendant.parkCar(car);
        Car retrievedCar = parkingAttendant.retrieveParkedCarForTicket(ticket);
        assertSame(car, retrievedCar);
    }

    @Test
    public void shouldNotRetrieveCarFromNonExistingParkingLot() throws Exception {
        try {
            Car car = new Car("");
            parkingAttendant.parkCar(car);
            parkingAttendant.parkCar(car);
            Ticket ticket = parkingAttendant.parkCar(car);
            parkingLotList.set(2, null);
            parkingAttendant.retrieveParkedCarForTicket(ticket);
        } catch (Exception e) {
            assertEquals("Parking lot does not exist", e.getMessage());
        }
    }

    @Test
    public void shouldNotParkCarIfParkingLotIsFull() throws Exception {
        try {
            Car car = new Car("");
            parkingAttendant.parkCar(car);
            parkingAttendant.parkCar(car);
            parkingAttendant.parkCar(car);
            parkingAttendant.parkCar(car);
        } catch (Exception e) {
            assertEquals("All parking lots are full.", e.getMessage());
        }
    }

}