import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NormalSeasonParkingStrategyTest {
    NormalSeasonParkingStrategy normalSeasonParkingStrategy;
    ArrayList<ParkingLot> parkingLotsList;
@Before
public void setUp() throws Exception {
    normalSeasonParkingStrategy = new NormalSeasonParkingStrategy();
    parkingLotsList = new ArrayList<ParkingLot>();
    parkingLotsList.add(new ParkingLot(1));
    parkingLotsList.add(new ParkingLot(1));
    parkingLotsList.add(new ParkingLot(1));
}

    @Test
    public void shouldThrowExceptionWhenNoParkingLotListIsPassed() throws Exception {
        try {
            normalSeasonParkingStrategy.getFreeParkingLot(null);
        } catch (Exception e) {
           assertEquals("Invalid parking lot list.", e.getMessage());
        }

    }

    @Test
    public void shouldReturnParkingLot() throws Exception {
        ParkingLot parkingLot = normalSeasonParkingStrategy.getFreeParkingLot(parkingLotsList);
        assertNotNull(parkingLot);
    }
}