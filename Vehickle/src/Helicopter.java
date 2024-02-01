public class Helicopter extends Vehicle {
    private int maxPassengerWeight;

    @Override
    public double getValue(){
        return super.getValue() + 5000 * (int)(maxPassengerWeight / 1000);
    }

    @Override
    public double getScrapValue(){
        final var value = BASE_VALUE + 5000 * (int)(maxPassengerWeight / 1000);
        return 0.3 * value;
    }

    public Helicopter(){
        super();
        maxPassengerWeight = 100;
        name = "My Copter";
    }

    ///We rebuild a Helicopter from a Vehicle.
    public static Helicopter rebuild(Vehicle from){
        var my_heli = new Helicopter();
        my_heli.name = from.name;
        my_heli.increaseMaxPassengerWeight(100_000);
        from.soft_drop();
        return my_heli;
    }

    ///We increase the max_weight_capacity
    public void increaseMaxPassengerWeight(int maxPassengerWeight) {
        this.maxPassengerWeight += maxPassengerWeight;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                ", miles=" + miles +
                ", max_passenger_weight=" + maxPassengerWeight +
                ", value=" + (condition == 0 ? getScrapValue() : getValue()) +
            '}';
    }
}
