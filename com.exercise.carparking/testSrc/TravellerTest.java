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
     public void shouldNotParkCarWhenCarIsNull() throws Exception {
        String outputException=null;
        try {
            Traveller traveller =new Traveller(null);
            traveller.parkCar();
        } catch (Exception e) {
            outputException=e.getMessage();
        }
        assertEquals("Car object is null", outputException);
    }

    @Test(expected = Exception.class)
     public void shouldNotParkCarWhenCarIsAlreadyParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        traveller.parkCar();
        traveller.parkCar();

    }

}