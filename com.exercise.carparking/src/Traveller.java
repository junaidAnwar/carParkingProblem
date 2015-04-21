
public class Traveller {
    public static final String CAR_OBJECT_IS_NULL = "Car object is null";
    public static final String CAR_IS_ALREADY_PARKED = "Car is already Parked!";
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
}
