public class Truck extends Car {
    public void tow(Vehicle other) {
        //TODO
    }


    public double getValue(){
        return super.getValue() + 5000 * (int)(1 / 1000);
    }
}
