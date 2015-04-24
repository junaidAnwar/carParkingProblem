import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    public static final String CAR_OBJECT_IS_NULL = "Car not present";
    private final int PARKING_LOT_FULL = -1;

    private final int parkingLotSize;
    private final Car parkedCars[];
    private final List<PersonObserver> personObserverList;
    private final List<AgentObserver> agentObserverList;

    public ParkingLot(int parkingLotSize) throws Exception {
        validateParkingLotSize(parkingLotSize);
        this.parkingLotSize = parkingLotSize;
        parkedCars = new Car[parkingLotSize];
        personObserverList = new ArrayList<PersonObserver>();
        agentObserverList = new ArrayList<AgentObserver>();
    }

    public int getParkingLotSize() {
        return parkingLotSize;
    }

    private void validateParkingLotSize(int parkingLotSize) throws Exception {
        if (parkingLotSize <= 0) {
            throw new Exception("Cannot create parking lot with no space");
        }
    }

    public void addPersonAsObserver(PersonObserver person) throws Exception {
        validatePerson(person);
        personObserverList.add(person);
    }

    private void validatePerson(Object person) throws Exception {
        if (person == null) {
            throw new Exception("Cannot add Person as Observer as person is not present");
        }
    }

    public int parkCar(Car car) throws Exception {
        validateCarForNull(car);
        validateIfSameCarIsAlreadyParked(car);
        if (isParkingFull()) {
            throw new Exception("Parking Lot is full");
        }
        int parkingSlotIndex = findEmptyParkingSlot();
        parkedCars[parkingSlotIndex] = car;
        notifyForPerson();
        notifyForAgent();
        return parkingSlotIndex;
    }

    public int getNumberOfFreeParkingLots() {
        int freeParkingLots =  0;
        for(int i = 0; i < parkingLotSize; i++){
            if(parkedCars[i] == null) {
                freeParkingLots++;
            }
        }
        return freeParkingLots;
    }

    private void notifyForAgent() {
        if(isParking80PercentFull()) {
            notifyFor80PercentFull();
        }
    }

    private boolean isParking80PercentFull() {
        int numberOfFreeParkingLots = getNumberOfFreeParkingLots();
        return (numberOfFreeParkingLots * 1.0 / parkingLotSize) <= 0.2;
    }

    private void notifyFor80PercentFull() {
        for (AgentObserver agentObserver : agentObserverList) {
            agentObserver.updateWhenParkingLotIs80PercentFull();
        }
    }

    private void notifyForLessThan80PercentFull() {
        for (AgentObserver agentObserver : agentObserverList) {
            agentObserver.updateWhenParkingLotIsNoLonger80PercentFull();
        }
    }


    private void notifyForMissingCar() {
        for (AgentObserver agentObserver : agentObserverList) {
            agentObserver.handleUpdateForCarNotFound();
        }
    }

    private void notifyForPerson() throws Exception {
        if (isParkingFull()) {
            notifyForFullParkingSpace();
            return;
        }
        notifyForFreeParkingSpace();
    }

    private void notifyForFullParkingSpace() throws Exception {
        for (PersonObserver personObserver : personObserverList) {
            personObserver.updateWhenParkingLotIsFull();
        }
    }

    private void notifyForFreeParkingSpace() {
        for (PersonObserver personObserver : personObserverList) {
            personObserver.updateWhenParkingLotIsAvailable();
        }
    }

    public boolean isParkingFull() {
        int freeParkingLots = getNumberOfFreeParkingLots();
        return freeParkingLots == 0;
    }

    private void validateIfSameCarIsAlreadyParked(Car car) throws Exception {
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkedCars[i] == car) {
                throw new Exception("Same Car is Already Parked");
            }
        }
    }

    private void validateCarForNull(Car car) {
        if (car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
    }

    private int findEmptyParkingSlot() {
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkedCars[i] == null) {
                return i;
            }
        }
        return PARKING_LOT_FULL;
    }

    public Car retrieveParkedCarForTicket(int parkingTicketNumber) throws Exception {
        checkForInvalidTicketNumber(parkingTicketNumber);
        Car car = parkedCars[parkingTicketNumber];
        notifyAndThrowExceptionForCarNotFound(car);

        boolean isParking80PercentFullBeforeCarRetrieval = isParking80PercentFull();

        parkedCars[parkingTicketNumber] = null;
        boolean isParking80PercentFullAfterCarRetrieval = isParking80PercentFull();
        notifyForFreeParkingSpace();
        if(isParking80PercentFullBeforeCarRetrieval && !isParking80PercentFullAfterCarRetrieval) {
            notifyForLessThan80PercentFull();
        }
        notifyFor80PercentFull();

        return car;
    }

    private void notifyAndThrowExceptionForCarNotFound(Car car) throws Exception {
        if (car == null) {
            notifyForMissingCar();
            throw new Exception("Car not Found");
        }
    }

    private void checkForInvalidTicketNumber(int parkingTicketNumber) throws Exception {
        if (parkingTicketNumber < 0 || parkingTicketNumber >= parkingLotSize) {
            throw new Exception("Invalid Ticket Number");
        }
    }

    public void addAgentObserver(AgentObserver agentObserver) {
        agentObserverList.add(agentObserver);
    }
}