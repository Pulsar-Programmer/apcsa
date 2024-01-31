public class Highway {
    public static void main(String[] args) throws Exception {
        var vehi = new Vehicle();
        var car = new Car();
        var truck = new Truck();
        var heli = new Helicopter();

        Vehicle[] my_vehicles = new Vehicle[4];
        my_vehicles[0] = vehi;
        my_vehicles[1] = car;
        my_vehicles[2] = truck;
        my_vehicles[3] = heli;
        System.out.println("Welcome to the Highway!");
        
        System.out.println("");

        heli.increaseMaxPassengerWeight(10_000);

        truck.tow(car);
        var heli2 = Car.rebuild(car);
        my_vehicles[3] = heli2;


    }
}
