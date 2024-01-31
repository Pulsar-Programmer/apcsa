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

    public void increaseMaxPassengerWeight(int maxPassengerWeight) {
        this.maxPassengerWeight += maxPassengerWeight;
    }
}
