public class Truck extends Car {


    public void tow(Vehicle other) {
        if(other instanceof Truck){
            return;
        }
        other.condition = 0;
    }

    public double getValue(){
        return super.getValue() * Math.pow(0.95,(int)(miles / 10));
    }
}
