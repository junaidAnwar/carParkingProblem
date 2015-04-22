import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void shouldNotCreateParkingLotWithNoParkingLotSpace() {
        String exceptionMsg = null;
        try {
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
        ParkingLot parkingLot = new ParkingLot(1);
        int parkingTicketNumber = parkingLot.parkCar(car);
        assertTrue(parkingTicketNumber >= 0);
    }

    @Test
    public void shouldNotParkCarWhenNoCar() throws Exception {
        String exceptionMsg = null;
        try {
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
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.parkCar(carA);
            parkingLot.parkCar(carB);
        }
        catch (Exception e){
            exceptionMsg=e.getMessage();
        }
        assertEquals("Parking Lot is full", exceptionMsg);


    }
}