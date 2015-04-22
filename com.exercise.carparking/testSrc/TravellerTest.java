import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravellerTest {

    @Test
    public void shouldParkCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        String result = traveller.parkCar();
        assertEquals("Car is Parked", result);
    }

    @Test
     public void shouldNotParkCarWhenNoCar() throws Exception {
        String outputException=null;
        try {
            Traveller traveller =new Traveller(null);
            traveller.parkCar();
        } catch (Exception e) {
            outputException=e.getMessage();
        }
        assertEquals("Car not present", outputException);
    }

    @Test
     public void shouldNotParkCarWhenCarIsAlreadyParked() throws Exception {
        String outputException=null;
        try {
            Traveller traveller =new Traveller(new Car());
            traveller.parkCar();
            traveller.parkCar();
        } catch (Exception e) {
            outputException=e.getMessage();
        }
        assertEquals("Car is already Parked!", outputException);
    }


    @Test
    public void shouldRetrieveCarWhenCarIsParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        traveller.parkCar();
        String successMsg = traveller.retrieveParkedCar();
        assertEquals("Car is unparked", successMsg);

    }

    @Test
    public void shouldNotRetrieveCarWhenCarIsNotParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        String successMsg = traveller.retrieveParkedCar();
        assertEquals("Car is not parked", successMsg);

    }

    @Test
    public void shouldParkCarAfterRetrievingCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        traveller.parkCar();
        traveller.retrieveParkedCar();
        String successMsg = traveller.parkCar();
        assertEquals("Car is Parked", successMsg);

    }


}