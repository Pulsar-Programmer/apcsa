public class Car extends Vehicle {
    public double getValue(){
        return super.getValue() + 1000 * condition;
    }
}
