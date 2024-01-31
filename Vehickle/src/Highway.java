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

        System.out.println("Here are the vehicles on the Highway!");
        for(Vehicle elem : my_vehicles){
            System.out.println(elem);
        }

        heli.increaseMaxPassengerWeight(10_000);

        truck.tow(car);
        var heli2 = Car.rebuild(car);
        my_vehicles[3] = heli2;

        var my_golf_cart = Car.salvage(car);
        my_vehicles[1] = my_golf_cart;

        for(var i = 0; i < 3; i++){
            var idx = (i + 3) % 4;
            truck.tow(my_vehicles[idx]);
        }

    }
}
