import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TravellerTest {
    
    private ArrayList<ParkingLot> parkingLotList;
    private ParkingAttendant parkingAttendant;

    @Before
    public void setUp() throws Exception {
        parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        parkingAttendant = new ParkingAttendant(parkingLotList);
    }

    @Test
    public void shouldParkCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        String result = traveller.parkCar(parkingAttendant);
        assertEquals("Car is Parked", result);
    }

    @Test
     public void shouldNotParkCarWhenCarIsAlreadyParked() throws Exception {
        String outputException=null;
        try {
            Traveller traveller =new Traveller(new Car());
            traveller.parkCar(parkingAttendant);
            traveller.parkCar(parkingAttendant);
        } catch (Exception e) {
            outputException=e.getMessage();
        }
        assertEquals("Car is already Parked!", outputException);
    }


    @Test
    public void shouldRetrieveCarWhenCarIsParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        traveller.parkCar(parkingAttendant);
        String successMsg = traveller.retrieveParkedCar(parkingAttendant);
        assertEquals("Car is retrieved", successMsg);

    }

    @Test
    public void shouldNotRetrieveCarWhenCarIsNotParked() throws Exception {
        Traveller traveller =new Traveller(new Car());
        String successMsg = traveller.retrieveParkedCar(parkingAttendant);
        assertEquals("Car is not parked", successMsg);

    }

    @Test
    public void shouldParkCarAfterRetrievingCar() throws Exception {
        Traveller traveller =new Traveller(new Car());
        traveller.parkCar(parkingAttendant);
        traveller.retrieveParkedCar(parkingAttendant);
        String successMsg = traveller.parkCar(parkingAttendant);
        assertEquals("Car is Parked", successMsg);

    }


}