import java.util.Observable;

public class ParkingLot extends Observable {

    public static final String CAR_OBJECT_IS_NULL = "Car not present";
    private final int PARKING_LOT_FULL = -1;

    private final int parkingLotSize;
    private final Car parkingSlots[];

    private boolean isParkingFull = false;

    public ParkingLot(int parkingLotSize) throws Exception {
        validateParkingLotSize(parkingLotSize);
        this.parkingLotSize = parkingLotSize;
        parkingSlots = new Car[parkingLotSize];
    }

    private void validateParkingLotSize(int parkingLotSize) throws Exception {
        if (parkingLotSize <= 0) {
            throw new Exception("Cannot create parking lot with no space");
        }
    }

    public void addParkingOwnerAsObserver(ParkingOwner parkingOwner) throws Exception {
        validateParkingOwner(parkingOwner);
        addObserver(parkingOwner);
    }

    private void validateParkingOwner(ParkingOwner parkingOwner) throws Exception {
        if (parkingOwner == null) {
            throw new Exception("Cannot add parking owner as owner is not present");
        }
    }

    public int parkCar(Car car) throws Exception {
        validateCarForNull(car);
        validateIfSameCarISAlreadyParked(car);
        if (isParkingFull) {
            throw new Exception("Parking Lot is full");
        }
        int parkingSlotIndex = findEmptyParkingSlot();
        parkingSlots[parkingSlotIndex] = car;
        checkAndNotifyForFullParkingSpace();
        return parkingSlotIndex;
    }

    private void validateIfSameCarISAlreadyParked(Car car) throws Exception {
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkingSlots[i] == car) {
                throw new Exception("Same Car is Already Parked");
            }
        }
    }

    private void checkAndNotifyForFullParkingSpace() throws Exception {
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkingSlots[i] == null) {
                return;
            }
        }
        isParkingFull = true;
        notifyParkingOwnerForFullParking();

    }

    private void notifyParkingOwnerForFullParking() {
        setChanged();
        notifyObservers(true);
    }

    private void validateCarForNull(Car car) {
        if (car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
    }

    private int findEmptyParkingSlot() {
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkingSlots[i] == null) {
                return i;
            }
        }

        return PARKING_LOT_FULL;
    }


    public Car retrieveParkedCarForTicket(int parkingTicketNumber) throws Exception {
        checkForInvalidTicketNumber(parkingTicketNumber);
        Car car = checkAndNotifyForFreeParkingSlot(parkingTicketNumber);
        if (car == null) {
            throw new Exception("Car not Found");
        }
        return car;
    }

    private Car checkAndNotifyForFreeParkingSlot(int parkingTicketNumber) {
        Car car = parkingSlots[parkingTicketNumber];
        if (car != null) {
            parkingSlots[parkingTicketNumber] = null;
            isParkingFull = false;
            notifyParkingOwnerForAvailableParking();
        }
        return car;
    }

    private void notifyParkingOwnerForAvailableParking() {
        setChanged();
        notifyObservers(false);
    }

    private void checkForInvalidTicketNumber(int parkingTicketNumber) throws Exception {
        if (parkingTicketNumber < 0 || parkingTicketNumber >= parkingLotSize) {
            throw new Exception("Invalid Ticket Number");
        }
    }


}