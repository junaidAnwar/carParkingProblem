import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NormalSeasonParkingStrategyTest {
    NormalSeasonParkingStrategy normalSeasonParkingStrategy;

@Before
public void setUp(){
    normalSeasonParkingStrategy = new NormalSeasonParkingStrategy();
}

    @Test
    public void shouldThrowExceptionWhenNoParkingLotListIsPassed() throws Exception {

        try {
            normalSeasonParkingStrategy.getFreeParkingLot(null);
        } catch (Exception e) {
           assertEquals("Invalid parking lot list.", e.getMessage());
        }

    }
}