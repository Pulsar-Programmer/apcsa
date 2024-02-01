public class Vehicle {
    protected String name;
    protected int condition;
    protected int miles; //in thousands

    protected static int activeCars = 0;
    protected static int totalCars = 0;

    protected final static double BASE_VALUE = 20_000.0;
    protected final static double CONDITION_VALUE = 8_000.0;

    public Vehicle(String name, int condition, int miles) {
        this.name = name;
        this.condition = condition;
        this.miles = miles;
        activeCars += 1;
        totalCars += 1;
    }

    public Vehicle() {
        name = "Honda Accord";
        condition = 5;
        miles = 0;
        activeCars += 1;
        totalCars += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        if((this instanceof Car || this instanceof Truck) && condition==0) activeCars -= 1;
        this.condition = condition;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        setCondition(0);
        if(miles >= 200){
            this.miles = 200;
            return;
        }
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                ", miles=" + miles +
                ", value=" + (condition == 0 ? getScrapValue() : getValue()) +
            '}';
    }

    public double getValue(){
        return condition * CONDITION_VALUE + BASE_VALUE;
    }

    public double getScrapValue(){
        return 0;
    }
    

    
    
}
