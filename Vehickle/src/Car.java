public class Car extends Vehicle {
    @Override
    public double getValue(){
        return super.getValue() + 1000 * condition;
    }

    @Override
    public double getScrapValue(){
        final var value = super.getValue() + 1000 * 5;
        final var removed = value * 0.1 - 500 * (int)(miles);
        final var clamped = Math.max(0, Math.min(2000, removed)); ///Clamp the value between 0-2000.
        return clamped;
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

    ///Drops the vehicle in question and returns a Golf Cart.
    public static Vehicle salvage(Car from){
        from.soft_drop();
        final var my_vehicle = new Vehicle("Golf Cart");
        return my_vehicle;
    }

    @Override
    protected void soft_drop(){
        if(condition==0){
            return;
        }
        super.soft_drop();
        activeCars -= 1;
    }

    @Override
    public String toString() {
        return "Car{" +
            stdfmtdebug_inner() +
        '}';
    }
}
