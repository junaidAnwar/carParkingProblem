import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotOwnerTest {

    ParkingLotOwner parkingLotOwner;

    @Before
    public void setUp() {
        parkingLotOwner = new ParkingLotOwner();
    }

    @Test
    public void shouldNotAddNullParkingLotToTheParkingLotList() throws Exception {
        boolean isAdded = parkingLotOwner.addParkingLotToTheList(null);
        assertFalse(isAdded);
    }

    @Test
    public void shouldAddParkingLotToTheParkingLotList() throws Exception {
        boolean isAdded = parkingLotOwner.addParkingLotToTheList(new ParkingLot(5));
        assertTrue(isAdded);
    }


}