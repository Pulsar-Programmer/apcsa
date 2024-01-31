public class Truck extends Car {
    
    public void tow(Vehicle other) {
        if(other instanceof Truck || other.condition == 0){
            return;
        }
        other.condition = 0;
        activeCars -= 1;
    }
    
    @Override
    public double getValue(){
        return super.getValue() * Math.pow(0.95,(int)(miles / 10));
    }
}
