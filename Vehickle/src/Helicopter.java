public class Helicopter extends Vehicle {
    private int maxPassengerWeight;

    public double getValue(){
        return super.getValue() + 5000 * (int)(maxPassengerWeight / 1000);
    }
}
