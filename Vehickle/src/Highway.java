public class Highway {
    public static void main(String[] args) throws Exception {
        ///We create our beautiful set of four vehicles.
        var vehi = new Vehicle();
        var car = new Car();
        var truck = new Truck();
        var heli = new Helicopter();

        ///We actually put them in the set.
        Vehicle[] my_vehicles = {vehi, car, truck, heli};
        System.out.println("Welcome to the Highway!");
        System.out.println("Here are the vehicles on the Highway!");
        ///We print the vehicles on the Highway.
        for(Vehicle elem : my_vehicles){
            System.out.println(elem);
        }
        ///We increase the max_weight_capacity.
        heli.increaseMaxPassengerWeight(10_000);
        System.out.println("The helicopter can hold more weight suddenly!");
        System.out.println("Helicopter: " + heli);
                            // System.out.println(Vehicle.getActiveCars());
        ///We tow the car.
        truck.tow(car);
        System.out.println("The car broke down! Let's tow it with our handy dandy truck!");
        System.out.println("Car: " + car);
        ///We rebuild the helicopter from the vehicle at slot 0.
        vehi = Helicopter.rebuild(vehi);
        System.out.println("The vehicle is... no more... let's rebuild it into a helicopter!!");
        System.out.println("New Helicopter: " + vehi);
                            // System.out.println(Vehicle.getActiveCars());
        System.out.println("We still have some parts left over from the car... so let's make a golf cart!");
        ///We create a golf cart.
        var my_golf_cart = Car.salvage(car);
        my_vehicles[1] = my_golf_cart;
        System.out.println("Golf Cart: " + my_golf_cart);
        ///We tow x3.
        for(var i = 0; i < 3; i++){
            ///We deduce idx and get that corresponding vehicle.
            final var idx = (i + 3) % 4;
            final var my_vehicle = my_vehicles[idx];
            ///We then tow that vehicle.
            truck.tow(my_vehicle);
            System.out.println("We must tow " + my_vehicle);
        }

        ///We report the Vehicles active cars and total cars.
        System.out.println(Vehicle.getActiveCars() + " Active Cars and " + Vehicle.getTotalCars() + " Total Cars.");
    }
}
