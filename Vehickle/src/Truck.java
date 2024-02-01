public class Truck extends Car {

    public Truck() {
        super();
        name = "Super Truck";
    }
    
    ///Soft drops a Vehicle if it is not a Truck and it hasn't already been dropped.
    public void tow(Vehicle other) {
        if(other instanceof Truck){
            return;
        }
        other.soft_drop();
    }
    
    @Override
    public double getValue(){
        return super.getValue() * Math.pow(0.95,(int)(miles / 10));
    }
}
