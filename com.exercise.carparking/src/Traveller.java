
public class Traveller {
    public static final String CAR_OBJECT_IS_NULL = "Car not present";
    public static final String CAR_IS_ALREADY_PARKED = "Car is already Parked!";
    public static final String CAR_IS_UNPARKED = "Car is unparked";
    public static final String CAR_IS_NOT_PARKED = "Car is not parked";
    Car car;
    private boolean isCarParked=false;
    public Traveller(Car car) {
        this.car = car;
    }

    public String parkCar() throws Exception {
        if(car == null) {
            throw new NullPointerException(CAR_OBJECT_IS_NULL);
        }
        if(isCarParked) {
            throw new Exception(CAR_IS_ALREADY_PARKED);
        }
        isCarParked=true;
        return "Car is Parked";
    }

    public String retrieveParkedCar() {
        if(isCarParked) {
            isCarParked=false;
            return CAR_IS_UNPARKED;
        }
        return CAR_IS_NOT_PARKED;
    }
}
