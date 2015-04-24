import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FestiveSeasonParkingStrategyTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    ParkingStrategy festiveSeasonParkingStrategy;

    @Before
    public void setUp() throws Exception {
        festiveSeasonParkingStrategy = new FestiveSeasonParkingStrategy();

    }

    @Test
    public void shouldReturnParkingLotWithMaximumSize() throws Exception {
        ParkingLot parkingLotA, parkingLotB, parkingLotC;
        parkingLotA = mock(ParkingLot.class);
        parkingLotB = mock(ParkingLot.class);
        parkingLotC = mock(ParkingLot.class);

        when(parkingLotA.isParkingFull()).thenReturn(false);
        when(parkingLotA.getParkingLotSize()).thenReturn(5);
        when(parkingLotB.isParkingFull()).thenReturn(true);
        when(parkingLotC.isParkingFull()).thenReturn(false);
        when(parkingLotC.getParkingLotSize()).thenReturn(8);
        ArrayList<ParkingLot> parkingLotsList;
        parkingLotsList = new ArrayList<ParkingLot>();
        parkingLotsList.add(parkingLotA);
        parkingLotsList.add(parkingLotB);
        parkingLotsList.add(parkingLotC);

        ParkingLot parkingLot = festiveSeasonParkingStrategy.getFreeParkingLot(parkingLotsList,"");
        assertSame(parkingLotC, parkingLot);
    }

    @Test
    public void shouldIgnoreNonExistingParkingLot() throws Exception {
        ParkingLot parkingLotA, parkingLotB, parkingLotC;
        parkingLotA = mock(ParkingLot.class);
        parkingLotB = mock(ParkingLot.class);
        parkingLotC = mock(ParkingLot.class);

        when(parkingLotA.isParkingFull()).thenReturn(false);
        when(parkingLotA.getParkingLotSize()).thenReturn(5);
        when(parkingLotB.isParkingFull()).thenReturn(true);
        when(parkingLotC.isParkingFull()).thenReturn(false);
        when(parkingLotC.getParkingLotSize()).thenReturn(8);
        ArrayList<ParkingLot> parkingLotsList;
        parkingLotsList = new ArrayList<ParkingLot>();
        parkingLotsList.add(parkingLotA);
        parkingLotsList.add(null);
        parkingLotsList.add(parkingLotB);
        parkingLotsList.add(parkingLotC);

        ParkingLot parkingLot = festiveSeasonParkingStrategy.getFreeParkingLot(parkingLotsList,"");
        assertSame(parkingLotC, parkingLot);
    }

    @Test
    public void shouldThrowExceptionIfAllParkingLotsAreFull() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("All parking lots are full.");

        ParkingLot parkingLotA, parkingLotB, parkingLotC;
        parkingLotA = mock(ParkingLot.class);
        parkingLotB = mock(ParkingLot.class);

        when(parkingLotA.isParkingFull()).thenReturn(true);
        when(parkingLotB.isParkingFull()).thenReturn(true);
        ArrayList<ParkingLot> parkingLotsList;
        parkingLotsList = new ArrayList<ParkingLot>();
        parkingLotsList.add(parkingLotA);
        parkingLotsList.add(parkingLotB);
        festiveSeasonParkingStrategy.getFreeParkingLot(parkingLotsList,"");
    }
}