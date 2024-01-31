public class Car extends Vehicle {
    public double getValue(){
        return super.getValue() + 1000 * condition;
    }

    public double getScrapValue(){
        final var value = super.getValue() + 1000 * 5;
        final var removed = value * 0.1 - 500 * (int)(miles);
        final var clamped = Math.max(0, Math.min(2000, removed));
        return clamped;
    }

    @Override
    public void setMiles(int miles) {
        if(miles >= 200){
            this.miles = 200;
            activeCars -= 1;
            return;
        }
        super.setMiles(miles);
    }

    public Car(String name, int condition, int miles) {
        super(name, condition, miles);
        activeCars += 1;
        totalCars += 1;
    }

    public Car() {
        super();
        activeCars += 1;
        totalCars += 1;
    }
}
