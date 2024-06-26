public class Vehicle {
    protected String name;
    protected int condition;
    protected int miles; ///in thousands

    protected static int activeCars = 0;
    protected static int totalCars = 0;

    protected final static double BASE_VALUE = 20_000.0;
    protected final static double CONDITION_VALUE = 8_000.0;

    ///We construct a vehicle.
    public Vehicle(String name, int condition, int miles) {
        setName(name);
        setCondition(condition);
        setMiles(miles);
    }

    public Vehicle() {
        this("Honda Accord", 5, 0);
    }

    public Vehicle(String name) {
        this();
        setName(name);
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
        ///If the intended condition is zero, we simply soft_drop.
        if(condition==0) soft_drop();
        ///Clamp the value between 1-5.
        else this.condition = Math.max(1, Math.min(5, condition)); 
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        ///If the miles have surpassed a certain amount, we need to make the drop.
        if(miles >= 200){
            soft_drop();
        }
        this.miles = Math.max(0, Math.min(200, miles)); ///Clamp the value between 0-200.;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                stdfmtdebug_inner() +
            '}';
    }

    ///We create a std::fmt::Debug implementation to be used for the inner fields in all the classes.
    protected String stdfmtdebug_inner() {
        return "name=" + name + '\'' +
        ", condition=" + condition +
        ", miles=" + miles +
        (condition == 0 ? ", scrapped_value=" + getScrapValue() : ", value=" + getValue());
    }

    public double getValue(){
        return condition * CONDITION_VALUE + BASE_VALUE;
    }

    public double getScrapValue(){
        return 0;
    }

    ///Implementation of deactivate, scrapping, and condition=0. You can only do this once.
    protected void soft_drop(){
        condition=0;
    }

    public static int getActiveCars() {
        return activeCars;
    }

    public static int getTotalCars() {
        return totalCars;
    }
    
}
