public class Helicopter extends Vehicle {
    private int maxPassengerWeight; ///in thousands

    @Override
    public double getValue(){
        return super.getValue() + 5000 * (int)(maxPassengerWeight / 1);
    }

    @Override
    public double getScrapValue(){
        final var value = BASE_VALUE + 5000 * (int)(maxPassengerWeight / 1);
        return 0.3 * value;
    }

    public Helicopter(){
        super();
        maxPassengerWeight = 10;
        name = "My Copter";
    }

    ///We rebuild a Helicopter from a Vehicle.
    public static Helicopter rebuild(Vehicle from){
        var my_heli = new Helicopter();
        ///We set the relevant Helicopter parameters.
        my_heli.name = from.name;
        my_heli.maxPassengerWeight = 100;
        ///We soft drop it after reading relevant info.
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
                (condition == 0 ? ", scrapped_value=" + getScrapValue() : ", value=" + getValue()) +
            '}';
    }
}
