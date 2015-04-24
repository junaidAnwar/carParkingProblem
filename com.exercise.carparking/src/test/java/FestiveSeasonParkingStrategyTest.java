import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertSame;

public class FestiveSeasonParkingStrategyTest {
    ParkingStrategy festiveSeasonParkingStrategy;
    ArrayList<ParkingLot> parkingLotsList;
    ParkingLot maxSizeParkingLot;
    @Before
    public void setUp() throws Exception {
        festiveSeasonParkingStrategy = new FestiveSeasonParkingStrategy();
        parkingLotsList = new ArrayList<ParkingLot>();
        parkingLotsList.add(new ParkingLot(1));
        parkingLotsList.add(new ParkingLot(2));
        maxSizeParkingLot = new ParkingLot(3);
        parkingLotsList.add(maxSizeParkingLot);
    }

    @Test
    public void shouldReturnParkingLotWithMaximumSize() throws Exception {
        ParkingLot parkingLot = festiveSeasonParkingStrategy.getFreeParkingLot(parkingLotsList);
        assertSame(maxSizeParkingLot, parkingLot);
    }
}