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
        System.out.println("The helicopter can hold more weight suddenly!");
        System.out.println("Helicopter: " + heli);

        truck.tow(car);
        System.out.println("The car broke down! Let's tow it with our handy dandy truck!");
        System.out.println("Car: " + car);
        var heli2 = Helicopter.rebuild(vehi);
        System.out.println("The vehicle is... no more... let's rebuild it into a helicopter!!");
        System.out.println("The highway only has room for one helicopter, so we're going to have to fly the other helicopter away!");
        System.out.println("New Helicopter: " + heli2);
        my_vehicles[3] = heli2;

        System.out.println("We still have some parts left over from the car... so let's make a golf cart!");
        var my_golf_cart = Car.salvage(car);
        my_vehicles[1] = my_golf_cart;
        System.out.println("Golf Cart: " + my_golf_cart);

        for(var i = 0; i < 3; i++){
            var idx = (i + 3) % 4;
            truck.tow(my_vehicles[idx]);
        }

    }
}
