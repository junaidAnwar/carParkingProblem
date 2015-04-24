import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NormalSeasonParkingStrategyTest {
    NormalSeasonParkingStrategy normalSeasonParkingStrategy;
    ArrayList<ParkingLot> parkingLotsList;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
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
            normalSeasonParkingStrategy.getFreeParkingLot(null,"");
        } catch (Exception e) {
           assertEquals("Invalid parking lot list.", e.getMessage());
        }

    }

    @Test
    public void shouldThrowExceptionWhenNoCarSizeIsPresent() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Car size not present");
        normalSeasonParkingStrategy.getFreeParkingLot(parkingLotsList,null);

    }

    @Test
    public void shouldReturnMostFreeSpaceParkingLotForLargeCar() throws Exception {
        ParkingLot parkingLotA, parkingLotB, parkingLotC;
        parkingLotA = mock(ParkingLot.class);
        parkingLotB = mock(ParkingLot.class);
        parkingLotC = mock(ParkingLot.class);

        when(parkingLotA.getNumberOfFreeParkingLots()).thenReturn(5);
        when(parkingLotB.getNumberOfFreeParkingLots()).thenReturn(6);
        when(parkingLotC.getNumberOfFreeParkingLots()).thenReturn(0);
        ArrayList<ParkingLot> parkingLotsList;
        parkingLotsList = new ArrayList<ParkingLot>();
        parkingLotsList.add(parkingLotA);
        parkingLotsList.add(parkingLotB);
        parkingLotsList.add(parkingLotC);

        ParkingLot parkingLot = normalSeasonParkingStrategy.getFreeParkingLot(parkingLotsList, "large");
        assertSame(parkingLotB, parkingLot);

    }
}