public class Helicopter extends Vehicle {
    private int maxPassengerWeight;

    public double getValue(){
        return super.getValue() + 5000 * (int)(maxPassengerWeight / 1000);
    }

    public double getScrapValue(){
        final var value = BASE_VALUE + 5000 * (int)(maxPassengerWeight / 1000);
        return 0.3 * value;
    }

    // public Helicopter(){
    //     maxPassengerWeight = 1000;
    // }

    public static Helicopter rebuild(Vehicle from){
        var my_heli = new Helicopter();
        my_heli.increaseMaxPassengerWeight(100_000);
        return my_heli;
    }

    public void increaseMaxPassengerWeight(int maxPassengerWeight) {
        this.maxPassengerWeight += maxPassengerWeight;
    }
}
