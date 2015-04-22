/**
 * Created by Junaid on 22-04-2015.
 */
public class ParkingLot {

    public static final String CAR_OBJECT_IS_NULL = "Car not present";
    private final int PARKING_LOT_FULL = -1;

    private final int parkingLotSize;
    private final boolean parkingSlots[];

    private boolean isParkingFull = false;

    public ParkingLot(int parkingLotSize) throws Exception {
        validateParkingLotSize(parkingLotSize);
        this.parkingLotSize = parkingLotSize;
        parkingSlots = new boolean[parkingLotSize];
    }

    private void validateParkingLotSize(int parkingLotSize) throws Exception {
        if(parkingLotSize <= 0){
            throw new Exception("Cannot create parking lot with no space");
        }
    }

    public int parkCar(Car car) throws Exception {
        validateCarForNull(car);
        if(isParkingFull){
            throw new Exception("Parking Lot is full");
        }
        int parkingSlotIndex = findEmptyParkingSlot();
        parkingSlots[parkingSlotIndex]=true;
        checkAndSetForFullParkingSpace();
        return parkingSlotIndex;
    }

    private void checkAndSetForFullParkingSpace() throws Exception {
        for(int i=0;i<parkingLotSize;i++) {
            if(!parkingSlots[i]) {
               return;
            }
        }
        isParkingFull = true;
    }

    private void validateCarForNull(Car car) {
        if(car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
    }

    private int findEmptyParkingSlot() {
        for(int i=0;i<parkingLotSize;i++) {
            if(!parkingSlots[i]) {
                return i;
            }
        }

        return PARKING_LOT_FULL;
    }


    public boolean retrieveParkedCarForTicket(int parkingTicketNumber) throws Exception {
        testForInvalidTicketNumber(parkingTicketNumber);
        return true;

    }

    private void testForInvalidTicketNumber(int parkingTicketNumber) throws Exception {
        if(parkingTicketNumber < 0  || parkingTicketNumber >= parkingLotSize){
            throw new Exception("Invalid Ticket Number");
        }
    }
}
