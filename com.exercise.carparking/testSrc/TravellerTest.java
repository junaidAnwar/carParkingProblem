import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravellerTest {

    @Test
    public void shouldParkCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(1);
        String result = traveller.parkCar(parkingLot);
        assertEquals("Car is Parked", result);
    }

    @Test
     public void shouldNotParkCarWhenCarIsAlreadyParked() throws Exception {
        String outputException=null;
        try {
            Traveller traveller =new Traveller(new Car());
            ParkingLot parkingLot = new ParkingLot(1);
            traveller.parkCar(parkingLot);
            traveller.parkCar(parkingLot);
        } catch (Exception e) {
            outputException=e.getMessage();
        }
        assertEquals("Car is already Parked!", outputException);
    }


    @Test
    public void shouldRetrieveCarWhenCarIsParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(1);
        traveller.parkCar(parkingLot);
        String successMsg = traveller.retrieveParkedCar(parkingLot);
        assertEquals("Car is retrieved", successMsg);

    }

    @Test
    public void shouldNotRetrieveCarWhenCarIsNotParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(1);
        String successMsg = traveller.retrieveParkedCar(parkingLot);
        assertEquals("Car is not parked", successMsg);

    }

    @Test
    public void shouldParkCarAfterRetrievingCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        ParkingLot parkingLot = new ParkingLot(1);
        traveller.parkCar(parkingLot);
        traveller.retrieveParkedCar(parkingLot);
        String successMsg = traveller.parkCar(parkingLot);
        assertEquals("Car is Parked", successMsg);

    }


}