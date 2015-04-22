import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingOwnerTest {
    @Test
    public void testUpdateForFullParkingSpace() throws Exception {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingOwner.update(null, true);

        assertTrue(parkingOwner.isParkingFull());
    }

    @Test
    public void testUpdateForAvailableParkingSpace() throws Exception {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingOwner.update(null, false);

        assertFalse(parkingOwner.isParkingFull());
    }
}