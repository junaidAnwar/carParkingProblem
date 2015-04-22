import java.util.Observable;

public class ParkingLot extends Observable{

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

    public void addParkingOwnerAsObserver(ParkingOwner parkingOwner) throws Exception {
        validateParkingOwner(parkingOwner);
        addObserver(parkingOwner);
    }

    private void validateParkingOwner(ParkingOwner parkingOwner) throws Exception {
        if(parkingOwner == null){
            throw new Exception("Cannot add parking owner as owner is not present");
        }
    }
    public int parkCar(Car car) throws Exception {
        validateCarForNull(car);
        if(isParkingFull){
            throw new Exception("Parking Lot is full");
        }
        int parkingSlotIndex = findEmptyParkingSlot();
        parkingSlots[parkingSlotIndex]=true;
        checkAndNotifyForFullParkingSpace();
        return parkingSlotIndex;
    }

    private void checkAndNotifyForFullParkingSpace() throws Exception {
        for(int i=0;i<parkingLotSize;i++) {
            if(!parkingSlots[i]) {
               return;
            }
        }
        isParkingFull = true;
        notifyParkingOwnerForFullParking();

    }

    private void notifyParkingOwnerForFullParking(){
        setChanged();
        notifyObservers(true);
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
        checkForInvalidTicketNumber(parkingTicketNumber);
        if (checkAndNotifyForFreeParkingSlot(parkingTicketNumber)){
            return true;
        }

        return false;

    }

    private boolean checkAndNotifyForFreeParkingSlot(int parkingTicketNumber) {
        if(parkingSlots[parkingTicketNumber]){
            parkingSlots[parkingTicketNumber] = false;
            isParkingFull = false;
            notifyParkingOwnerForAvailableParking();
            return true;
        }
        return false;
    }

    private void notifyParkingOwnerForAvailableParking() {
        setChanged();
        notifyObservers(false);
    }

    private void checkForInvalidTicketNumber(int parkingTicketNumber) throws Exception {
        if(parkingTicketNumber < 0  || parkingTicketNumber >= parkingLotSize){
            throw new Exception("Invalid Ticket Number");
        }
    }


}
