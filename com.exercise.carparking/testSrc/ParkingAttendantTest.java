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
      public void shouldParkCarAtFirstParkingLot() throws Exception {
        Car car = new Car();
        Ticket ticket = parkingAttendant.parkCar(car);
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(0, parkingLotNumber);
    }

    @Test
    public void shouldParkCarAtSecondParkingLot() throws Exception {
        parkingAttendant.parkCar(new Car());
        Ticket ticket = parkingAttendant.parkCar(new Car());
        int parkingLotNumber = ticket.getParkingLotNumber();
        assertEquals(1, parkingLotNumber);
    }

    @Test
    public void shouldRetrieveCarFromParkingLot() throws Exception {
        Car car = new Car();
        Ticket ticket = parkingAttendant.parkCar(car);
        Car retrievedCar = parkingAttendant.retrieveParkedCarForTicket(ticket);
        assertSame(car, retrievedCar);
    }

}