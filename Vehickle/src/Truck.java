public class Truck extends Car {

    public Truck() {
        super();
        name = "Super Truck";
    }
    
    ///Soft drops a Vehicle if it is not a Truck and it hasn't already been dropped.
    public void tow(Vehicle other) {
        ///if it is a Truck, we cannot tow it.
        if(other instanceof Truck){
            return;
        }
        ///We soft_drop the Vehicle.
        other.soft_drop();
    }
    
    @Override
    public double getValue(){
        return super.getValue() * Math.pow(0.95,(int)(miles / 10));
    }

    @Override
    public String toString() {
        return "Truck{" +
            stdfmtdebug_inner() +
        '}';
    }
}
